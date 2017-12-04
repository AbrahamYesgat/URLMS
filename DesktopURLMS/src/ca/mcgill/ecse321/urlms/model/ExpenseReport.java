/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.1-f40f105-3613 modeling language!*/

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
  private String expense;
  private double amount;

  //Autounique Attributes
  private int id;

  //ExpenseReport Associations
  private Laboratory laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ExpenseReport(String aExpense, Laboratory aLaboratory)
  {
    expense = aExpense;
    amount = 0;
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

  public boolean setExpense(String aExpense)
  {
    boolean wasSet = false;
    expense = aExpense;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmount(double aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public String getExpense()
  {
    return expense;
  }

  public double getAmount()
  {
    return amount;
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
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "expense" + ":" + getExpense()+ "," +
            "amount" + ":" + getAmount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "laboratory = "+(getLaboratory()!=null?Integer.toHexString(System.identityHashCode(getLaboratory())):"null");
  }
}