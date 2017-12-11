<?php

namespace App\Http\Controllers\URLMS;

use Illuminate\Http\Request;
use Exception;

/**
 * Controller for authentication
 */
class AuthenticationController extends URLMSController {
	/**
	 * Login as staff or director
	 * @param Request $request 
	 * ['email': login email, 
	 * 'password': password]
	 * @return
	 * ['status': either login succeeded or not]
	 */
	public function login(Request $request) {
		$this->updateCurrent ( $request );
		$email = $this->cleanString($request->input ( 'email' ));
		$password = $request->input ( 'password' );
		
		$labs = $this->urlms->getLaboratories ();
		
		//Check all directors
		foreach ( $this->urlms->getDirectors () as $dir ) {
			if ($dir->getEmail () == $email && $dir->getPassword () == $password) {
				$this->currentUser = $dir;
				
				$request->session ()->put ( 'logged-in', true );
				$request->session ()->put ( 'email', $email );
				$request->session ()->put ( 'director', true );
				
				return response ()->json ( [ 
						'status' => true 
				] );
			}
		}
		
		//Check in all labs
		if ($this->urlms->numberOfLaboratories () != 0) {
			foreach ( $labs as $lab ) {
				//and each staff
				foreach ( $lab->getStaffs () as $staff ) {
					if ($staff->getEmail () == $email && $staff->getPassword () == $password) {
						$this->currentUser = $staff;
						
						//Set last login for staff
						date_default_timezone_set ( 'America/New_York' );
						$staff->setLastLogin ( date ( 'm/d/Y - h:ia' ) );
						PersistenceController::saveModel ( $this->urlms );
						
						$request->session ()->put ( 'logged-in', true );
						$request->session ()->put ( 'email', $email );
						$request->session ()->put ( 'director', false );
						
						return response ()->json ( [ 
								'status' => true 
						] );
					}
				}
			}
		}
		
		return response ()->json ( [ 
				'status' => false 
		] );
	}
	/**
	 * Register a director account
	 * @param Request $request 
	 * ['name': name of the director
	 * 'email': email of registration]
	 * 'password': password
	 * @return
	 * ['status': either registration succeeded or not,
	 * 'message': response message (for error),
	 * 'name': (success) name of registered user]
	 */
	public function register(Request $request) {
		$this->updateCurrent ( $request );
		$name = $this->cleanString($request->input ( 'name' ));
		$email = $this->cleanString($request->input ( 'email' ));
		$password = $request->input ( 'password' );
		
		if ($name == null || $name == '' || $email == null || $email == '' || $password == null || $password == '') {
			return response ()->json ( [ 
					'status' => false,
					'message' => 'Empty string detected' 
			] );
		}
		
		foreach ( $this->urlms->getDirectors () as $dir ) {
			if ($dir->getEmail () == $email) {
				return response ()->json ( [ 
						'status' => false,
						'message' => 'A director is already using this email' 
				] );
			}
		}
		
		try {
			$this->currentUser = $this->urlms->addDirectorVia ( $email, $password, $name );
			PersistenceController::saveModel ( $this->urlms );
		} catch ( Exception $e ) {
			return response ()->json ( [ 
					'status' => false,
					'message' => $e->getMessage () 
			] );
		}
		
		$request->session ()->put ( 'logged-in', true );
		$request->session ()->put ( 'email', $email );
		$request->session ()->put ( 'director', true );
		
		return response ()->json ( [ 
				'status' => true,
				'name' => $name 
		] );
	}
	/**
	 * Logout from system
	 * @param Request $request 
	 * @return ['status': true if logout succeeded]
	 */
	public function logout(Request $request) {
		$this->updateCurrent ( $request );
		PersistenceController::saveModel($this->urlms);
        $request->session()->forget(['logged-in', 'email', 'labName', 'director']);
        
        return response()->json(['status' => true]);
    }
}