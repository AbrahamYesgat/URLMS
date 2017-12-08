<?php
namespace App\Http\Controllers\URLMS;

use App\User;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Exception;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Laboratory;
use App\Http\Controllers\URLMS\Model\Staff;
use App\Http\Controllers\URLMS\Model\StaffRole;

class MainController extends Controller
{
    private $urlms = null;
    
    private $currentUser = null;
    private $currentLab = null;
    
    public function __construct() {
        $this->urlms = PersistenceController::loadModel();
    }
    
    /*
     * Authentification
     */
    public function login(Request $request) {
        $this->updateCurrent($request);
        $email = $request->input('email');
        $password = $request->input('password');
        
        $labs = $this->urlms->getLaboratories();
        
        foreach($this->urlms->getDirectors() as $dir) {
            if($dir->getEmail() == $email && $dir->getPassword() == $password) {
                $this->currentUser = $dir;
                
                $request->session()->put('logged-in', true);
                $request->session()->put('email', $email);
                
                return response()->json(['status' => true]);
            }
        }
        
        if ($this->urlms->numberOfLaboratories() != 0) {
            foreach($labs as $lab) {
                foreach($lab->getStaffs() as $staff) {
                    if($staff->getEmail() == $email && $staff->getPassword() == $password) {
                        $this->currentUser = $staff;
                        
                        $request->session()->put('logged-in', true);
                        $request->session()->put('email', $email);
                        
                        return response()->json(['status' => true]);
                    }
                }
            }
        }
        
        return response()->json(['status' => false]);
    }
    
    public function register(Request $request) {
        $this->updateCurrent($request);
        $name = $request->input('name');
        $email = $request->input('email');
        $password = $request->input('password');
        
        if($name == null || $name == '' || $email == null || $email == '' || $password == null || $password == '') {
            return response()->json(['status' => false, 'message' => 'Empty string detected']);
        }
        
        foreach($this->urlms->getDirectors() as $dir) {
            if($dir->getEmail() == $email) {
                return response()->json(['status' => false, 'message' => 'A director is already using this email']);
            }
        }
        
        try {
            $this->currentUser = $this->urlms->addDirectorVia($email, $password, $name);
            PersistenceController::saveModel($this->urlms);
        } catch(Exception $e) {
            return response()->json(['status' => false, 'message' => $e->getMessage()]);
        }
        
        $request->session()->put('logged-in', true);
        $request->session()->put('email', $email);
        return response()->json(['status' => true, 'name' => $name]);
    }
    
    public function logout(Request $request) {
        $this->updateCurrent($request);
        PersistenceController::saveModel($this->urlms);
        $request->session()->forget(['logged-in', 'email', 'labName']);
        
        return response()->json(['status' => true]);
    }
    
    private function updateCurrent(Request $request) {
        if($request->session()->get('logged-in')) {
            foreach($this->urlms->getDirectors() as $dir) {
                if($dir->getEmail() == $request->session()->get('email')) {
                    $this->currentUser = $dir;
                    break;
                }
            }
            
            if ($this->urlms->numberOfLaboratories() != 0) {
                foreach($this->urlms->getLaboratories() as $lab) {
                    foreach($lab->getStaffs() as $staff) {
                        if($staff->getEmail() == $request->session()->get('email')) {
                            $this->currentUser = $staff;
                            break;
                        }
                    }
                }
            }
            
            foreach ($this->urlms->getLaboratories() as $lab) {
                if($lab->getName() == $request->session()->get('labName')) {
                    $this->currentLab = $lab;
                    break;
                }
            }
        }
    }
    
    /*
     * Profile
     */
    public function getCurrentUser(Request $request) {
        $this->updateCurrent($request);
        if($this->currentUser != null && $this->currentUser instanceof Director) {
            return response()->json(['status' => true, 
                                    'director' => true, 
                                    'name' => $this->currentUser->getName(),
                                    'email' => $this->currentUser->getEmail(),
                                    'role' => null
            ]);
        }
        
        if($this->currentUser != null && $this->currentUser instanceof Staff) {
            return response()->json(['status' => true,
                                    'director' => false,
                                    'name' => $this->currentUser->getName(),
                                    'email' => $this->currentUser->getEmail(),
                                    'role' => ($this->currentUser->getStaffRole() == StaffRole::ResearchAssociate) ? 'Research Associate' : 'Research Assistant'
            ]);
        }
        
        return response()->json(['status' => false]);
    }
    
