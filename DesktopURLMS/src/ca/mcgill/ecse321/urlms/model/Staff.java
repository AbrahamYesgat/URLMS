/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;

// line 44 "../../../../../URLMS.ump"
public class Staff extends UserRole
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum StaffRole { ResearchAssociate, ResearchAssistant }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private StaffRole staffRole;
  private String lastLogin;

  //Staff Associations
  private List<Laboratory> laboratories;
  private List<ProgressUpdate> progressUpdates;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aEmail, String aPassword, String aName, StaffRole aStaffRole, Laboratory... allLaboratories)
  {
    super(aEmail, aPassword, aName);
    staffRole = aStaffRole;
    lastLogin = null;
    laboratories = new ArrayList<Laboratory>();
    boolean didAddLaboratories = setLaboratories(allLaboratories);
    if (!didAddLaboratories)
    {
      throw new RuntimeException("Unable to create Staff, must have at least 1 laboratories");
    }
    progressUpdates = new ArrayList<ProgressUpdate>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStaffRole(StaffRole aStaffRole)
  {
    boolean wasSet = false;
    staffRole = aStaffRole;
    wasSet = true;
    return wasSet;
  }

  public boolean setLastLogin(String aLastLogin)
  {
    boolean wasSet = false;
    lastLogin = aLastLogin;
    wasSet = true;
    return wasSet;
  }

  public StaffRole getStaffRole()
  {
    return staffRole;
  }

  public String getLastLogin()
  {
    return lastLogin;
  }

  public Laboratory getLaboratory(int index)
  {
    Laboratory aLaboratory = laboratories.get(index);
    return aLaboratory;
  }

  public List<Laboratory> getLaboratories()
  {
    List<Laboratory> newLaboratories = Collections.unmodifiableList(laboratories);
    return newLaboratories;
  }

  public int numberOfLaboratories()
  {
    int number = laboratories.size();
    return number;
  }

  public boolean hasLaboratories()
  {
    boolean has = laboratories.size() > 0;
    return has;
  }

  public int indexOfLaboratory(Laboratory aLaboratory)
  {
    int index = laboratories.indexOf(aLaboratory);
    return index;
  }

  public ProgressUpdate getProgressUpdate(int index)
  {
    ProgressUpdate aProgressUpdate = progressUpdates.get(index);
    return aProgressUpdate;
  }

  public List<ProgressUpdate> getProgressUpdates()
  {
    List<ProgressUpdate> newProgressUpdates = Collections.unmodifiableList(progressUpdates);
    return newProgressUpdates;
  }

  public int numberOfProgressUpdates()
  {
    int number = progressUpdates.size();
    return number;
  }

  public boolean hasProgressUpdates()
  {
    boolean has = progressUpdates.size() > 0;
    return has;
  }

  public int indexOfProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    int index = progressUpdates.indexOf(aProgressUpdate);
    return index;
  }

  public boolean isNumberOfLaboratoriesValid()
  {
    boolean isValid = numberOfLaboratories() >= minimumNumberOfLaboratories();
    return isValid;
  }

  public static int minimumNumberOfLaboratories()
  {
    return 1;
  }

  public boolean addLaboratory(Laboratory aLaboratory)
  {
    boolean wasAdded = false;
    if (laboratories.contains(aLaboratory)) { return false; }
    laboratories.add(aLaboratory);
    if (aLaboratory.indexOfStaff(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLaboratory.addStaff(this);
      if (!wasAdded)
      {
        laboratories.remove(aLaboratory);
      }
    }
    return wasAdded;
  }

  public boolean removeLaboratory(Laboratory aLaboratory)
  {
    boolean wasRemoved = false;
    if (!laboratories.contains(aLaboratory))
    {
      return wasRemoved;
    }

    if (numberOfLaboratories() <= minimumNumberOfLaboratories())
    {
      return wasRemoved;
    }

    int oldIndex = laboratories.indexOf(aLaboratory);
    laboratories.remove(oldIndex);
    if (aLaboratory.indexOfStaff(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLaboratory.removeStaff(this);
      if (!wasRemoved)
      {
        laboratories.add(oldIndex,aLaboratory);
      }
    }
    return wasRemoved;
  }

  public boolean setLaboratories(Laboratory... newLaboratories)
  {
    boolean wasSet = false;
    ArrayList<Laboratory> verifiedLaboratories = new ArrayList<Laboratory>();
    for (Laboratory aLaboratory : newLaboratories)
    {
      if (verifiedLaboratories.contains(aLaboratory))
      {
        continue;
      }
      verifiedLaboratories.add(aLaboratory);
    }

    if (verifiedLaboratories.size() != newLaboratories.length || verifiedLaboratories.size() < minimumNumberOfLaboratories())
    {
      return wasSet;
    }

    ArrayList<Laboratory> oldLaboratories = new ArrayList<Laboratory>(laboratories);
    laboratories.clear();
    for (Laboratory aNewLaboratory : verifiedLaboratories)
    {
      laboratories.add(aNewLaboratory);
      if (oldLaboratories.contains(aNewLaboratory))
      {
        oldLaboratories.remove(aNewLaboratory);
      }
      else
      {
        aNewLaboratory.addStaff(this);
      }
    }

    for (Laboratory anOldLaboratory : oldLaboratories)
    {
      anOldLaboratory.removeStaff(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addLaboratoryAt(Laboratory aLaboratory, int index)
  {  
    boolean wasAdded = false;
    if(addLaboratory(aLaboratory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLaboratories()) { index = numberOfLaboratories() - 1; }
      laboratories.remove(aLaboratory);
      laboratories.add(index, aLaboratory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLaboratoryAt(Laboratory aLaboratory, int index)
  {
    boolean wasAdded = false;
    if(laboratories.contains(aLaboratory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLaboratories()) { index = numberOfLaboratories() - 1; }
      laboratories.remove(aLaboratory);
      laboratories.add(index, aLaboratory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLaboratoryAt(aLaboratory, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public ProgressUpdate addProgressUpdate(String aTitle, String aReport, Laboratory aLaboratory)
  {
    return new ProgressUpdate(aTitle, aReport, aLaboratory, this);
  }

  public boolean addProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    boolean wasAdded = false;
    if (progressUpdates.contains(aProgressUpdate)) { return false; }
    Staff existingStaff = aProgressUpdate.getStaff();
    boolean isNewStaff = existingStaff != null && !this.equals(existingStaff);
    if (isNewStaff)
    {
      aProgressUpdate.setStaff(this);
    }
    else
    {
      progressUpdates.add(aProgressUpdate);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    boolean wasRemoved = false;
    //Unable to remove aProgressUpdate, as it must always have a staff
    if (!this.equals(aProgressUpdate.getStaff()))
    {
      progressUpdates.remove(aProgressUpdate);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addProgressUpdateAt(ProgressUpdate aProgressUpdate, int index)
  {  
    boolean wasAdded = false;
    if(addProgressUpdate(aProgressUpdate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgressUpdates()) { index = numberOfProgressUpdates() - 1; }
      progressUpdates.remove(aProgressUpdate);
      progressUpdates.add(index, aProgressUpdate);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveProgressUpdateAt(ProgressUpdate aProgressUpdate, int index)
  {
    boolean wasAdded = false;
    if(progressUpdates.contains(aProgressUpdate))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfProgressUpdates()) { index = numberOfProgressUpdates() - 1; }
      progressUpdates.remove(aProgressUpdate);
      progressUpdates.add(index, aProgressUpdate);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addProgressUpdateAt(aProgressUpdate, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Laboratory> copyOfLaboratories = new ArrayList<Laboratory>(laboratories);
    laboratories.clear();
    for(Laboratory aLaboratory : copyOfLaboratories)
    {
      aLaboratory.removeStaff(this);
    }
    for(int i=progressUpdates.size(); i > 0; i--)
    {
      ProgressUpdate aProgressUpdate = progressUpdates.get(i - 1);
      aProgressUpdate.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "lastLogin" + ":" + getLastLogin()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "staffRole" + "=" + (getStaffRole() != null ? !getStaffRole().equals(this)  ? getStaffRole().toString().replaceAll("  ","    ") : "this" : "null");
  }
}