<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

class FundingAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FundingAccount Attributes
  private $currentBalance;
  private $accountNumber;

  //FundingAccount Associations
  private $laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aCurrentBalance, $aAccountNumber, $aLaboratory)
  {
    $this->currentBalance = $aCurrentBalance;
    $this->accountNumber = $aAccountNumber;
    $didAddLaboratory = $this->setLaboratory($aLaboratory);
    if (!$didAddLaboratory)
    {
      throw new Exception("Unable to create fundingAccount due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setCurrentBalance($aCurrentBalance)
  {
    $wasSet = false;
    $this->currentBalance = $aCurrentBalance;
    $wasSet = true;
    return $wasSet;
  }

  public function setAccountNumber($aAccountNumber)
  {
    $wasSet = false;
    $this->accountNumber = $aAccountNumber;
    $wasSet = true;
    return $wasSet;
  }

  public function getCurrentBalance()
  {
    return $this->currentBalance;
  }

  public function getAccountNumber()
  {
    return $this->accountNumber;
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
      $existingLaboratory->removeFundingAccount($this);
    }
    $this->laboratory->addFundingAccount($this);
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
    $placeholderLaboratory->removeFundingAccount($this);
  }

}
?>