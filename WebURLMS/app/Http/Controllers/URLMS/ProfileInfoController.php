<?php

namespace App\Http\Controllers\URLMS;

use Illuminate\Http\Request;;
use App\Http\Controllers\URLMS\Model\Director;
use App\Http\Controllers\URLMS\Model\Staff;
use App\Http\Controllers\URLMS\Model\StaffRole;

class ProfileInfoController extends URLMSController {
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
	public function updateProfile(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		$name = $request->input ( 'name' );
		$email = $request->input ( 'email' );
		$role = $request->input ( 'role' );
		
		$this->currentUser->setName ( $name );
		$this->currentUser->setEmail ( $email );
		
		if ($this->isUserStaff ()) {
			$this->currentUser->setStaffRole ( ($role == 0) ? StaffRole::ResearchAssistant : StaffRole::ResearchAssociate );
		}
		
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
	public function getCurrentLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No lab could be found' 
			] );
		
		return response ()->json ( [ 
				'status' => true,
				'name' => $this->currentLab->getName(), 'field' => $this->currentLab->getFieldOfStudy(), 'date' => $this->currentLab->getStartDate(), 'active' => $this->currentLab->getActive()]);
    }
}