/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../URLMS.ump"
public class URLMS
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private List<Laboratory> laboratory;
  private List<Director> dir;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public URLMS()
  {
    laboratory = new ArrayList<Laboratory>();
    dir = new ArrayList<Director>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Director getDir(int index)
  {
    Director aDir = dir.get(index);
    return aDir;
  }

  public List<Director> getDir()
  {
    List<Director> newDir = Collections.unmodifiableList(dir);
    return newDir;
  }

  public int numberOfDir()
  {
    int number = dir.size();
    return number;
  }

  public boolean hasDir()
  {
    boolean has = dir.size() > 0;
    return has;
  }

  public int indexOfDir(Director aDir)
  {
    int index = dir.indexOf(aDir);
    return index;
  }

  public static int minimumNumberOfLaboratory()
  {
    return 0;
  }

  public Laboratory addLaboratory(String aName, String aFieldOfStudy, Date aStartDate, Date aDeadline, boolean aActive, Director aD)
  {
    return new Laboratory(aName, aFieldOfStudy, aStartDate, aDeadline, aActive, this, aD);
  }

  public boolean addLaboratory(Laboratory aLaboratory)
  {
    boolean wasAdded = false;
    if (laboratory.contains(aLaboratory)) { return false; }
    URLMS existingUrlms = aLaboratory.getUrlms();
    boolean isNewUrlms = existingUrlms != null && !this.equals(existingUrlms);
    if (isNewUrlms)
    {
      aLaboratory.setUrlms(this);
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
    //Unable to remove aLaboratory, as it must always have a urlms
    if (!this.equals(aLaboratory.getUrlms()))
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

  public static int minimumNumberOfDir()
  {
    return 0;
  }

  public Director addDir(String aEmail, String aPassword, String aName)
  {
    return new Director(aEmail, aPassword, aName, this);
  }

  public boolean addDir(Director aDir)
  {
    boolean wasAdded = false;
    if (dir.contains(aDir)) { return false; }
    URLMS existingUrlms = aDir.getUrlms();
    boolean isNewUrlms = existingUrlms != null && !this.equals(existingUrlms);
    if (isNewUrlms)
    {
      aDir.setUrlms(this);
    }
    else
    {
      dir.add(aDir);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDir(Director aDir)
  {
    boolean wasRemoved = false;
    //Unable to remove aDir, as it must always have a urlms
    if (!this.equals(aDir.getUrlms()))
    {
      dir.remove(aDir);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addDirAt(Director aDir, int index)
  {  
    boolean wasAdded = false;
    if(addDir(aDir))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDir()) { index = numberOfDir() - 1; }
      dir.remove(aDir);
      dir.add(index, aDir);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDirAt(Director aDir, int index)
  {
    boolean wasAdded = false;
    if(dir.contains(aDir))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDir()) { index = numberOfDir() - 1; }
      dir.remove(aDir);
      dir.add(index, aDir);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDirAt(aDir, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (laboratory.size() > 0)
    {
      Laboratory aLaboratory = laboratory.get(laboratory.size() - 1);
      aLaboratory.delete();
      laboratory.remove(aLaboratory);
    }
    
    while (dir.size() > 0)
    {
      Director aDir = dir.get(dir.size() - 1);
      aDir.delete();
      dir.remove(aDir);
    }
    
  }

}