    public function updatePassword(Request $request) {
        $this->updateCurrent($request);
        
        if($this->currentUser != null) {
            $previousPassword = $request->input('previousPassword');
            $newPassword = $request->input('newPassword');
            
            if ($this->currentUser->getPassword() == $previousPassword) {
                $this->currentUser->setPassword($newPassword);
                
                PersistenceController::saveModel($this->urlms);
                return response()->json(['status' => true]);
            }
        }
        
        return response()->json(['status' => false, 'message' => 'Bad previous password']);
    }
    
    public function updateProfile(Request $request) {
        $this->updateCurrent($request);
        
        $name = $request->input('name');
        $email = $request->input('email');
        $role = $request->input('role');
        
        if($this->currentUser != null) {
            $this->currentUser->setName($name);
            $this->currentUser->setEmail($email);
            
            if($this->currentUser instanceof Staff) {
                $this->currentUser->setStaffRole(($role == 0) ? StaffRole::ResearchAssistant : StaffRole::ResearchAssociate);
            }
            
            PersistenceController::saveModel($this->urlms);
            return response()->json(['status' => true]);
        }
            
        return response()->json(['status' => false, 'message' => 'Could not update profile']);
    }
    
    /*
     * Labs
     */
    public function addLab(Request $request) {
        $this->updateCurrent($request);
        $name = $request->input('name');
        $field = $request->input('field');
        $date = $request->input('date');
        $active = $request->input('active');
        
        if($this->currentUser != null) {
            foreach($this->currentUser->getLaboratories() as $lab) {
                if($name == $lab->getName()) {
                    return response()->json(['status' => false, 'message' => 'A lab already exists with this name']);
                }
            }
            
            if($this->currentUser instanceof Director) {
                try{
                    $this->currentUser->addLaboratoryVia($name, $field, $date, $active, $this->urlms, $this->currentUser);
                } catch(Exception $e) {
                    return response()->json(['status' => false, 'message' => $e->getMessage()]);
                }
                
                PersistenceController::saveModel($this->urlms);
                return response()->json(['status' => true]);
            }
        }
        
        return response()->json(['status' => false, 'message' => 'No current director']);
    }
    
    public function removeLab(Request $request) {
        $this->updateCurrent($request);
        $name = $request->input('name');
        
        if($this->currentUser != null) {
            if($this->currentUser instanceof Director) {
                try{
                    foreach($this->currentUser->getLaboratories() as $lab) {
                        if($name == $lab->getName()) {
                            $lab->delete();
                            PersistenceController::saveModel($this->urlms);
                            return response()->json(['status' => true]);
                        }
                    }
                } catch(Exception $e) {
                    return response()->json(['status' => false, 'message' => $e->getMessage()]);
                }
                
                return response()->json(['status' => false, 'message' => 'Unknown error']);
            }
        }
        
        return response()->json(['status' => false, 'message' => 'No current director']);
    }
    
    public function clearLabs(Request $request) {
        $this->updateCurrent($request);
        if($this->currentUser != null) {
            if($this->currentUser instanceof Director) {
                try{
                    foreach($this->currentUser->getLaboratories() as $lab) {
                        $lab->delete();
                    }
                } catch(Exception $e) {
                    return response()->json(['status' => false, 'message' => $e->getMessage()]);
                }
                PersistenceController::saveModel($this->urlms);
                return response()->json(['status' => true]);
            }
        }
        
        return response()->json(['status' => false, 'message' => 'No current director']);
    }
    
    public function enterLab(Request $request) {
        $this->updateCurrent($request);
        $name = $request->input('name');
        
        if($this->currentUser != null) {
            foreach($this->currentUser->getLaboratories() as $lab) {
                if($name == $lab->getName()) {
                    $this->currentLab = $lab;
                    $request->session()->put('labName', $name);
                    return response()->json(['status' => true]);
                }
            }
        }
        
        return response()->json(['status' => false, 'message' => 'Could not find lab']);
    }
    
