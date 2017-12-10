<?php

namespace App\Http\Controllers\URLMS;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Exception;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Staff;
use App\Http\Controllers\URLMS\Model\StaffRole;

class URLMSController extends Controller {
	protected $urlms = null;
	protected $currentUser = null;
	protected $currentLab = null;
	public function __construct() {
		$this->urlms = PersistenceController::loadModel ();
	}
	protected function isUserDirector() {
		if ($this->currentUser != null) {
			if ($this->currentUser instanceof Director)
				return true;
		}
		
		return false;
	}
	protected function isUserStaff() {
		if ($this->currentUser != null) {
			if ($this->currentUser instanceof Staff)
				return true;
		}
		
		return false;
	}
	protected function isUserNull() {
		if ($this->currentUser == null)
			return true;
		
		return false;
	}
	protected function isLabNull() {
		if ($this->currentLab == null) {
			return true;
		}
		
		return false;
	}
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
}