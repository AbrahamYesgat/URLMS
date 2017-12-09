<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/
namespace App\Http\Controllers\URLMS\Model;
use Exception;

class ExpenseReport
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ExpenseReport Attributes
  private $expense;
  private $amount;
  private $date;

  //Autounique Attributes
  private $id;

  //ExpenseReport Associations
  private $laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aExpense, $aAmount, $aDate, $aLaboratory)
  {
    $this->expense = $aExpense;
    $this->date = $aDate;
    $this->amount = $aAmount;
    $this->id = self::$nextId++;
    $didAddLaboratory = $this->setLaboratory($aLaboratory);
    if (!$didAddLaboratory)
    {
      throw new Exception("Unable to create expenseReport due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setExpense($aExpense)
  {
    $wasSet = false;
    $this->expense = $aExpense;
    $wasSet = true;
    return $wasSet;
  }

  public function setAmount($aAmount)
  {
    $wasSet = false;
    $this->amount = $aAmount;
    $wasSet = true;
    return $wasSet;
  }
  
  public function setDate($aDate)
  {
  	$wasSet = false;
  	$this->date = $aDate;
  	$wasSet = true;
  	return $wasSet;
  }

  public function getExpense()
  {
    return $this->expense;
  }

  public function getAmount()
  {
    return $this->amount;
  }
  
  public function getDate()
  {
  	return $this->date;
  }

  public function getId()
  {
    return $this->id;
  }

  public function getLaboratory()
  {
    return $this->laboratory;
  }

  public function setLaboratory($aLaboratory)
  {
    $wasSet = false;
    if ($aLaboratory == null)
    {
      return $wasSet;
    }
    
    $existingLaboratory = $this->laboratory;
    $this->laboratory = $aLaboratory;
    if ($existingLaboratory != null && $existingLaboratory != $aLaboratory)
    {
      $existingLaboratory->removeExpenseReport($this);
    }
    $this->laboratory->addExpenseReport($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderLaboratory = $this->laboratory;
    $this->laboratory = null;
    $placeholderLaboratory->removeExpenseReport($this);
  }

}
?>