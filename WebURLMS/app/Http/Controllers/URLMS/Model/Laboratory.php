<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/
namespace App\Http\Controllers\URLMS\Model;
use Exception;
use RuntimeException;
use App\Http\Controllers\URLMS\Model\Supplies as Supplies;
use App\Http\Controllers\URLMS\Model\Equipment as Equipment;
use App\Http\Controllers\URLMS\Model\ProgressUpdate as ProgressUpdate;
use App\Http\Controllers\URLMS\Model\ExpenseReport as ExpenseReport;
use App\Http\Controllers\URLMS\Model\FundingAccount as FundingAccount;

class Laboratory
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $laboratorysByName = array();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Laboratory Attributes
  private $name;
  private $fieldOfStudy;
  private $startDate;
  private $active;

  //Laboratory Associations
  private $equipment;
  private $uRLMS;
  private $expenseReports;
  private $director;
  private $supplies;
  private $staffs;
  private $progressUpdates;
  private $fundingAccounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aFieldOfStudy, $aStartDate, $aActive, $aURLMS, $aDirector)
  {
    $this->fieldOfStudy = $aFieldOfStudy;
    $this->startDate = $aStartDate;
    $this->active = $aActive;
    if (!$this->setName($aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name");
    }
    $this->equipment = array();
    $didAddURLMS = $this->setURLMS($aURLMS);
    if (!$didAddURLMS)
    {
      throw new Exception("Unable to create laboratory due to uRLMS");
    }
    $this->expenseReports = array();
    $didAddDirector = $this->setDirector($aDirector);
    if (!$didAddDirector)
    {
      throw new Exception("Unable to create laboratory due to director");
    }
    $this->supplies = array();
    $this->staffs = array();
    $this->progressUpdates = array();
    $this->fundingAccounts = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    if (isset($this->name)) {
      $anOldName = $this->getName();
    }
    if (Laboratory::hasWithName($aName)) {
      return $wasSet;
    }
    $this->name = $aName;
    $wasSet = true;
    if (isset($anOldName)) {
      unset(Laboratory::$laboratorysByName[(string)$anOldName]);
    }
    Laboratory::$laboratorysByName[(string)$aName] = $this;
    return $wasSet;
  }

  public function setFieldOfStudy($aFieldOfStudy)
  {
    $wasSet = false;
    $this->fieldOfStudy = $aFieldOfStudy;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartDate($aStartDate)
  {
    $wasSet = false;
    $this->startDate = $aStartDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setActive($aActive)
  {
    $wasSet = false;
    $this->active = $aActive;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public static function getWithName($aName)
  {
    return Laboratory::$laboratorysByName[(string)$aName];
  }

  public static function hasWithName($aName)
  {
    return array_key_exists((string)$aName, Laboratory::$laboratorysByName);
  }

  public function getFieldOfStudy()
  {
    return $this->fieldOfStudy;
  }

  public function getStartDate()
  {
    return $this->startDate;
  }

  public function getActive()
  {
    return $this->active;
  }

  public function isActive()
  {
    return $this->active;
  }

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getURLMS()
  {
    return $this->uRLMS;
  }

  public function getExpenseReport_index($index)
  {
    $aExpenseReport = $this->expenseReports[$index];
    return $aExpenseReport;
  }

  public function getExpenseReports()
  {
    $newExpenseReports = $this->expenseReports;
    return $newExpenseReports;
  }

  public function numberOfExpenseReports()
  {
    $number = count($this->expenseReports);
    return $number;
  }

  public function hasExpenseReports()
  {
    $has = $this->numberOfExpenseReports() > 0;
    return $has;
  }

  public function indexOfExpenseReport($aExpenseReport)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->expenseReports as $expenseReport)
    {
      if ($expenseReport->equals($aExpenseReport))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getDirector()
  {
    return $this->director;
  }

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaff_index($index)
  {
    $aStaff = $this->staffs[$index];
    return $aStaff;
  }

  public function getStaffs()
  {
    $newStaffs = $this->staffs;
    return $newStaffs;
  }

  public function numberOfStaffs()
  {
    $number = count($this->staffs);
    return $number;
  }

  public function hasStaffs()
  {
    $has = $this->numberOfStaffs() > 0;
    return $has;
  }

  public function indexOfStaff($aStaff)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffs as $staff)
    {
      if ($staff->equals($aStaff))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgressUpdate_index($index)
  {
    $aProgressUpdate = $this->progressUpdates[$index];
    return $aProgressUpdate;
  }

  public function getProgressUpdates()
  {
    $newProgressUpdates = $this->progressUpdates;
    return $newProgressUpdates;
  }

  public function numberOfProgressUpdates()
  {
    $number = count($this->progressUpdates);
    return $number;
  }

  public function hasProgressUpdates()
  {
    $has = $this->numberOfProgressUpdates() > 0;
    return $has;
  }

  public function indexOfProgressUpdate($aProgressUpdate)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->progressUpdates as $progressUpdate)
    {
      if ($progressUpdate->equals($aProgressUpdate))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFundingAccount_index($index)
  {
    $aFundingAccount = $this->fundingAccounts[$index];
    return $aFundingAccount;
  }

  public function getFundingAccounts()
  {
    $newFundingAccounts = $this->fundingAccounts;
    return $newFundingAccounts;
  }

  public function numberOfFundingAccounts()
  {
    $number = count($this->fundingAccounts);
    return $number;
  }

  public function hasFundingAccounts()
  {
    $has = $this->numberOfFundingAccounts() > 0;
    return $has;
  }

  public function indexOfFundingAccount($aFundingAccount)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->fundingAccounts as $fundingAccount)
    {
      if ($fundingAccount->equals($aFundingAccount))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipmentVia($aName, $aQuantity)
  {
    return new Equipment($aName, $aQuantity, $this);
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $existingLaboratory = $aEquipment->getLaboratory();
    $isNewLaboratory = $existingLaboratory != null && $this !== $existingLaboratory;
    if ($isNewLaboratory)
    {
      $aEquipment->setLaboratory($this);
    }
    else
    {
      $this->equipment[] = $aEquipment;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a laboratory
    if ($this !== $aEquipment->getLaboratory())
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public function setURLMS($aURLMS)
  {
    $wasSet = false;
    if ($aURLMS == null)
    {
      return $wasSet;
    }
    
    $existingURLMS = $this->uRLMS;
    $this->uRLMS = $aURLMS;
    if ($existingURLMS != null && $existingURLMS != $aURLMS)
    {
      $existingURLMS->removeLaboratory($this);
    }
    $this->uRLMS->addLaboratory($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfExpenseReports()
  {
    return 0;
  }

  public function addExpenseReportVia($aExpense)
  {
    return new ExpenseReport($aExpense, $this);
  }

  public function addExpenseReport($aExpenseReport)
  {
    $wasAdded = false;
    if ($this->indexOfExpenseReport($aExpenseReport) !== -1) { return false; }
    $existingLaboratory = $aExpenseReport->getLaboratory();
    $isNewLaboratory = $existingLaboratory != null && $this !== $existingLaboratory;
    if ($isNewLaboratory)
    {
      $aExpenseReport->setLaboratory($this);
    }
    else
    {
      $this->expenseReports[] = $aExpenseReport;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeExpenseReport($aExpenseReport)
  {
    $wasRemoved = false;
    //Unable to remove aExpenseReport, as it must always have a laboratory
    if ($this !== $aExpenseReport->getLaboratory())
    {
      unset($this->expenseReports[$this->indexOfExpenseReport($aExpenseReport)]);
      $this->expenseReports = array_values($this->expenseReports);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addExpenseReportAt($aExpenseReport, $index)
  {  
    $wasAdded = false;
    if($this->addExpenseReport($aExpenseReport))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfExpenseReports()) { $index = $this->numberOfExpenseReports() - 1; }
      array_splice($this->expenseReports, $this->indexOfExpenseReport($aExpenseReport), 1);
      array_splice($this->expenseReports, $index, 0, array($aExpenseReport));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveExpenseReportAt($aExpenseReport, $index)
  {
    $wasAdded = false;
    if($this->indexOfExpenseReport($aExpenseReport) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfExpenseReports()) { $index = $this->numberOfExpenseReports() - 1; }
      array_splice($this->expenseReports, $this->indexOfExpenseReport($aExpenseReport), 1);
      array_splice($this->expenseReports, $index, 0, array($aExpenseReport));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addExpenseReportAt($aExpenseReport, $index);
    }
    return $wasAdded;
  }

  public function setDirector($aDirector)
  {
    $wasSet = false;
    if ($aDirector == null)
    {
      return $wasSet;
    }
    
    $existingDirector = $this->director;
    $this->director = $aDirector;
    if ($existingDirector != null && $existingDirector != $aDirector)
    {
      $existingDirector->removeLaboratory($this);
    }
    $this->director->addLaboratory($this);
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupplyVia($aName, $aQuantity)
  {
    return new Supplies($aName, $aQuantity, $this);
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $existingLaboratory = $aSupply->getLaboratory();
    $isNewLaboratory = $existingLaboratory != null && $this !== $existingLaboratory;
    if ($isNewLaboratory)
    {
      $aSupply->setLaboratory($this);
    }
    else
    {
      $this->supplies[] = $aSupply;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    //Unable to remove aSupply, as it must always have a laboratory
    if ($this !== $aSupply->getLaboratory())
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfStaffs()
  {
    return 0;
  }

  public function addStaff($aStaff)
  {
    $wasAdded = false;
    if ($this->indexOfStaff($aStaff) !== -1) { return false; }
    $this->staffs[] = $aStaff;
    if ($aStaff->indexOfLaboratory($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aStaff->addLaboratory($this);
      if (!$wasAdded)
      {
        array_pop($this->staffs);
      }
    }
    return $wasAdded;
  }

  public function removeStaff($aStaff)
  {
    $wasRemoved = false;
    if ($this->indexOfStaff($aStaff) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfStaff($aStaff);
    unset($this->staffs[$oldIndex]);
    if ($aStaff->indexOfLaboratory($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aStaff->removeLaboratory($this);
      if (!$wasRemoved)
      {
        $this->staffs[$oldIndex] = $aStaff;
        ksort($this->staffs);
      }
    }
    $this->staffs = array_values($this->staffs);
    return $wasRemoved;
  }

  public function addStaffAt($aStaff, $index)
  {  
    $wasAdded = false;
    if($this->addStaff($aStaff))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffAt($aStaff, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaff($aStaff) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffAt($aStaff, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public function addProgressUpdateVia($aTitle, $aReport, $aStaff)
  {
    return new ProgressUpdate($aTitle, $aReport, $this, $aStaff);
  }

  public function addProgressUpdate($aProgressUpdate)
  {
    $wasAdded = false;
    if ($this->indexOfProgressUpdate($aProgressUpdate) !== -1) { return false; }
    $existingLaboratory = $aProgressUpdate->getLaboratory();
    $isNewLaboratory = $existingLaboratory != null && $this !== $existingLaboratory;
    if ($isNewLaboratory)
    {
      $aProgressUpdate->setLaboratory($this);
    }
    else
    {
      $this->progressUpdates[] = $aProgressUpdate;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeProgressUpdate($aProgressUpdate)
  {
    $wasRemoved = false;
    //Unable to remove aProgressUpdate, as it must always have a laboratory
    if ($this !== $aProgressUpdate->getLaboratory())
    {
      unset($this->progressUpdates[$this->indexOfProgressUpdate($aProgressUpdate)]);
      $this->progressUpdates = array_values($this->progressUpdates);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addProgressUpdateAt($aProgressUpdate, $index)
  {  
    $wasAdded = false;
    if($this->addProgressUpdate($aProgressUpdate))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveProgressUpdateAt($aProgressUpdate, $index)
  {
    $wasAdded = false;
    if($this->indexOfProgressUpdate($aProgressUpdate) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfProgressUpdates()) { $index = $this->numberOfProgressUpdates() - 1; }
      array_splice($this->progressUpdates, $this->indexOfProgressUpdate($aProgressUpdate), 1);
      array_splice($this->progressUpdates, $index, 0, array($aProgressUpdate));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addProgressUpdateAt($aProgressUpdate, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfFundingAccounts()
  {
    return 0;
  }

  public function addFundingAccountVia($aCurrentBalance, $aAccountNumber)
  {
    return new FundingAccount($aCurrentBalance, $aAccountNumber, $this);
  }

  public function addFundingAccount($aFundingAccount)
  {
    $wasAdded = false;
    if ($this->indexOfFundingAccount($aFundingAccount) !== -1) { return false; }
    $existingLaboratory = $aFundingAccount->getLaboratory();
    $isNewLaboratory = $existingLaboratory != null && $this !== $existingLaboratory;
    if ($isNewLaboratory)
    {
      $aFundingAccount->setLaboratory($this);
    }
    else
    {
      $this->fundingAccounts[] = $aFundingAccount;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFundingAccount($aFundingAccount)
  {
    $wasRemoved = false;
    //Unable to remove aFundingAccount, as it must always have a laboratory
    if ($this !== $aFundingAccount->getLaboratory())
    {
      unset($this->fundingAccounts[$this->indexOfFundingAccount($aFundingAccount)]);
      $this->fundingAccounts = array_values($this->fundingAccounts);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFundingAccountAt($aFundingAccount, $index)
  {  
    $wasAdded = false;
    if($this->addFundingAccount($aFundingAccount))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccounts()) { $index = $this->numberOfFundingAccounts() - 1; }
      array_splice($this->fundingAccounts, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccounts, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFundingAccountAt($aFundingAccount, $index)
  {
    $wasAdded = false;
    if($this->indexOfFundingAccount($aFundingAccount) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFundingAccounts()) { $index = $this->numberOfFundingAccounts() - 1; }
      array_splice($this->fundingAccounts, $this->indexOfFundingAccount($aFundingAccount), 1);
      array_splice($this->fundingAccounts, $index, 0, array($aFundingAccount));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFundingAccountAt($aFundingAccount, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    unset(Laboratory::$laboratorysByName[(string)$this->getName()]);
    foreach ($this->equipment as $aEquipment)
    {
      $aEquipment->delete();
    }
    $placeholderURLMS = $this->uRLMS;
    $this->uRLMS = null;
    $placeholderURLMS->removeLaboratory($this);
    foreach ($this->expenseReports as $aExpenseReport)
    {
      $aExpenseReport->delete();
    }
    $placeholderDirector = $this->director;
    $this->director = null;
    $placeholderDirector->removeLaboratory($this);
    foreach ($this->supplies as $aSupply)
    {
      $aSupply->delete();
    }
    while (count($this->staffs) > 0)
    {
      $aStaff = $this->staffs[count($this->staffs) - 1];
      $aStaff->delete();
      unset($this->staffs[$this->indexOfStaff($aStaff)]);
      $this->staffs = array_values($this->staffs);
    }
    
    foreach ($this->progressUpdates as $aProgressUpdate)
    {
      $aProgressUpdate->delete();
    }
    foreach ($this->fundingAccounts as $aFundingAccount)
    {
      $aFundingAccount->delete();
    }
  }

}
?>