<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

class URLMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private $laboratory;
  private $dir;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct()
  {
    $this->laboratory = array();
    $this->dir = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public function getDir_index($index)
  {
    $aDir = $this->dir[$index];
    return $aDir;
  }

  public function getDir()
  {
    $newDir = $this->dir;
    return $newDir;
  }

  public function numberOfDir()
  {
    $number = count($this->dir);
    return $number;
  }

  public function hasDir()
  {
    $has = $this->numberOfDir() > 0;
    return $has;
  }

  public function indexOfDir($aDir)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->dir as $dir)
    {
      if ($dir->equals($aDir))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfLaboratory()
  {
    return 0;
  }

  public function addLaboratoryVia($aName, $aFieldOfStudy, $aStartDate, $aDeadline, $aActive, $aD)
  {
    return new Laboratory($aName, $aFieldOfStudy, $aStartDate, $aDeadline, $aActive, $this, $aD);
  }

  public function addLaboratory($aLaboratory)
  {
    $wasAdded = false;
    if ($this->indexOfLaboratory($aLaboratory) !== -1) { return false; }
    $existingUrlms = $aLaboratory->getUrlms();
    $isNewUrlms = $existingUrlms != null && $this !== $existingUrlms;
    if ($isNewUrlms)
    {
      $aLaboratory->setUrlms($this);
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
    //Unable to remove aLaboratory, as it must always have a urlms
    if ($this !== $aLaboratory->getUrlms())
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

  public static function minimumNumberOfDir()
  {
    return 0;
  }

  public function addDirVia($aEmail, $aPassword, $aName)
  {
    return new Director($aEmail, $aPassword, $aName, $this);
  }

  public function addDir($aDir)
  {
    $wasAdded = false;
    if ($this->indexOfDir($aDir) !== -1) { return false; }
    $existingUrlms = $aDir->getUrlms();
    $isNewUrlms = $existingUrlms != null && $this !== $existingUrlms;
    if ($isNewUrlms)
    {
      $aDir->setUrlms($this);
    }
    else
    {
      $this->dir[] = $aDir;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeDir($aDir)
  {
    $wasRemoved = false;
    //Unable to remove aDir, as it must always have a urlms
    if ($this !== $aDir->getUrlms())
    {
      unset($this->dir[$this->indexOfDir($aDir)]);
      $this->dir = array_values($this->dir);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addDirAt($aDir, $index)
  {  
    $wasAdded = false;
    if($this->addDir($aDir))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfDir()) { $index = $this->numberOfDir() - 1; }
      array_splice($this->dir, $this->indexOfDir($aDir), 1);
      array_splice($this->dir, $index, 0, array($aDir));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveDirAt($aDir, $index)
  {
    $wasAdded = false;
    if($this->indexOfDir($aDir) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfDir()) { $index = $this->numberOfDir() - 1; }
      array_splice($this->dir, $this->indexOfDir($aDir), 1);
      array_splice($this->dir, $index, 0, array($aDir));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addDirAt($aDir, $index);
    }
    return $wasAdded;
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
    
    while (count($this->dir) > 0)
    {
      $aDir = $this->dir[count($this->dir) - 1];
      $aDir->delete();
      unset($this->dir[$this->indexOfDir($aDir)]);
      $this->dir = array_values($this->dir);
    }
    
  }

}
?>