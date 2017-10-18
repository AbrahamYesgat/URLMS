<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

class Staff extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Associations
  private $laboratories;
  private $progressUpdates;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEmail, $aPassword, $aName)
  {
    parent::__construct($aEmail, $aPassword, $aName);
    $this->laboratories = array();
    $this->progressUpdates = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public static function minimumNumberOfLaboratories()
  {
    return 0;
  }

  public function addLaboratory($aLaboratory)
  {
    $wasAdded = false;
    if ($this->indexOfLaboratory($aLaboratory) !== -1) { return false; }
    $this->laboratories[] = $aLaboratory;
    if ($aLaboratory->indexOfStaff($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aLaboratory->addStaff($this);
      if (!$wasAdded)
      {
        array_pop($this->laboratories);
      }
    }
    return $wasAdded;
  }

  public function removeLaboratory($aLaboratory)
  {
    $wasRemoved = false;
    if ($this->indexOfLaboratory($aLaboratory) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfLaboratory($aLaboratory);
    unset($this->laboratories[$oldIndex]);
    if ($aLaboratory->indexOfStaff($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aLaboratory->removeStaff($this);
      if (!$wasRemoved)
      {
        $this->laboratories[$oldIndex] = $aLaboratory;
        ksort($this->laboratories);
      }
    }
    $this->laboratories = array_values($this->laboratories);
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

  public static function minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public function addProgressUpdateVia($aTitle, $aReportingPeriod, $aLaboratory)
  {
    return new ProgressUpdate($aTitle, $aReportingPeriod, $aLaboratory, $this);
  }

  public function addProgressUpdate($aProgressUpdate)
  {
    $wasAdded = false;
    if ($this->indexOfProgressUpdate($aProgressUpdate) !== -1) { return false; }
    $existingStaff = $aProgressUpdate->getStaff();
    $isNewStaff = $existingStaff != null && $this !== $existingStaff;
    if ($isNewStaff)
    {
      $aProgressUpdate->setStaff($this);
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
    //Unable to remove aProgressUpdate, as it must always have a staff
    if ($this !== $aProgressUpdate->getStaff())
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfLaboratories = $this->laboratories;
    $this->laboratories = array();
    foreach ($copyOfLaboratories as $aLaboratory)
    {
      $aLaboratory->removeStaff($this);
    }
    foreach ($this->progressUpdates as $aProgressUpdate)
    {
      $aProgressUpdate->delete();
    }
    parent::delete();
  }

}
?>