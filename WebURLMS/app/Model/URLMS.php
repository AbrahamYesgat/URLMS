<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

class URLMS
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private $laboratories;
  private $directors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->laboratories = array();
    $this->directors = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new URLMS();
    }
    return self::$theInstance;
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

  public function getDirector_index($index)
  {
    $aDirector = $this->directors[$index];
    return $aDirector;
  }

  public function getDirectors()
  {
    $newDirectors = $this->directors;
    return $newDirectors;
  }

  public function numberOfDirectors()
  {
    $number = count($this->directors);
    return $number;
  }

  public function hasDirectors()
  {
    $has = $this->numberOfDirectors() > 0;
    return $has;
  }

  public function indexOfDirector($aDirector)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->directors as $director)
    {
      if ($director->equals($aDirector))
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

  public function addLaboratoryVia($aName, $aFieldOfStudy, $aStartDate, $aActive, $aDirector)
  {
    return new Laboratory($aName, $aFieldOfStudy, $aStartDate, $aActive, $this, $aDirector);
  }

  public function addLaboratory($aLaboratory)
  {
    $wasAdded = false;
    if ($this->indexOfLaboratory($aLaboratory) !== -1) { return false; }
    $existingURLMS = $aLaboratory->getURLMS();
    $isNewURLMS = $existingURLMS != null && $this !== $existingURLMS;
    if ($isNewURLMS)
    {
      $aLaboratory->setURLMS($this);
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
    //Unable to remove aLaboratory, as it must always have a uRLMS
    if ($this !== $aLaboratory->getURLMS())
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

  public static function minimumNumberOfDirectors()
  {
    return 0;
  }

  public function addDirectorVia($aEmail, $aPassword, $aName)
  {
    return new Director($aEmail, $aPassword, $aName, $this);
  }

  public function addDirector($aDirector)
  {
    $wasAdded = false;
    if ($this->indexOfDirector($aDirector) !== -1) { return false; }
    $existingURLMS = $aDirector->getURLMS();
    $isNewURLMS = $existingURLMS != null && $this !== $existingURLMS;
    if ($isNewURLMS)
    {
      $aDirector->setURLMS($this);
    }
    else
    {
      $this->directors[] = $aDirector;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeDirector($aDirector)
  {
    $wasRemoved = false;
    //Unable to remove aDirector, as it must always have a uRLMS
    if ($this !== $aDirector->getURLMS())
    {
      unset($this->directors[$this->indexOfDirector($aDirector)]);
      $this->directors = array_values($this->directors);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addDirectorAt($aDirector, $index)
  {  
    $wasAdded = false;
    if($this->addDirector($aDirector))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfDirectors()) { $index = $this->numberOfDirectors() - 1; }
      array_splice($this->directors, $this->indexOfDirector($aDirector), 1);
      array_splice($this->directors, $index, 0, array($aDirector));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveDirectorAt($aDirector, $index)
  {
    $wasAdded = false;
    if($this->indexOfDirector($aDirector) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfDirectors()) { $index = $this->numberOfDirectors() - 1; }
      array_splice($this->directors, $this->indexOfDirector($aDirector), 1);
      array_splice($this->directors, $index, 0, array($aDirector));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addDirectorAt($aDirector, $index);
    }
    return $wasAdded;
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
    
    while (count($this->directors) > 0)
    {
      $aDirector = $this->directors[count($this->directors) - 1];
      $aDirector->delete();
      unset($this->directors[$this->indexOfDirector($aDirector)]);
      $this->directors = array_values($this->directors);
    }
    
  }

}
?>