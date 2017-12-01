/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 69 "../../../../../URLMS.ump"
public class UserRole
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, UserRole> userrolesByEmail = new HashMap<String, UserRole>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Attributes
  private String email;
  private String password;
  private String name;
  private boolean currentlyAssigned;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRole(String aEmail, String aPassword, String aName)
  {
    password = aPassword;
    name = aName;
    resetCurrentlyAssigned();
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    String anOldEmail = getEmail();
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    String anOldEmail = getEmail();
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      userrolesByEmail.remove(anOldEmail);
    }
    userrolesByEmail.put(aEmail, this);
    if (anOldEmail != null) {
      userrolesByEmail.remove(anOldEmail);
    }
    userrolesByEmail.put(aEmail, this);
    if (anOldEmail != null) {
      userrolesByEmail.remove(anOldEmail);
    }
    userrolesByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentlyAssigned(boolean aCurrentlyAssigned)
  {
    boolean wasSet = false;
    currentlyAssigned = aCurrentlyAssigned;
    wasSet = true;
    return wasSet;
  }

  public boolean resetCurrentlyAssigned()
  {
    boolean wasReset = false;
    currentlyAssigned = getDefaultCurrentlyAssigned();
    wasReset = true;
    return wasReset;
  }

  public String getEmail()
  {
    return email;
  }

  public static UserRole getWithEmail(String aEmail)
  {
    return userrolesByEmail.get(aEmail);
  }

  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getName()
  {
    return name;
  }

  public boolean getCurrentlyAssigned()
  {
    return currentlyAssigned;
  }

  public boolean getDefaultCurrentlyAssigned()
  {
    return true;
  }

  public boolean isCurrentlyAssigned()
  {
    return currentlyAssigned;
  }

  public void delete()
  {
    userrolesByEmail.remove(getEmail());
    userrolesByEmail.remove(getEmail());
    userrolesByEmail.remove(getEmail());
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "name" + ":" + getName()+ "," +
            "currentlyAssigned" + ":" + getCurrentlyAssigned()+ "]"
     + outputString;
  }
}