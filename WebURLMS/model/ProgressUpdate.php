<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class ProgressUpdate
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ProgressUpdate Attributes
  private $title;
  private $reportingPeriod;

  //Autounique Attributes
  private $id;

  //ProgressUpdate Associations
  private $laboratory;
  private $staff;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aReportingPeriod, $aLaboratory, $aStaff)
  {
    $this->title = $aTitle;
    $this->reportingPeriod = $aReportingPeriod;
    $this->id = self::$nextId++;
    $didAddLaboratory = $this->setLaboratory($aLaboratory);
    if (!$didAddLaboratory)
    {
      throw new Exception("Unable to create progressUpdate due to laboratory");
    }
    $didAddStaff = $this->setStaff($aStaff);
    if (!$didAddStaff)
    {
      throw new Exception("Unable to create progressUpdate due to staff");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setTitle($aTitle)
  {
    $wasSet = false;
    $this->title = $aTitle;
    $wasSet = true;
    return $wasSet;
  }

  public function setReportingPeriod($aReportingPeriod)
  {
    $wasSet = false;
    $this->reportingPeriod = $aReportingPeriod;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getReportingPeriod()
  {
    return $this->reportingPeriod;
  }

  public function getId()
  {
    return $this->id;
  }

  public function getLaboratory()
  {
    return $this->laboratory;
  }

  public function getStaff()
  {
    return $this->staff;
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
      $existingLaboratory->removeProgressUpdate($this);
    }
    $this->laboratory->addProgressUpdate($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setStaff($aStaff)
  {
    $wasSet = false;
    if ($aStaff == null)
    {
      return $wasSet;
    }
    
    $existingStaff = $this->staff;
    $this->staff = $aStaff;
    if ($existingStaff != null && $existingStaff != $aStaff)
    {
      $existingStaff->removeProgressUpdate($this);
    }
    $this->staff->addProgressUpdate($this);
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
    $placeholderLaboratory->removeProgressUpdate($this);
    $placeholderStaff = $this->staff;
    $this->staff = null;
    $placeholderStaff->removeProgressUpdate($this);
  }

}
?>