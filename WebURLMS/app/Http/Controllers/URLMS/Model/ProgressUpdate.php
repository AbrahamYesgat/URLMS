<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/
namespace App\Http\Controllers\URLMS\Model;
use Exception;

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
  private $report;

  //Autounique Attributes
  private $id;

  //ProgressUpdate Associations
  private $laboratory;
  private $staff;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aTitle, $aReport, $aLaboratory, $aStaff)
  {
    $this->title = $aTitle;
    $this->report = $aReport;
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

  public function setReport($aReport)
  {
    $wasSet = false;
    $this->report = $aReport;
    $wasSet = true;
    return $wasSet;
  }

  public function getTitle()
  {
    return $this->title;
  }

  public function getReport()
  {
    return $this->report;
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