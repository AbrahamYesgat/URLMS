<?php 
foreach(glob(dirname(__FILE__) . '/../model/*.php') as $file){
    require_once $file;
}

require_once dirname(__FILE__) . '/../persistence/PersistenceManager.php';

class Controller
{
    private $urlms;
    private $activeUser;
    private $activeLab;
    
    public function __construct($aURLMS) {
        $this->urlms = $aURLMS;
    }
    
    public function login($email, $password) {
        $labs = $this->urlms->getLaboratories();
        
        foreach($this->urlms->getDirectors() as $dir) {
            if($dir->getEmail() == $email && $dir->getPassword() == $password) {
                $this->activeUser = $dir;
                return true;
            }
        }
        
        foreach($labs as $lab) {
            foreach($lab->getStaffs() as $staff) {
                if($staff->getEmail() == $email && $staff->getPassword() == $password) {
                    $this->activeUser = $staff;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public function logout() {
        $this->activeUser = null;
        PersistenceManager::writeDataToStore($this->urlms);
        
        return true;
    }
    
    //Create a new lab
    public function addLaboratory($name, $fieldOfStudy, $startDate) {
        if($this->activeUser instanceof Director) {
            $this->activeLab = $this->activeUser->addLaboratoryVia($name, $fieldOfStudy, $startDate, true, $this->urlms, $this->activeUser);
        }
        
        PersistenceManager::writeDataToStore($this->urlms);
    }
    
    // Add a new staff member to the current laboratory
    public function addStaff($name, $email, $password, $role) {
        if($this->activeUser instanceof Director) {
            new Staff($email, $password, $name, $role, [$this->activeLab]);
        }
        
        PersistenceManager::writeDataToStore($this->urlms);
    }
    
    public function createDirector($email, $password, $name) {
        $this->activeUser = $this->urlms->addDirectorVia($email, $password, $name);
        PersistenceManager::writeDataToStore($this->urlms);
    }
    
    public function setActiveLaboratory($lab){
        $this->activeLab = $lab;
    }
    public function setActiveUser($user) {
        $this->activeUser = $user;
    }
    public function getActiveUser() {
        return $this->activeUser;
    }
    public function getActiveLaboratory() {
        return $this->activeLab;
    }
}
?>