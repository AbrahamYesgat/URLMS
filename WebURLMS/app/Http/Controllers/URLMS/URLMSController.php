<?php

namespace App\Http\Controllers\URLMS;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Staff;

/**
 * Base class for App Controller
 */
class URLMSController extends Controller {
	protected $urlms = null;
	protected $currentUser = null;
	protected $currentLab = null;
	public function __construct() {
		$this->urlms = PersistenceController::loadModel ();
	}
	/**
	 * Returns if current user is director or not
	 * @return boolean true if director, false otherwise
	 */
	protected function isUserDirector() {
		if ($this->currentUser != null) {
			if ($this->currentUser instanceof Director)
				return true;
		}
		
		return false;
	}
	/**
	 * Returns if current user is staff or not
	 * @return boolean true if staff, false otherwise
	 */
	protected function isUserStaff() {
		if ($this->currentUser != null) {
			if ($this->currentUser instanceof Staff)
				return true;
		}
		
		return false;
	}
	/**
	 * Returns if current user is null (i.e., no current user)
	 * @return boolean true if current user is null, false otherwise
	 */
	protected function isUserNull() {
		if ($this->currentUser == null)
			return true;
		
		return false;
	}
	/**
	 * Returns if current lab is null (i.e., no current lab)
	 * @return boolean true if current lab is null, false otherwise
	 */
	protected function isLabNull() {
		if ($this->currentLab == null) {
			return true;
		}
		
		return false;
	}
	/**
	 * Loads values from session variables and update variables accordingly
	 * @param Request $request 
	 */
	protected function updateCurrent(Request $request) {
		if ($request->session ()->get ( 'logged-in' )) {
			foreach ( $this->urlms->getDirectors () as $dir ) {
				if ($dir->getEmail () == $request->session ()->get ( 'email' )) {
					$this->currentUser = $dir;
					break;
				}
			}
			
			if ($this->urlms->numberOfLaboratories () != 0 && $this->currentUser == null) {
				foreach ( $this->urlms->getLaboratories () as $lab ) {
					foreach ( $lab->getStaffs () as $staff ) {
						if ($staff->getEmail () == $request->session ()->get ( 'email' )) {
							$this->currentUser = $staff;
							break;
						}
					}
				}
			}
			
			foreach ( $this->urlms->getLaboratories () as $lab ) {
				if($lab->getName() == $request->session()->get('labName')) {
                    $this->currentLab = $lab;
                    break;
                }
            }
        }
    }
    /**
     * "Clean" strings to protect from potential XSS attacks by escaping html characters with quotes
     * @param String $s Input string
     * @return String Sanitized string
     */
    protected function cleanString($s) {
    		return htmlspecialchars($s, ENT_QUOTES, 'UTF-8');
    }
}