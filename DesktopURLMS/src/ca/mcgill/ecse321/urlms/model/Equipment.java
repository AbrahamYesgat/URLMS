/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

import java.util.Iterator;

// line 31 "../../../../../URLMS.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private String name;
  private String type;
  private int quantity;

  //Equipment Associations
  private Laboratory laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, int aQuantity, Laboratory aLaboratory)
  {
    name = aName;
    quantity = aQuantity;
    boolean didAddLaboratory = setLaboratory(aLaboratory);
    if (!didAddLaboratory)
    {
      throw new RuntimeException("Unable to create equipment due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  

  public String getType()
  {
    return type;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public Laboratory getLaboratory()
  {
    return laboratory;
  }

  public boolean setLaboratory(Laboratory aLaboratory)
  {
    boolean wasSet = false;
    if (aLaboratory == null)
    {
      return wasSet;
    }

    Laboratory existingLaboratory = laboratory;
    laboratory = aLaboratory;
    if (existingLaboratory != null && !existingLaboratory.equals(aLaboratory))
    {
      existingLaboratory.removeEquipment(this);
    }
    laboratory.addEquipment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Laboratory placeholderLaboratory = laboratory;
    this.laboratory = null;
    placeholderLaboratory.removeEquipment(this);
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "type" + ":" + getType()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "laboratory = "+(getLaboratory()!=null?Integer.toHexString(System.identityHashCode(getLaboratory())):"null");
  }
}