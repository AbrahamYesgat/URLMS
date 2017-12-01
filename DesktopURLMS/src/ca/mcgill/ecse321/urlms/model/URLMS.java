/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../URLMS.ump"
public class URLMS
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static URLMS theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //URLMS Associations
  private List<Laboratory> laboratories;
  private List<Director> directors;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private URLMS()
  {
    laboratories = new ArrayList<Laboratory>();
    directors = new ArrayList<Director>();
  }

  public static URLMS getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new URLMS();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Director getDirector(int index)
  {
    Director aDirector = directors.get(index);
    return aDirector;
  }

  public List<Director> getDirectors()
  {
    List<Director> newDirectors = Collections.unmodifiableList(directors);
    return newDirectors;
  }

  public int numberOfDirectors()
  {
    int number = directors.size();
    return number;
  }

  public boolean hasDirectors()
  {
    boolean has = directors.size() > 0;
    return has;
  }

  public int indexOfDirector(Director aDirector)
  {
    int index = directors.indexOf(aDirector);
    return index;
  }

  public static int minimumNumberOfLaboratories()
  {
    return 0;
  }

  public Laboratory addLaboratory(String aName, String aFieldOfStudy, Date aStartDate, boolean aActive, Director aDirector)
  {
    return new Laboratory(aName, aFieldOfStudy, aStartDate, aActive, this, aDirector);
  }

  public boolean addLaboratory(Laboratory aLaboratory)
  {
    boolean wasAdded = false;
    if (laboratories.contains(aLaboratory)) { return false; }
    URLMS existingURLMS = aLaboratory.getURLMS();
    boolean isNewURLMS = existingURLMS != null && !this.equals(existingURLMS);
    if (isNewURLMS)
    {
      aLaboratory.setURLMS(this);
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
    //Unable to remove aLaboratory, as it must always have a uRLMS
    if (!this.equals(aLaboratory.getURLMS()))
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

  public static int minimumNumberOfDirectors()
  {
    return 0;
  }

  public Director addDirector(String aEmail, String aPassword, String aName)
  {
    return new Director(aEmail, aPassword, aName, this);
  }

  public boolean addDirector(Director aDirector)
  {
    boolean wasAdded = false;
    if (directors.contains(aDirector)) { return false; }
    URLMS existingURLMS = aDirector.getURLMS();
    boolean isNewURLMS = existingURLMS != null && !this.equals(existingURLMS);
    if (isNewURLMS)
    {
      aDirector.setURLMS(this);
    }
    else
    {
      directors.add(aDirector);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDirector(Director aDirector)
  {
    boolean wasRemoved = false;
    //Unable to remove aDirector, as it must always have a uRLMS
    if (!this.equals(aDirector.getURLMS()))
    {
      directors.remove(aDirector);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addDirectorAt(Director aDirector, int index)
  {  
    boolean wasAdded = false;
    if(addDirector(aDirector))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDirectors()) { index = numberOfDirectors() - 1; }
      directors.remove(aDirector);
      directors.add(index, aDirector);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDirectorAt(Director aDirector, int index)
  {
    boolean wasAdded = false;
    if(directors.contains(aDirector))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDirectors()) { index = numberOfDirectors() - 1; }
      directors.remove(aDirector);
      directors.add(index, aDirector);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDirectorAt(aDirector, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (laboratories.size() > 0)
    {
      Laboratory aLaboratory = laboratories.get(laboratories.size() - 1);
      aLaboratory.delete();
      laboratories.remove(aLaboratory);
    }
    
    while (directors.size() > 0)
    {
      Director aDirector = directors.get(directors.size() - 1);
      aDirector.delete();
      directors.remove(aDirector);
    }
    
  }

}