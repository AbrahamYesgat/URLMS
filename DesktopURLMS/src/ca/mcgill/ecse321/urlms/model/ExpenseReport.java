/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 17 "../../../../../URLMS.ump"
public class ExpenseReport
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ExpenseReport Attributes
  private String expensePeriod;

  //Autounique Attributes
  private int id;

  //ExpenseReport Associations
  private Laboratory laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ExpenseReport(String aExpensePeriod, Laboratory aLaboratory)
  {
    expensePeriod = aExpensePeriod;
    id = nextId++;
    boolean didAddLaboratory = setLaboratory(aLaboratory);
    if (!didAddLaboratory)
    {
      throw new RuntimeException("Unable to create expenseReport due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setExpensePeriod(String aExpensePeriod)
  {
    boolean wasSet = false;
    expensePeriod = aExpensePeriod;
    wasSet = true;
    return wasSet;
  }

  public String getExpensePeriod()
  {
    return expensePeriod;
  }

  public int getId()
  {
    return id;
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
      existingLaboratory.removeExpenseReport(this);
    }
    laboratory.addExpenseReport(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Laboratory placeholderLaboratory = laboratory;
    this.laboratory = null;
    placeholderLaboratory.removeExpenseReport(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "expensePeriod" + ":" + getExpensePeriod()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "laboratory = "+(getLaboratory()!=null?Integer.toHexString(System.identityHashCode(getLaboratory())):"null")
     + outputString;
  }
}