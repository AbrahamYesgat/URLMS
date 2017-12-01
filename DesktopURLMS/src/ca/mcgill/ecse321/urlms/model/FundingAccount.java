/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse321.urlms.model;

// line 61 "../../../../../URLMS.ump"
public class FundingAccount
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FundingAccount Attributes
  private double currentBalance;
  private int accountNumber;

  //FundingAccount Associations
  private Laboratory laboratory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FundingAccount(double aCurrentBalance, int aAccountNumber, Laboratory aLaboratory)
  {
    currentBalance = aCurrentBalance;
    accountNumber = aAccountNumber;
    boolean didAddLaboratory = setLaboratory(aLaboratory);
    if (!didAddLaboratory)
    {
      throw new RuntimeException("Unable to create fundingAccount due to laboratory");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentBalance(double aCurrentBalance)
  {
    boolean wasSet = false;
    currentBalance = aCurrentBalance;
    wasSet = true;
    return wasSet;
  }

  public boolean setAccountNumber(int aAccountNumber)
  {
    boolean wasSet = false;
    accountNumber = aAccountNumber;
    wasSet = true;
    return wasSet;
  }

  public double getCurrentBalance()
  {
    return currentBalance;
  }

  public int getAccountNumber()
  {
    return accountNumber;
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
      existingLaboratory.removeFundingAccount(this);
    }
    laboratory.addFundingAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Laboratory placeholderLaboratory = laboratory;
    this.laboratory = null;
    placeholderLaboratory.removeFundingAccount(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "currentBalance" + ":" + getCurrentBalance()+ "," +
            "accountNumber" + ":" + getAccountNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "laboratory = "+(getLaboratory()!=null?Integer.toHexString(System.identityHashCode(getLaboratory())):"null")
     + outputString;
  }
}