    public function updateLab(Request $request) {
        $this->updateCurrent($request);
        $newName = $request->input('newName');
        $field = $request->input('field');
        $active = $request->input("active");
        
        if($this->currentUser != null && $this->currentLab != null) {
            $this->currentLab->setName($newName);
            $this->currentLab->setActive($active);
            $this->currentLab->setFieldOfStudy($field);
            PersistenceController::saveModel($this->urlms);
            return response()->json(['status' => true]);
        }
        
        return response()->json(['status' => false, 'message' => 'Could not find lab']);
    }
    
    public function getCurrentLab(Request $request) {
        $this->updateCurrent($request);
        if($this->currentLab != null) {
            return response()->json(['status' => true, 'name' => $this->currentLab->getName(), 'field' => $this->currentLab->getFieldOfStudy(), 'date' => $this->currentLab->getStartDate(), 'active' => $this->currentLab->getActive()]);
        } else {
            return response()->json(['status' => false]);
        }
    }
    
    public function getLabs(Request $request) {
        $this->updateCurrent($request);
        if($this->currentUser != null) {
            $modelLabs = $this->currentUser->getLaboratories();
            $resLabs = [];
            
            foreach($modelLabs as $lab) {
                $resLabs[] = ['name' => $lab->getName(), 
                              'field' => $lab->getFieldOfStudy(), 
                              'date' => $lab->getStartDate(), 
                              'active' => $lab->getActive()
                ];
            }
            
            return response()->json(['status' => true, 'labs' => $resLabs]);
        } else {
            return response()->json(['status' => false]);
        }
    }
    
    /*
     * Staff
     */
    public function getStaff(Request $request) {
        $this->updateCurrent($request);
        if($this->currentLab != null) {
            $modelStaff = $this->currentLab->getStaffs();
            $resStaff = [];
            
            foreach($modelStaff as $staff) {
                $resStaff[] = ['name' => $staff->getName(),
                    'email' => $staff->getEmail(),
                    'role' => ($staff->getStaffRole() == StaffRole::ResearchAssistant) ? 'Research Assistant' : 'Research Associate'
                ];
            }
            
            return response()->json(['status' => true, 'staff' => $resStaff]);
        } else {
            return response()->json(['status' => false, 'message' => 'No lab could be found']);
        }
    }
    
    public function addStaff(Request $request){
        $this->updateCurrent($request);
        
        $name = $request->input('name');
        $email = $request->input('email');
        $role = $request->input('role');
        $password = $request->input('password');
        
        if($this->currentLab != null) {
            foreach($this->urlms->getLaboratories() as $lab) {
                foreach($lab->getStaffs() as $staff) {
                    if($staff->getEmail() == $email) {
                        return response()->json(['status' => false, 'message' => 'User already exists']);
                    }
                }
            }
            
            foreach($this->urlms->getDirectors() as $dir) {
                if($dir->getEmail() == $email) {
                    return response()->json(['status' => false, 'message' => 'User already exists']);
                }
            }
            
            new Staff($email, $password, $name, ($role == 0) ? StaffRole::ResearchAssociate : StaffRole::ResearchAssistant, [$this->currentLab]);
            PersistenceController::saveModel($this->urlms);
            return response()->json(['status' => true]);
        } else {
            return response()->json(['status' => false, 'message' => 'No lab could be found']);
        }
    }
    
    public function clearStaff(Request $request) {
        $this->updateCurrent($request);
        
        if($this->currentLab != null) {
           foreach($this->currentLab->getStaffs() as $staff) {
               $staff->delete();
           }
            
           PersistenceController::saveModel($this->urlms);
           return response()->json(['status' => true]);
        } else {
            return response()->json(['status' => false, 'message' => 'No lab could be found']);
        }
    }
    
    public function removeStaff(Request $request) {
        $this->updateCurrent($request);
        $email = $request->input('email');
        
        if($this->currentLab != null) {
            foreach($this->currentLab->getStaffs() as $staff) {
                if($staff->getEmail() == $email) {
                    $staff->delete();
                    break;
                }
            }
            
            PersistenceController::saveModel($this->urlms);
            return response()->json(['status' => true]);
        } else {
            return response()->json(['status' => false, 'message' => 'No lab could be found']);
        }
    }
}