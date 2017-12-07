<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/
namespace App\Http\Controllers\URLMS\Model;
use Exception;
use App\Http\Controllers\URLMS\Model\UserRole as UserRole;
use App\Http\Controllers\URLMS\Model\Laboratory as Laboratory;

class Director extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Director Attributes
  private $labManaged;

  //Director Associations
  private $laboratories;
  private $uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEmail, $aPassword, $aName, $aURLMS)
  {
    parent::__construct($aEmail, $aPassword, $aName);
    $this->labManaged = 0;
    $this->laboratories = array();
    $didAddURLMS = $this->setURLMS($aURLMS);
    if (!$didAddURLMS)
    {
      throw new Exception("Unable to create director due to uRLMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setLabManaged($aLabManaged)
  {
    $wasSet = false;
    $this->labManaged = $aLabManaged;
    $wasSet = true;
    return $wasSet;
  }

  public function getLabManaged()
  {
    return $this->labManaged;
  }

  public function getLaboratory_index($index)
  {
    $aLaboratory = $this->laboratories[$index];
    return $aLaboratory;
  }

  public function getLaboratories()
  {
    $newLaboratories = $this->laboratories;
    return $newLaboratories;
  }

  public function numberOfLaboratories()
  {
    $number = count($this->laboratories);
    return $number;
  }

  public function hasLaboratories()
  {
    $has = $this->numberOfLaboratories() > 0;
    return $has;
  }

  public function indexOfLaboratory($aLaboratory)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->laboratories as $laboratory)
    {
      if ($laboratory->equals($aLaboratory))
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

  public static function minimumNumberOfLaboratories()
  {
    return 0;
  }

  public function addLaboratoryVia($aName, $aFieldOfStudy, $aStartDate, $aActive, $aURLMS)
  {
    return new Laboratory($aName, $aFieldOfStudy, $aStartDate, $aActive, $aURLMS, $this);
  }

  public function addLaboratory($aLaboratory)
  {
    $wasAdded = false;
    if ($this->indexOfLaboratory($aLaboratory) !== -1) { return false; }
    $existingDirector = $aLaboratory->getDirector();
    $isNewDirector = $existingDirector != null && $this !== $existingDirector;
    if ($isNewDirector)
    {
      $aLaboratory->setDirector($this);
    }
    else
    {
      $this->laboratories[] = $aLaboratory;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeLaboratory($aLaboratory)
  {
    $wasRemoved = false;
    //Unable to remove aLaboratory, as it must always have a director
    if ($this !== $aLaboratory->getDirector())
    {
      unset($this->laboratories[$this->indexOfLaboratory($aLaboratory)]);
      $this->laboratories = array_values($this->laboratories);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addLaboratoryAt($aLaboratory, $index)
  {  
    $wasAdded = false;
    if($this->addLaboratory($aLaboratory))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLaboratories()) { $index = $this->numberOfLaboratories() - 1; }
      array_splice($this->laboratories, $this->indexOfLaboratory($aLaboratory), 1);
      array_splice($this->laboratories, $index, 0, array($aLaboratory));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveLaboratoryAt($aLaboratory, $index)
  {
    $wasAdded = false;
    if($this->indexOfLaboratory($aLaboratory) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfLaboratories()) { $index = $this->numberOfLaboratories() - 1; }
      array_splice($this->laboratories, $this->indexOfLaboratory($aLaboratory), 1);
      array_splice($this->laboratories, $index, 0, array($aLaboratory));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addLaboratoryAt($aLaboratory, $index);
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
      $existingURLMS->removeDirector($this);
    }
    $this->uRLMS->addDirector($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->laboratories) > 0)
    {
      $aLaboratory = $this->laboratories[count($this->laboratories) - 1];
      $aLaboratory->delete();
      unset($this->laboratories[$this->indexOfLaboratory($aLaboratory)]);
      $this->laboratories = array_values($this->laboratories);
    }
    
    $placeholderURLMS = $this->uRLMS;
    $this->uRLMS = null;
    $placeholderURLMS->removeDirector($this);
    parent::delete();
  }

}
?>