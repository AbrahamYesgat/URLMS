/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.26.0-b05b57321 modeling language!*/

package ca.mcgill.ecse321.urlms.model;
import java.sql.Date;
import java.util.*;

// line 7 "../../../../../URLMS.ump"
public class Laboratory
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Laboratory Attributes
  private String name;
  private String fieldOfStudy;
  private Date startDate;
  private Date deadline;
  private boolean active;

  //Laboratory Associations
  private List<Equipment> equipment;
  private URLMS uRLMS;
  private List<ExpenseReport> expenseReports;
  private Director director;
  private List<Supplies> supplies;
  private List<Staff> staffs;
  private List<ProgressUpdate> progressUpdates;
  private List<FundingAccount> fundingAccounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Laboratory(String aName, String aFieldOfStudy, Date aStartDate, Date aDeadline, boolean aActive, URLMS aURLMS, Director aDirector)
  {
    name = aName;
    fieldOfStudy = aFieldOfStudy;
    startDate = aStartDate;
    deadline = aDeadline;
    active = aActive;
    equipment = new ArrayList<Equipment>();
    boolean didAddURLMS = setURLMS(aURLMS);
    if (!didAddURLMS)
    {
      throw new RuntimeException("Unable to create laboratory due to uRLMS");
    }
    expenseReports = new ArrayList<ExpenseReport>();
    boolean didAddDirector = setDirector(aDirector);
    if (!didAddDirector)
    {
      throw new RuntimeException("Unable to create laboratory due to director");
    }
    supplies = new ArrayList<Supplies>();
    staffs = new ArrayList<Staff>();
    progressUpdates = new ArrayList<ProgressUpdate>();
    fundingAccounts = new ArrayList<FundingAccount>();
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

  public boolean setFieldOfStudy(String aFieldOfStudy)
  {
    boolean wasSet = false;
    fieldOfStudy = aFieldOfStudy;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeadline(Date aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setActive(boolean aActive)
  {
    boolean wasSet = false;
    active = aActive;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getFieldOfStudy()
  {
    return fieldOfStudy;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getDeadline()
  {
    return deadline;
  }

  public boolean getActive()
  {
    return active;
  }

  public boolean isActive()
  {
    return active;
  }

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }

  public URLMS getURLMS()
  {
    return uRLMS;
  }

  public ExpenseReport getExpenseReport(int index)
  {
    ExpenseReport aExpenseReport = expenseReports.get(index);
    return aExpenseReport;
  }

  public List<ExpenseReport> getExpenseReports()
  {
    List<ExpenseReport> newExpenseReports = Collections.unmodifiableList(expenseReports);
    return newExpenseReports;
  }

  public int numberOfExpenseReports()
  {
    int number = expenseReports.size();
    return number;
  }

  public boolean hasExpenseReports()
  {
    boolean has = expenseReports.size() > 0;
    return has;
  }

  public int indexOfExpenseReport(ExpenseReport aExpenseReport)
  {
    int index = expenseReports.indexOf(aExpenseReport);
    return index;
  }

  public Director getDirector()
  {
    return director;
  }

  public Supplies getSupply(int index)
  {
    Supplies aSupply = supplies.get(index);
    return aSupply;
  }

  public List<Supplies> getSupplies()
  {
    List<Supplies> newSupplies = Collections.unmodifiableList(supplies);
    return newSupplies;
  }

  public int numberOfSupplies()
  {
    int number = supplies.size();
    return number;
  }

  public boolean hasSupplies()
  {
    boolean has = supplies.size() > 0;
    return has;
  }

  public int indexOfSupply(Supplies aSupply)
  {
    int index = supplies.indexOf(aSupply);
    return index;
  }

  public Staff getStaff(int index)
  {
    Staff aStaff = staffs.get(index);
    return aStaff;
  }

  public List<Staff> getStaffs()
  {
    List<Staff> newStaffs = Collections.unmodifiableList(staffs);
    return newStaffs;
  }

  public int numberOfStaffs()
  {
    int number = staffs.size();
    return number;
  }

  public boolean hasStaffs()
  {
    boolean has = staffs.size() > 0;
    return has;
  }

  public int indexOfStaff(Staff aStaff)
  {
    int index = staffs.indexOf(aStaff);
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

  public FundingAccount getFundingAccount(int index)
  {
    FundingAccount aFundingAccount = fundingAccounts.get(index);
    return aFundingAccount;
  }

  public List<FundingAccount> getFundingAccounts()
  {
    List<FundingAccount> newFundingAccounts = Collections.unmodifiableList(fundingAccounts);
    return newFundingAccounts;
  }

  public int numberOfFundingAccounts()
  {
    int number = fundingAccounts.size();
    return number;
  }

  public boolean hasFundingAccounts()
  {
    boolean has = fundingAccounts.size() > 0;
    return has;
  }

  public int indexOfFundingAccount(FundingAccount aFundingAccount)
  {
    int index = fundingAccounts.indexOf(aFundingAccount);
    return index;
  }

  public static int minimumNumberOfEquipment()
  {
    return 0;
  }

  public Equipment addEquipment(String aName, String aType, int aQuantity)
  {
    return new Equipment(aName, aType, aQuantity, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    Laboratory existingLaboratory = aEquipment.getLaboratory();
    boolean isNewLaboratory = existingLaboratory != null && !this.equals(existingLaboratory);
    if (isNewLaboratory)
    {
      aEquipment.setLaboratory(this);
    }
    else
    {
      equipment.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a laboratory
    if (!this.equals(aEquipment.getLaboratory()))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
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
      existingURLMS.removeLaboratory(this);
    }
    uRLMS.addLaboratory(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfExpenseReports()
  {
    return 0;
  }

  public ExpenseReport addExpenseReport(String aExpensePeriod)
  {
    return new ExpenseReport(aExpensePeriod, this);
  }

  public boolean addExpenseReport(ExpenseReport aExpenseReport)
  {
    boolean wasAdded = false;
    if (expenseReports.contains(aExpenseReport)) { return false; }
    Laboratory existingLaboratory = aExpenseReport.getLaboratory();
    boolean isNewLaboratory = existingLaboratory != null && !this.equals(existingLaboratory);
    if (isNewLaboratory)
    {
      aExpenseReport.setLaboratory(this);
    }
    else
    {
      expenseReports.add(aExpenseReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeExpenseReport(ExpenseReport aExpenseReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aExpenseReport, as it must always have a laboratory
    if (!this.equals(aExpenseReport.getLaboratory()))
    {
      expenseReports.remove(aExpenseReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addExpenseReportAt(ExpenseReport aExpenseReport, int index)
  {  
    boolean wasAdded = false;
    if(addExpenseReport(aExpenseReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenseReports()) { index = numberOfExpenseReports() - 1; }
      expenseReports.remove(aExpenseReport);
      expenseReports.add(index, aExpenseReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveExpenseReportAt(ExpenseReport aExpenseReport, int index)
  {
    boolean wasAdded = false;
    if(expenseReports.contains(aExpenseReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfExpenseReports()) { index = numberOfExpenseReports() - 1; }
      expenseReports.remove(aExpenseReport);
      expenseReports.add(index, aExpenseReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addExpenseReportAt(aExpenseReport, index);
    }
    return wasAdded;
  }

  public boolean setDirector(Director aDirector)
  {
    boolean wasSet = false;
    if (aDirector == null)
    {
      return wasSet;
    }

    Director existingDirector = director;
    director = aDirector;
    if (existingDirector != null && !existingDirector.equals(aDirector))
    {
      existingDirector.removeLaboratory(this);
    }
    director.addLaboratory(this);
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfSupplies()
  {
    return 0;
  }

  public Supplies addSupply(String aName, String aType, int aQuantity)
  {
    return new Supplies(aName, aType, aQuantity, this);
  }

  public boolean addSupply(Supplies aSupply)
  {
    boolean wasAdded = false;
    if (supplies.contains(aSupply)) { return false; }
    Laboratory existingLaboratory = aSupply.getLaboratory();
    boolean isNewLaboratory = existingLaboratory != null && !this.equals(existingLaboratory);
    if (isNewLaboratory)
    {
      aSupply.setLaboratory(this);
    }
    else
    {
      supplies.add(aSupply);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSupply(Supplies aSupply)
  {
    boolean wasRemoved = false;
    //Unable to remove aSupply, as it must always have a laboratory
    if (!this.equals(aSupply.getLaboratory()))
    {
      supplies.remove(aSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addSupplyAt(Supplies aSupply, int index)
  {  
    boolean wasAdded = false;
    if(addSupply(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSupplyAt(Supplies aSupply, int index)
  {
    boolean wasAdded = false;
    if(supplies.contains(aSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSupplies()) { index = numberOfSupplies() - 1; }
      supplies.remove(aSupply);
      supplies.add(index, aSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSupplyAt(aSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfStaffs()
  {
    return 0;
  }

  public boolean addStaff(Staff aStaff)
  {
    boolean wasAdded = false;
    if (staffs.contains(aStaff)) { return false; }
    staffs.add(aStaff);
    if (aStaff.indexOfLaboratory(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aStaff.addLaboratory(this);
      if (!wasAdded)
      {
        staffs.remove(aStaff);
      }
    }
    return wasAdded;
  }

  public boolean removeStaff(Staff aStaff)
  {
    boolean wasRemoved = false;
    if (!staffs.contains(aStaff))
    {
      return wasRemoved;
    }

    int oldIndex = staffs.indexOf(aStaff);
    staffs.remove(oldIndex);
    if (aStaff.indexOfLaboratory(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aStaff.removeLaboratory(this);
      if (!wasRemoved)
      {
        staffs.add(oldIndex,aStaff);
      }
    }
    return wasRemoved;
  }

  public boolean addStaffAt(Staff aStaff, int index)
  {  
    boolean wasAdded = false;
    if(addStaff(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveStaffAt(Staff aStaff, int index)
  {
    boolean wasAdded = false;
    if(staffs.contains(aStaff))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfStaffs()) { index = numberOfStaffs() - 1; }
      staffs.remove(aStaff);
      staffs.add(index, aStaff);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addStaffAt(aStaff, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfProgressUpdates()
  {
    return 0;
  }

  public ProgressUpdate addProgressUpdate(String aTitle, String aReportingPeriod, Staff aStaff)
  {
    return new ProgressUpdate(aTitle, aReportingPeriod, this, aStaff);
  }

  public boolean addProgressUpdate(ProgressUpdate aProgressUpdate)
  {
    boolean wasAdded = false;
    if (progressUpdates.contains(aProgressUpdate)) { return false; }
    Laboratory existingLaboratory = aProgressUpdate.getLaboratory();
    boolean isNewLaboratory = existingLaboratory != null && !this.equals(existingLaboratory);
    if (isNewLaboratory)
    {
      aProgressUpdate.setLaboratory(this);
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
    //Unable to remove aProgressUpdate, as it must always have a laboratory
    if (!this.equals(aProgressUpdate.getLaboratory()))
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

  public static int minimumNumberOfFundingAccounts()
  {
    return 0;
  }

  public FundingAccount addFundingAccount(double aCurrentBalance, int aAccountNumber)
  {
    return new FundingAccount(aCurrentBalance, aAccountNumber, this);
  }

  public boolean addFundingAccount(FundingAccount aFundingAccount)
  {
    boolean wasAdded = false;
    if (fundingAccounts.contains(aFundingAccount)) { return false; }
    Laboratory existingLaboratory = aFundingAccount.getLaboratory();
    boolean isNewLaboratory = existingLaboratory != null && !this.equals(existingLaboratory);
    if (isNewLaboratory)
    {
      aFundingAccount.setLaboratory(this);
    }
    else
    {
      fundingAccounts.add(aFundingAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFundingAccount(FundingAccount aFundingAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aFundingAccount, as it must always have a laboratory
    if (!this.equals(aFundingAccount.getLaboratory()))
    {
      fundingAccounts.remove(aFundingAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFundingAccountAt(FundingAccount aFundingAccount, int index)
  {  
    boolean wasAdded = false;
    if(addFundingAccount(aFundingAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFundingAccounts()) { index = numberOfFundingAccounts() - 1; }
      fundingAccounts.remove(aFundingAccount);
      fundingAccounts.add(index, aFundingAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFundingAccountAt(FundingAccount aFundingAccount, int index)
  {
    boolean wasAdded = false;
    if(fundingAccounts.contains(aFundingAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFundingAccounts()) { index = numberOfFundingAccounts() - 1; }
      fundingAccounts.remove(aFundingAccount);
      fundingAccounts.add(index, aFundingAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFundingAccountAt(aFundingAccount, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=equipment.size(); i > 0; i--)
    {
      Equipment aEquipment = equipment.get(i - 1);
      aEquipment.delete();
    }
    URLMS placeholderURLMS = uRLMS;
    this.uRLMS = null;
    placeholderURLMS.removeLaboratory(this);
    for(int i=expenseReports.size(); i > 0; i--)
    {
      ExpenseReport aExpenseReport = expenseReports.get(i - 1);
      aExpenseReport.delete();
    }
    Director placeholderDirector = director;
    this.director = null;
    placeholderDirector.removeLaboratory(this);
    for(int i=supplies.size(); i > 0; i--)
    {
      Supplies aSupply = supplies.get(i - 1);
      aSupply.delete();
    }
    ArrayList<Staff> copyOfStaffs = new ArrayList<Staff>(staffs);
    staffs.clear();
    for(Staff aStaff : copyOfStaffs)
    {
      aStaff.removeLaboratory(this);
    }
    for(int i=progressUpdates.size(); i > 0; i--)
    {
      ProgressUpdate aProgressUpdate = progressUpdates.get(i - 1);
      aProgressUpdate.delete();
    }
    for(int i=fundingAccounts.size(); i > 0; i--)
    {
      FundingAccount aFundingAccount = fundingAccounts.get(i - 1);
      aFundingAccount.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "fieldOfStudy" + ":" + getFieldOfStudy()+ "," +
            "active" + ":" + getActive()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "deadline" + "=" + (getDeadline() != null ? !getDeadline().equals(this)  ? getDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "uRLMS = "+(getURLMS()!=null?Integer.toHexString(System.identityHashCode(getURLMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "director = "+(getDirector()!=null?Integer.toHexString(System.identityHashCode(getDirector())):"null");
  }
}