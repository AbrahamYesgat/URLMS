<?php

namespace App\Http\Controllers\URLMS;

use Illuminate\Http\Request;
use Exception;
use App\Http\Controllers\URLMS\Model\Staff;
use App\Http\Controllers\URLMS\Model\StaffRole;

class MainController extends URLMSController {
	/*
	 * Labs
	 */
	public function addLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$field = $this->cleanString ( $request->input ( 'field' ) );
		$date = $this->cleanString ( $request->input ( 'date' ) );
		$active = $request->input ( 'active' );
		
		if ($active != 'Active' && $active != 'Unactive') {
			return response ()->json ( [ 
					'status' => false,
					'message' => 'Invalid active input' 
			] );
		}
		
		foreach ( $this->currentUser->getLaboratories () as $lab ) {
			if ($name == $lab->getName ()) {
				return response ()->json ( [ 
						'status' => false,
						'message' => 'A lab already exists with this name' 
				] );
			}
		}
		
		try {
			$this->currentUser->addLaboratoryVia ( $name, $field, $date, $active, $this->urlms, $this->currentUser );
		} catch ( Exception $e ) {
			return response ()->json ( [ 
					'status' => false,
					'message' => $e->getMessage () 
			] );
		}
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Lab ' . $name . ' has been successfully added' 
		] );
	}
	public function removeLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		
		try {
			foreach ( $this->currentUser->getLaboratories () as $lab ) {
				if ($name == $lab->getName ()) {
					$lab->delete ();
					PersistenceController::saveModel ( $this->urlms );
					return response ()->json ( [ 
							'status' => true,
							'message' => 'Lab ' . $name . ' has been successfully deleted' 
					] );
				}
			}
		} catch ( Exception $e ) {
			return response ()->json ( [ 
					'status' => false,
					'message' => $e->getMessage () 
			] );
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Unknown error' 
		] );
	}
	public function clearLabs(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		try {
			foreach ( $this->currentUser->getLaboratories () as $lab ) {
				$lab->delete ();
			}
		} catch ( Exception $e ) {
			return response ()->json ( [ 
					'status' => false,
					'message' => $e->getMessage () 
			] );
		}
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'All labs have been successfully deleted' 
		] );
	}
	public function enterLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		
		if ($this->currentUser != null) {
			foreach ( $this->currentUser->getLaboratories () as $lab ) {
				if ($name == $lab->getName ()) {
					$this->currentLab = $lab;
					$request->session ()->put ( 'labName', $name );
					return response ()->json ( [ 
							'status' => true 
					] );
				}
			}
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Could not find lab' 
		] );
	}
	public function updateLab(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$newName = $this->cleanString ( $request->input ( 'newName' ) );
		$field = $this->cleanString ( $request->input ( 'field' ) );
		$active = $request->input ( "active" );
		
		$this->currentLab->setName ( $newName );
		$this->currentLab->setActive ( $active );
		$this->currentLab->setFieldOfStudy ( $field );
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Lab ' . $newName . ' has been successfully updated' 
		] );
	}
	public function getLabs(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user could be found' 
			] );
		
		$modelLabs = $this->currentUser->getLaboratories ();
		$resLabs = [ ];
		
		foreach ( $modelLabs as $lab ) {
			$resLabs [] = [ 
					'name' => $lab->getName (),
					'field' => $lab->getFieldOfStudy (),
					'date' => $lab->getStartDate (),
					'active' => $lab->getActive () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'labs' => $resLabs 
		] );
	}
	
	/*
	 * Staff
	 */
	public function getStaff(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$modelStaff = $this->currentLab->getStaffs ();
		$resStaff = [ ];
		
		foreach ( $modelStaff as $staff ) {
			$resStaff [] = [ 
					'name' => $staff->getName (),
					'email' => $staff->getEmail (),
					'role' => ($staff->getStaffRole () == StaffRole::ResearchAssistant) ? 'Research Assistant' : 'Research Associate',
					'lastLogIn' => ($staff->getLastLogin () == null) ? 'Never' : $staff->getLastLogin () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'staff' => $resStaff 
		] );
	}
	public function addStaff(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$email = $this->cleanString ( $request->input ( 'email' ) );
		$role = ($request->input ( 'role' ) == 0) ? StaffRole::ResearchAssociate : StaffRole::ResearchAssistant;
		$password = $request->input ( 'password' );
		
		foreach ( $this->urlms->getLaboratories () as $lab ) {
			foreach ( $lab->getStaffs () as $staff ) {
				if ($staff->getEmail () == $email) {
					if ($staff->getName () == $name && $staff->getStaffRole () == $role && $staff->getPassword () == $password) {
						$staff->addLaboratory ( $lab );
						
						PersistenceController::saveModel ( $this->urlms );
						return response ()->json ( [ 
								'status' => true,
								'message' => 'Staff member ' . $name . ' has been successfully added' 
						] );
					} else {
						return response ()->json ( [ 
								'status' => false,
								'message' => 'Staff already exists but incorrect information' 
						] );
					}
				}
			}
		}
		
		foreach ( $this->urlms->getDirectors () as $dir ) {
			if ($dir->getEmail () == $email) {
				return response ()->json ( [ 
						'status' => false,
						'message' => 'Director already has this email' 
				] );
			}
		}
		
		new Staff ( $email, $password, $name, $role, [ 
				$this->currentLab 
		] );
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Staff member ' . $name . ' has been successfully added' 
		] );
	}
	public function clearStaff(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		foreach ( $this->currentLab->getStaffs () as $staff ) {
			$staff->delete ();
		}
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'All staff members have been successfully deleted' 
		] );
	}
	public function removeStaff(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$name = '';
		$email = $this->cleanString ( $request->input ( 'email' ) );
		
		foreach ( $this->currentLab->getStaffs () as $staff ) {
			if ($staff->getEmail () == $email) {
				$name = $staff->getName ();
				$staff->delete ();
				break;
			}
		}
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Staff member ' . $name . ' has been successfully deleted' 
		] );
	}
	public function modifyStaff(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$prevEmail = $this->cleanString ( $request->input ( 'prevEmail' ) );
		$email = $this->cleanString ( $request->input ( 'email' ) );
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$role = ($request->input ( 'role' ) == 0) ? StaffRole::ResearchAssociate : StaffRole::ResearchAssistant;
		$password = $request->input ( 'password' );
		
		foreach ( $this->currentLab->getStaffs () as $staff ) {
			if ($staff->getEmail () == $prevEmail) {
				$staff->setEmail ( $email );
				$staff->setStaffRole ( $role );
				$staff->setName ( $name );
				
				if ($password != '') {
					$staff->setPassword ( $password );
				}
				
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Staff member ' . $name . ' has been successfully modified' 
				] );
			}
		}
	}
	
	/*
	 * Supplies
	 */
	public function getSupplies(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$modelSupplies = $this->currentLab->getSupplies ();
		$resSupplies = [ ];
		
		foreach ( $modelSupplies as $supply ) {
			$resSupplies [] = [ 
					'name' => $supply->getName (),
					'qty' => $supply->getQuantity () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'supplies' => $resSupplies 
		] );
	}
	public function clearSupplies(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		foreach ( $this->currentLab->getSupplies () as $supply ) {
			$supply->delete ();
		}
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'All supplies have been successfully deleted' 
		] );
	}
	public function addSupplies(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$qty = ( int ) $request->input ( 'qty' );
		
		foreach ( $this->currentLab->getSupplies () as $supply ) {
			if ($supply->getName () == $name) {
				$supply->setQuantity ( $supply->getQuantity () + $qty );
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Supply ' . $name . ' has been successfully added' 
				] );
			}
		}
		
		$this->currentLab->addSupplyVia ( $name, $qty );
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Supply ' . $name . ' has been successfully added' 
		] );
	}
	public function modifySupplies(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$qty = ( int ) $request->input ( 'qty' );
		
		foreach ( $this->currentLab->getSupplies () as $supply ) {
			if ($supply->getName () == $name) {
				$supply->setQuantity ( $qty );
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Supply ' . $name . ' has been successfully modified' 
				] );
			}
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Supply could not be found' 
		] );
	}
	public function removeSupplies(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		
		foreach ( $this->currentLab->getSupplies () as $supply ) {
			if ($supply->getName () == $name) {
				$supply->delete ();
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Supply ' . $name . ' has been successfully deleted' 
				] );
			}
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Supply could not be found' 
		] );
	}
	
	/*
	 * Equipment
	 */
	public function getEquipment(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$modelEquipment = $this->currentLab->getEquipment ();
		$resEquipment = [ ];
		
		foreach ( $modelEquipment as $equipment ) {
			$resEquipment [] = [ 
					'name' => $equipment->getName (),
					'qty' => $equipment->getQuantity () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'equipment' => $resEquipment 
		] );
	}
	public function clearEquipment(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		foreach ( $this->currentLab->getEquipment () as $equipment ) {
			$equipment->delete ();
		}
		
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'All equipments have been successfully deleted' 
		] );
	}
	public function addEquipment(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$qty = ( int ) $request->input ( 'qty' );
		
		foreach ( $this->currentLab->getEquipment () as $equipment ) {
			if ($equipment->getName () == $name) {
				$equipment->setQuantity ( $equipment->getQuantity () + $qty );
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Equipment ' . $name . ' has been successfully added' 
				] );
			}
		}
		
		$this->currentLab->addEquipmentVia ( $name, $qty );
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Equipment ' . $name . ' has been successfully added' 
		] );
	}
	public function modifyEquipment(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		$qty = ( int ) $request->input ( 'qty' );
		
		foreach ( $this->currentLab->getEquipment () as $equipment ) {
			if ($equipment->getName () == $name) {
				$equipment->setQuantity ( $qty );
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Equipment ' . $name . ' has been successfully modified' 
				] );
			}
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Equipment could not be found' 
		] );
	}
	public function removeEquipment(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$name = $this->cleanString ( $request->input ( 'name' ) );
		
		foreach ( $this->currentLab->getEquipment () as $equipment ) {
			if ($equipment->getName () == $name) {
				$equipment->delete ();
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Equipment ' . $name . ' has been successfully deleted' 
				] );
			}
		}
		
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Equipment could not be found' 
		] );
	}
	
	/*
	 * Weekly progress reports
	 */
	public function getWeeklyProgress(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$modelWeeklyProgress = $this->currentLab->getProgressUpdates ();
		$resWeeklyProgress = [ ];
		
		foreach ( $modelWeeklyProgress as $weeklyProgress ) {
			$resWeeklyProgress [] = [ 
					'title' => $weeklyProgress->getTitle (),
					'report' => $weeklyProgress->getReport (),
					'author' => $weeklyProgress->getStaff ()->getName () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'reports' => $resWeeklyProgress 
		] );
	}
	public function addWeeklyProgress(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserStaff ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not staff member' 
			] );
		
		$title = $this->cleanString ( $request->input ( 'title' ) );
		$report = $this->cleanString ( $request->input ( 'report' ) );
		
		$this->currentLab->addProgressUpdateVia ( $title, $report, $this->currentUser );
		PersistenceController::saveModel ( $this->urlms );
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Weekly report has been successfully added' 
		] );
	}
	
	/*
	 * Expenses
	 */
	public function getExpenses(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$modelExpenses = $this->currentLab->getExpenseReports ();
		$resExpenses = [ ];
		
		foreach ( $modelExpenses as $expense ) {
			$resExpenses [] = [ 
					'description' => $expense->getExpense (),
					'amount' => $expense->getAmount (),
					'date' => $expense->getDate () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'expenses' => $resExpenses 
		] );
	}
	public function addExpenses(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		$description = $this->cleanString ( $request->input ( 'description' ) );
		$amount = ( float ) $request->input ( 'amount' );
		$date = $this->cleanString ( $request->input ( 'date' ) );
		
		$this->currentLab->addExpenseReportVia ( $description, $amount, $date );
		PersistenceController::saveModel ( $this->urlms );
		
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Expense has been successfully added' 
		] );
	}
	
	/*
	 * Funding accounts
	 */
	public function getFundingAccounts(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$modelFundingAccounts = $this->currentLab->getFundingAccounts ();
		$resFundingAccounts = [ ];
		
		foreach ( $modelFundingAccounts as $funding ) {
			$resFundingAccounts [] = [ 
					'number' => $funding->getAccountNumber (),
					'funds' => $funding->getCurrentBalance () 
			];
		}
		
		return response ()->json ( [ 
				'status' => true,
				'fundings' => $resFundingAccounts 
		] );
	}
	public function addFundingAccount(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$accountNumber = $this->cleanString ( $request->input ( 'number' ) );
		$funds = ( float ) $request->input ( 'funds' );
		
		foreach ( $this->currentLab->getFundingAccounts () as $funding ) {
			if ($funding->getAccountNumber () == $accountNumber) {
				return response ()->json ( [ 
						'status' => false,
						'message' => 'Account number already exists' 
				] );
			}
		}
		
		$this->currentLab->addFundingAccountVia ( $funds, $accountNumber );
		PersistenceController::saveModel ( $this->urlms );
		
		return response ()->json ( [ 
				'status' => true,
				'message' => 'Funding account ' . $accountNumber . ' has been successfully added' 
		] );
	}
	public function modifyFundingAccount(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$accountNumber = $this->cleanString ( $request->input ( 'number' ) );
		$funds = ( float ) $request->input ( 'funds' );
		
		foreach ( $this->currentLab->getFundingAccounts () as $funding ) {
			if ($funding->getAccountNumber () == $accountNumber) {
				$funding->setCurrentBalance ( $funds );
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Funding account ' . $accountNumber . ' has been successfully modified' 
				] );
			}
		}
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Account number could not be found' 
		] );
	}
	public function removeFundingAccount(Request $request) {
		$this->updateCurrent ( $request );
		
		if ($this->isUserNull () || $this->isLabNull ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'No user or lab could be found' 
			] );
		
		if (! $this->isUserDirector ())
			return response ()->json ( [ 
					'status' => false,
					'message' => 'User is not director' 
			] );
		
		$accountNumber = $this->cleanString ( $request->input ( 'number' ) );
		
		foreach ( $this->currentLab->getFundingAccounts () as $funding ) {
			if ($funding->getAccountNumber () == $accountNumber) {
				$funding->delete ();
				PersistenceController::saveModel ( $this->urlms );
				return response ()->json ( [ 
						'status' => true,
						'message' => 'Funding account ' . $accountNumber . ' has been successfully deleted' 
				] );
			}
		}
		return response ()->json ( [ 
				'status' => false,
				'message' => 'Account number could not be found' 
		] );
	}
}