<?php 
$curr_dir = dirname(__FILE__);

foreach(glob($curr_dir . '/../model/*.php') as $file){
    require_once $file;
}

class Controller
{
    private $urlms;
    
    public function __construct($aURLMS) {
        $this->urlms = $aURLMS;
    }
    
    public function login($email, $password) {
        $labs = $this->urlms->getLaboratories();
        
        if ($this->urlms->numberOfLaboratories() == 0) {
            foreach($this->urlms->getDirectors() as $dir) {
                if($dir->getEmail() == $email && $dir->getPassword() == $password) {
                    return TRUE;
                }
            }
        } 
        
        else {
            foreach($labs as $lab) {
                foreach($this->urlms->getStaffs() as $staff) {
                    if($staff->getEmail() == $email && $staff->getPassword() == $password) {
                        return TRUE;
                    }
                }
            }
        }
        
        return FALSE;
    }
}
?>