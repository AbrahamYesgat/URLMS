<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

class Director extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Director Attributes
  private $labManaged;

  //Director Associations
  private $laboratory;
  private $urlms;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEmail, $aPassword, $aName, $aUrlms)
  {
    parent::__construct($aEmail, $aPassword, $aName);
    $this->labManaged = 0;
    $this->laboratory = array();
    $didAddUrlms = $this->setUrlms($aUrlms);
    if (!$didAddUrlms)
    {
      throw new Exception("Unable to create dir due to urlms");
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
    $aLaboratory = $this->laboratory[$index];
    return $aLaboratory;
  }

  public function getLaboratory()
  {
    $newLaboratory = $this->laboratory;
    return $newLaboratory;
  }

  public function numberOfLaboratory()
  {
    $number = count($this->laboratory);
    return $number;
  }

  public function hasLaboratory()
  {
    $has = $this->numberOfLaboratory() > 0;
    return $has;
  }

  public function indexOfLaboratory($aLaboratory)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->laboratory as $laboratory)
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

  public function getUrlms()
  {
    return $this->urlms;
  }

  public static function minimumNumberOfLaboratory()
  {
    return 0;
  }

  public function addLaboratoryVia($aName, $aFieldOfStudy, $aStartDate, $aDeadline, $aActive, $aUrlms)
  {
    return new Laboratory($aName, $aFieldOfStudy, $aStartDate, $aDeadline, $aActive, $aUrlms, $this);
  }

  public function addLaboratory($aLaboratory)
  {
    $wasAdded = false;
    if ($this->indexOfLaboratory($aLaboratory) !== -1) { return false; }
    $existingD = $aLaboratory->getD();
    $isNewD = $existingD != null && $this !== $existingD;
    if ($isNewD)
    {
      $aLaboratory->setD($this);
    }
    else
    {
      $this->laboratory[] = $aLaboratory;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeLaboratory($aLaboratory)
  {
    $wasRemoved = false;
    //Unable to remove aLaboratory, as it must always have a d
    if ($this !== $aLaboratory->getD())
    {
      unset($this->laboratory[$this->indexOfLaboratory($aLaboratory)]);
      $this->laboratory = array_values($this->laboratory);
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
      if($index > $this->numberOfLaboratory()) { $index = $this->numberOfLaboratory() - 1; }
      array_splice($this->laboratory, $this->indexOfLaboratory($aLaboratory), 1);
      array_splice($this->laboratory, $index, 0, array($aLaboratory));
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
      if($index > $this->numberOfLaboratory()) { $index = $this->numberOfLaboratory() - 1; }
      array_splice($this->laboratory, $this->indexOfLaboratory($aLaboratory), 1);
      array_splice($this->laboratory, $index, 0, array($aLaboratory));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addLaboratoryAt($aLaboratory, $index);
    }
    return $wasAdded;
  }

  public function setUrlms($aUrlms)
  {
    $wasSet = false;
    if ($aUrlms == null)
    {
      return $wasSet;
    }
    
    $existingUrlms = $this->urlms;
    $this->urlms = $aUrlms;
    if ($existingUrlms != null && $existingUrlms != $aUrlms)
    {
      $existingUrlms->removeDir($this);
    }
    $this->urlms->addDir($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->laboratory) > 0)
    {
      $aLaboratory = $this->laboratory[count($this->laboratory) - 1];
      $aLaboratory->delete();
      unset($this->laboratory[$this->indexOfLaboratory($aLaboratory)]);
      $this->laboratory = array_values($this->laboratory);
    }
    
    $placeholderUrlms = $this->urlms;
    $this->urlms = null;
    $placeholderUrlms->removeDir($this);
    parent::delete();
  }

}
?>