/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 26 "../../../../../URLMS.ump"
public class Director extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Director Attributes
  private int labManaged;

  //Director Associations
  private List<Laboratory> laboratories;
  private URLMS uRLMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Director(String aEmail, String aPassword, String aName, URLMS aURLMS)
  {
    super(aEmail, aPassword, aName);
    labManaged = 0;
    laboratories = new ArrayList<Laboratory>();
    boolean didAddURLMS = setURLMS(aURLMS);
    if (!didAddURLMS)
    {
      throw new RuntimeException("Unable to create director due to uRLMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setLabManaged(int aLabManaged)
  {
    boolean wasSet = false;
    labManaged = aLabManaged;
    wasSet = true;
    return wasSet;
  }

  public int getLabManaged()
  {
    return labManaged;
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

  public URLMS getURLMS()
  {
    return uRLMS;
  }

  public static int minimumNumberOfLaboratories()
  {
    return 0;
  }

  public Laboratory addLaboratory(String aName, String aFieldOfStudy, Date aStartDate, Date aDeadline, boolean aActive, URLMS aURLMS)
  {
    return new Laboratory(aName, aFieldOfStudy, aStartDate, aDeadline, aActive, aURLMS, this);
  }

  public boolean addLaboratory(Laboratory aLaboratory)
  {
    boolean wasAdded = false;
    if (laboratories.contains(aLaboratory)) { return false; }
    Director existingDirector = aLaboratory.getDirector();
    boolean isNewDirector = existingDirector != null && !this.equals(existingDirector);
    if (isNewDirector)
    {
      aLaboratory.setDirector(this);
    }
    else
    {
      laboratories.add(aLaboratory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLaboratory(Laboratory aLaboratory)
  {
    boolean wasRemoved = false;
    //Unable to remove aLaboratory, as it must always have a director
    if (!this.equals(aLaboratory.getDirector()))
    {
      laboratories.remove(aLaboratory);
      wasRemoved = true;
    }
    return wasRemoved;
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

  public boolean setURLMS(URLMS aURLMS)
  {
    boolean wasSet = false;
    if (aURLMS == null)
    {
      return wasSet;
    }

    URLMS existingURLMS = uRLMS;
    uRLMS = aURLMS;
    if (existingURLMS != null && !existingURLMS.equals(aURLMS))
    {
      existingURLMS.removeDirector(this);
    }
    uRLMS.addDirector(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (laboratories.size() > 0)
    {
      Laboratory aLaboratory = laboratories.get(laboratories.size() - 1);
      aLaboratory.delete();
      laboratories.remove(aLaboratory);
    }
    
    URLMS placeholderURLMS = uRLMS;
    this.uRLMS = null;
    placeholderURLMS.removeDirector(this);
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "labManaged" + ":" + getLabManaged()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "uRLMS = "+(getURLMS()!=null?Integer.toHexString(System.identityHashCode(getURLMS())):"null");
  }
}