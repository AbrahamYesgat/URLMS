<?php 
$curr_dir = dirname(__FILE__);

foreach(glob($curr_dir . '/../model/*.php') as $file){
    require_once $file;
}

require_once $curr_dir . '/../persistence/PersistenceManager.php';

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
        
        if ($this->urlms->numberOfLaboratories() == 0) {
            foreach($this->urlms->getDirectors() as $dir) {
                if($dir->getEmail() == $email && $dir->getPassword() == $password) {
                    return true;
                }
            }
        } 
        
        else {
            foreach($labs as $lab) {
                foreach($this->urlms->getStaffs() as $staff) {
                    if($staff->getEmail() == $email && $staff->getPassword() == $password) {
                        return true;
                    }
                }
            }
        }
        
        return FALSE;
    }
    
    public function logout() {
        $activeUser = null;
        PersistenceManager::writeDataToStore($this->urlms);
        
        return true;
    }
    
    //Create a new lab
    public function addLaboratory($name, $fieldOfStudy, $startDate) {
        if($activeUser instanceof Director) {
            $activeLab = $activeUser->addLaboratoryVia($name, $fieldOfStudy, $startDate, true, $this->urlms);
        }
        
        return false;
    }
    
    // Add a new staff member to the current laboratory
    public function addStaff($name, $email, $password, $role) {
        if($activeUser instanceof Director) {
            $newMember = new Staff($name, $email, $password, [$activeLab]);
        }
        
        return true;
    }
    
    public function createDirector($email, $password, $name) {
        URLMS::getInstance()->addDirectorVia($email, $password, $name);
        PersistenceManager::writeDataToStore($this->urlms);
    }
    
    public function setActiveLaboratory($lab){
        $activeLab = $lab;
    }
    public function setActiveUser($user) {
        $activeUser = $user;
    }
    public function getActiveUser() {
        return $activeUser;
    }
    public function getActiveLaboratory() {
        return $activeLab;
    }
}
?>