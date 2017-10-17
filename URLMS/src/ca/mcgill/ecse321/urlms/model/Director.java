/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 25 "../../../../../URLMS.ump"
public class Director extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Director Attributes
  private int labManaged;

  //Director Associations
  private List<Laboratory> laboratory;
  private URLMS urlms;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Director(String aEmail, String aPassword, String aName, URLMS aUrlms)
  {
    super(aEmail, aPassword, aName);
    labManaged = 0;
    laboratory = new ArrayList<Laboratory>();
    boolean didAddUrlms = setUrlms(aUrlms);
    if (!didAddUrlms)
    {
      throw new RuntimeException("Unable to create dir due to urlms");
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
    Laboratory aLaboratory = laboratory.get(index);
    return aLaboratory;
  }

  public List<Laboratory> getLaboratory()
  {
    List<Laboratory> newLaboratory = Collections.unmodifiableList(laboratory);
    return newLaboratory;
  }

  public int numberOfLaboratory()
  {
    int number = laboratory.size();
    return number;
  }

  public boolean hasLaboratory()
  {
    boolean has = laboratory.size() > 0;
    return has;
  }

  public int indexOfLaboratory(Laboratory aLaboratory)
  {
    int index = laboratory.indexOf(aLaboratory);
    return index;
  }

  public URLMS getUrlms()
  {
    return urlms;
  }

  public static int minimumNumberOfLaboratory()
  {
    return 0;
  }

  public Laboratory addLaboratory(String aName, String aFieldOfStudy, Date aStartDate, Date aDeadline, boolean aActive, URLMS aUrlms)
  {
    return new Laboratory(aName, aFieldOfStudy, aStartDate, aDeadline, aActive, aUrlms, this);
  }

  public boolean addLaboratory(Laboratory aLaboratory)
  {
    boolean wasAdded = false;
    if (laboratory.contains(aLaboratory)) { return false; }
    Director existingD = aLaboratory.getD();
    boolean isNewD = existingD != null && !this.equals(existingD);
    if (isNewD)
    {
      aLaboratory.setD(this);
    }
    else
    {
      laboratory.add(aLaboratory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLaboratory(Laboratory aLaboratory)
  {
    boolean wasRemoved = false;
    //Unable to remove aLaboratory, as it must always have a d
    if (!this.equals(aLaboratory.getD()))
    {
      laboratory.remove(aLaboratory);
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
      if(index > numberOfLaboratory()) { index = numberOfLaboratory() - 1; }
      laboratory.remove(aLaboratory);
      laboratory.add(index, aLaboratory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLaboratoryAt(Laboratory aLaboratory, int index)
  {
    boolean wasAdded = false;
    if(laboratory.contains(aLaboratory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLaboratory()) { index = numberOfLaboratory() - 1; }
      laboratory.remove(aLaboratory);
      laboratory.add(index, aLaboratory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLaboratoryAt(aLaboratory, index);
    }
    return wasAdded;
  }

  public boolean setUrlms(URLMS aUrlms)
  {
    boolean wasSet = false;
    if (aUrlms == null)
    {
      return wasSet;
    }

    URLMS existingUrlms = urlms;
    urlms = aUrlms;
    if (existingUrlms != null && !existingUrlms.equals(aUrlms))
    {
      existingUrlms.removeDir(this);
    }
    urlms.addDir(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (laboratory.size() > 0)
    {
      Laboratory aLaboratory = laboratory.get(laboratory.size() - 1);
      aLaboratory.delete();
      laboratory.remove(aLaboratory);
    }
    
    URLMS placeholderUrlms = urlms;
    this.urlms = null;
    placeholderUrlms.removeDir(this);
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "labManaged" + ":" + getLabManaged()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "urlms = "+(getUrlms()!=null?Integer.toHexString(System.identityHashCode(getUrlms())):"null");
  }
}