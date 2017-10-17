<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

class UserRole
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $userrolesByEmail = array();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Attributes
  private $email;
  private $password;
  private $name;
  private $currentlyAssigned;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aEmail, $aPassword, $aName)
  {
    $this->password = $aPassword;
    $this->name = $aName;
    $this->resetCurrentlyAssigned();
    if (!$this->setEmail($aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setEmail($aEmail)
  {
    $wasSet = false;
    if (isset($this->email)) {
      $anOldEmail = $this->getEmail();
    }
    if (UserRole::hasWithEmail($aEmail)) {
      return $wasSet;
    }
    if (isset($this->email)) {
      $anOldEmail = $this->getEmail();
    }
    if (UserRole::hasWithEmail($aEmail)) {
      return $wasSet;
    }
    if (isset($this->email)) {
      $anOldEmail = $this->getEmail();
    }
    if (UserRole::hasWithEmail($aEmail)) {
      return $wasSet;
    }
    $this->email = $aEmail;
    $wasSet = true;
    if (isset($anOldEmail)) {
      unset(UserRole::$userrolesByEmail[(string)$anOldEmail]);
    }
    UserRole::$userrolesByEmail[(string)$aEmail] = $this;
    if (isset($anOldEmail)) {
      unset(UserRole::$userrolesByEmail[(string)$anOldEmail]);
    }
    UserRole::$userrolesByEmail[(string)$aEmail] = $this;
    if (isset($anOldEmail)) {
      unset(UserRole::$userrolesByEmail[(string)$anOldEmail]);
    }
    UserRole::$userrolesByEmail[(string)$aEmail] = $this;
    return $wasSet;
  }

  public function setPassword($aPassword)
  {
    $wasSet = false;
    $this->password = $aPassword;
    $wasSet = true;
    return $wasSet;
  }

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setCurrentlyAssigned($aCurrentlyAssigned)
  {
    $wasSet = false;
    $this->currentlyAssigned = $aCurrentlyAssigned;
    $wasSet = true;
    return $wasSet;
  }

  public function resetCurrentlyAssigned()
  {
    $wasReset = false;
    $this->currentlyAssigned = $this->getDefaultCurrentlyAssigned();
    $wasReset = true;
    return $wasReset;
  }

  public function getEmail()
  {
    return $this->email;
  }

  public static function getWithEmail($aEmail)
  {
    return UserRole::$userrolesByEmail[(string)$aEmail];
  }

  public static function hasWithEmail($aEmail)
  {
    return array_key_exists((string)$aEmail, UserRole::$userrolesByEmail);
  }

  public function getPassword()
  {
    return $this->password;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getCurrentlyAssigned()
  {
    return $this->currentlyAssigned;
  }

  public function getDefaultCurrentlyAssigned()
  {
    return true;
  }

  public function isCurrentlyAssigned()
  {
    return $this->currentlyAssigned;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    unset(UserRole::$userrolesByEmail[(string)$this->getEmail()]);
    unset(UserRole::$userrolesByEmail[(string)$this->getEmail()]);
    unset(UserRole::$userrolesByEmail[(string)$this->getEmail()]);
  }

}
?>