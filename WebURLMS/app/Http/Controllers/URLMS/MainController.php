<?php
namespace App\Http\Controllers\URLMS;

use App\User;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Exception;

class MainController extends Controller
{
    private $urlms = null;
    
    private $currentUser = null;
    private $currentLab = null;
    
    public function __construct() {
        $this->urlms = PersistenceController::loadModel();

    }
    
    /*
     * Login
     */
    public function login(Request $request) {
        $email = $request->input('email');
        $password = $request->input('password');
        
        $labs = $this->urlms->getLaboratories();
        
        foreach($this->urlms->getDirectors() as $dir) {
            if($dir->getEmail() == $email && $dir->getPassword() == $password) {
                $this->currentUser = $dir;
                
                session(['logged-in' => true, 'name' => $dir->getName()]);
                
                return response()->json(['status' => true]);
            }
        }
        
        if ($this->urlms->numberOfLaboratories() != 0) {
            foreach($labs as $lab) {
                foreach($lab->getStaffs() as $staff) {
                    if($staff->getEmail() == $email && $staff->getPassword() == $password) {
                        $this->currentUser = $staff;
                        
                        session(['logged-in' => true, 'name' => $staff->getName()]);
                        
                        return response()->json(['status' => true]);
                    }
                }
            }
        }
        
        return response()->json(['status' => false]);
    }
    
    public function register(Request $request) {
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
            $this->activeUser = $this->urlms->addDirectorVia($email, $password, $name);
            PersistenceController::saveModel($this->urlms);
        } catch(Exception $e) {
            return response()->json(['status' => false, 'message' => $e->getMessage()]);
        }
        
        session(['logged-in' => true, 'name' => $name]);
        return response()->json(['status' => true, 'name' => $name]);
    }
    
    public function logout() {
        $this->currentLab = null;
        $this->currentUser = null;
        PersistenceController::saveModel($this->urlms);
        session(['logged-in' => false, 'name' => '']);
        
        return response()->json(['status' => true]);
    }
    
    /*
     * Getters and setters
     */
    
    public function setCurrentUser($user) {
        $this->currentUser = $user;
    }
    
    public function getCurrentUser() {
        return $this->currentUser;
    }
    
    public function setCurrentLab($lab) {
        $this->currentLab = $lab;
    }
    
    public function getCurrentLab() {
        return $this->currentLab;
    }
}