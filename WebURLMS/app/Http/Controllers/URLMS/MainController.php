<?php
namespace App\Http\Controllers\URLMS;

use App\User;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Exception;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Laboratory;
use App\Http\Controllers\URLMS\Model\Staff;

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
        $this->currentLab = null;
        $this->currentUser = null;
        PersistenceController::saveModel($this->urlms);
        $request->session()->flush();
        
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
     * Action restriction
     */
    public function getCurrentUser(Request $request) {
        $this->updateCurrent($request);
        if($this->currentUser != null && $this->currentUser instanceof Director) {
            return response()->json(['status' => true, 
                                    'admin' => true, 
                                    'name' => $this->currentUser->getName(),
                                    'email' => $this->currentUser->getEmail(),
                                    'role' => null
            ]);
        }
        
        if($this->currentUser != null && $this->currentUser instanceof Staff) {
            return response()->json(['status' => true,
                                    'admin' => true,
                                    'name' => $this->currentUser->getName(),
                                    'email' => $this->currentUser->getEmail(),
                                    'role' => ($this->currentUser->getStaffRole() == ResearchAssociate) ? 'Research Associate' : 'Research Assistant'
            ]);
        }
        
        return response()->json(['status' => false]);
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
        $prevName = $request->input('prevName');
        $newName = $request->input('newName');
        $field = $request->input('field');
        $active = $request->input("active");
        
        if($this->currentUser != null) {
            foreach($this->currentUser->getLaboratories() as $lab) {
                if($prevName == $lab->getName()) {
                    $lab->setName($newName);
                    $lab->setActive($active);
                    $lab->setFieldOfStudy($field);
                    PersistenceController::saveModel($this->urlms);
                    return response()->json(['status' => true]);
                }
            }
        }
        
        return response()->json(['status' => false, 'message' => 'Could not find lab']);
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
}