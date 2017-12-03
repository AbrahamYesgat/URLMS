<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

class Supplies
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supplies Attributes
  private $name;
  private $quantity;

  //Supplies Associations
  private $laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aQuantity, $aLaboratory)
  {
    $this->name = $aName;
    $this->quantity = $aQuantity;
    $didAddLaboratory = $this->setLaboratory($aLaboratory);
    if (!$didAddLaboratory)
    {
      throw new Exception("Unable to create supply due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setQuantity($aQuantity)
  {
    $wasSet = false;
    $this->quantity = $aQuantity;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getQuantity()
  {
    return $this->quantity;
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
      $existingLaboratory->removeSupply($this);
    }
    $this->laboratory->addSupply($this);
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
    $placeholderLaboratory->removeSupply($this);
  }

}
?>