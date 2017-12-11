<?php

namespace App\Http\Controllers\URLMS;

use Illuminate\Http\Request;

;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Staff;
use App\Http\Controllers\URLMS\Model\StaffRole;

/**
 * Manages user interaction and current session status
 */
class ProfileInfoController extends URLMSController {
	/**
	 * Get current connected user
	 * 
	 * @param Request $request
	 * @return ['status': either response is valid,
	 *         'message': response message,
	 *         'director': (success) if logged in user is director,
	 *         'name' : (success) name of logged in user,
	 *         'email' : (success) email of logged in user,
	 *         'role' : (success) role of Staff (null for director)]
	 */
	public function getCurrentUser(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		if ($this->currentUser instanceof Director) {
			return response ()->json ( [ 
					'status' => true,
					'director' => true,
					'name' => $this->currentUser->getName (),
					'email' => $this->currentUser->getEmail (),
					'role' => null 
			] );
		}
		
		if ($this->currentUser instanceof Staff) {
			return response ()->json ( [ 
					'status' => true,
					'director' => false,
					'name' => $this->currentUser->getName (),
					'email' => $this->currentUser->getEmail (),
					'role' => ($this->currentUser->getStaffRole () == StaffRole::ResearchAssociate) ? 'Research Associate' : 'Research Assistant' 
			] );
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Unknown error' 
		] );
	}
	/**
	 * Update profile password (checks if previous password is good)
	 * 
	 * @param Request $request
	 *        	['previousPassword': previous password,
	 *        	'newPassword': password to update to]
	 * @return ['status': if password was successfully updated or not,
	 *         'message': response message]
	 */
	public function updatePassword(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		$previousPassword = $request->input ( 'previousPassword' );
		$newPassword = $request->input ( 'newPassword' );
		
		if ($this->currentUser->getPassword () == $previousPassword) {
			$this->currentUser->setPassword ( $newPassword );
			
			PersistenceController::saveModel ( $this->urlms );
			return response ()->json ( [ 
					'status' => true,
					'Password has been successfully modified' 
			] );
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Bad previous password' 
		] );
	}
	/**
	 * Update profile with given info
	 * 
	 * @param Request $request
	 *        	['name': new name,
	 *        	'email': new email]
	 * @return ['status': either profile was successfully updated or not,
	 *         'message': response message]
	 */
	public function updateProfile(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		$name = $request->input ( 'name' );
		$email = $request->input ( 'email' );
		
		$this->currentUser->setName ( $name );
		
		foreach ( $this->urlms->getLaboratories () as $lab ) {
			foreach ( $lab->getStaffs () as $staff ) {
				if ($staff->getEmail () == $email) {
					return response ()->json ( [ 
							'status' => false,
							'message' => 'Another user already has this email' 
					] );
				}
			}
		}
		
		foreach ( $this->urlms->getDirectors () as $dir ) {
			if ($dir->getEmail () == $email) {
				return response ()->json ( [ 
						'status' => false,
						'message' => 'Another user already has this email' 
				] );
			}
		}
		
		$this->currentUser->setEmail ( $email );
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Profile has been successfully updated' 
		] );
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Could not update profile' 
		] );
	}
	/**
	 * Get current lab
	 * 
	 * @param Request $request
	 * @return ['status': if theres a lab or not,
	 *         'message': response message,
	 *         'name': (success) name of the lab,
	 *         'field': (success) field of the lab,
	 *         'date': (success) start date of the lab,
	 *         'active': (success) if lab is 'Active' or 'Unactive']
	 */
	public function getCurrentLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No lab could be found' 
			] );
		
		return response ()->json ( [ 
				'status' => true,
				'name' => $this->currentLab->getName (),
				'field' => $this->currentLab->getFieldOfStudy(), 'date' => $this->currentLab->getStartDate(), 'active' => $this->currentLab->getActive()]);
    }
}