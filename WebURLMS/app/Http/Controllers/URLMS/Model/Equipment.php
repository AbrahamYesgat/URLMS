<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/
namespace App\Http\Controllers\URLMS\Model;
use Exception;

class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private $name;
  private $quantity;

  //Equipment Associations
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
      throw new Exception("Unable to create equipment due to laboratory");
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
      $existingLaboratory->removeEquipment($this);
    }
    $this->laboratory->addEquipment($this);
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
    $placeholderLaboratory->removeEquipment($this);
  }

}
?>