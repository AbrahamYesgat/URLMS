<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

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
  private $expensePeriod;

  //Autounique Attributes
  private $id;

  //ExpenseReport Associations
  private $laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aExpensePeriod, $aLaboratory)
  {
    $this->expensePeriod = $aExpensePeriod;
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

  public function setExpensePeriod($aExpensePeriod)
  {
    $wasSet = false;
    $this->expensePeriod = $aExpensePeriod;
    $wasSet = true;
    return $wasSet;
  }

  public function getExpensePeriod()
  {
    return $this->expensePeriod;
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