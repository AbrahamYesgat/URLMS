package ca.mcgill.ecse321.urlms.controller;


import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Iterator;
import java.io.*;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

/**
 * This the controller of the URLMS system. 
 * @author Group 1
 *
 */
//CHRIS CAN YOU SEE THIS

public class URLMSController {

	private URLMS urlms;
	private UserRole activeUser;
	private Laboratory activeLab;
	
	/**
	 * Constructor for the controller.
	 * @param urlms Takes the URLMS object as a parameter in order to get data from the system and to modify it. 
	 */
	public URLMSController(URLMS urlms) {
		this.urlms = urlms;
	}
	
	/**
	 * Setter for the URLMS system.
	 * @param urlms If the URLMS system gets deleted it can change to where the controller points to the system. 
	 */
	public void setURLMS(URLMS urlms) {
		this.urlms = urlms;
	}
	
	/**
	 * Getter for the URLMS system. 
	 * @return The URLMS object.
	 */
	public URLMS getURLMS() {
		return this.urlms;
	}
	
	/**
	 * Checks if the entered parameters corresponds to a user in order to login to the system. 
	 * @param email Inputed email by the user.
	 * @param password Inputed password by the user. 
	 * @return True if it is a successful login. False if it is an unsuccessful login. 
	 */
	public boolean login(String email, String password) {
		List<Laboratory> labs = urlms.getLaboratories();
		// Tries to find a director or a staff member that matches the input.
		List<Director> dirs = urlms.getDirectors();
		for (Director dir : dirs) {
			if(dir.getEmail().equals(email) && dir.getPassword().equals(password)) {
				activeUser = dir;
				return true;
			}
		}

		if(urlms.hasLaboratories()) {
			for (Laboratory lab : labs) {
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equals(email) && member.getPassword().equals(password)) {
						activeUser = member;
						
						return true;	
					}
				}
			}
		}
		// If it does not find a user, it returns false (failed login).
		return false;
	}
	

	/**
	 * Is used for a director to sign up to the system. Creates a director account
	 * @param email Desired email account (@ urlms.ca by convention)
	 * @param password Desired password. No restrictions.
	 * @param name First and/or last name can be inputed.
	 * @return True if the database successfully saved the data. False for a saving error. 
	 */
	public boolean createDirector(String email, String password, String name) {
		email=email.trim();
		name=name.trim();
		List<Director> dirs = urlms.getDirectors();
		for (Director dir : dirs) {
			if(dir.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		} 
		if(email.equals(" ")|| email.equals("")) return false;
		
		urlms.addDirector(email, password, name);
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	

	/**
	 * Saves data and logs the user out of the system
	 * @return True if the database successfully saved the data. False otherwise.
	 */
	public boolean logout() {
		activeUser = null;
		activeLab = null; 
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	

	/**
	 * Director creates a laboratory he is managing. 
	 * @param name Name of the laboratory.
	 * @param fieldOfStudy The laboratory's field of study 
	 * @param startDate When the laboratory will begin research.
	 * @return True if the database successfully saved the laboratory. False if it did not and if a staff user tried to add a laboratory. 
	 */
	public boolean addLaboratory(String name, String fieldOfStudy, Date startDate) {
		name=name.trim();
		if(name.equals(" ") || name.equals("") );
		if(activeUser instanceof Director) {
			if(urlms.hasLaboratories()) {
				List<Laboratory> labs = urlms.getLaboratories();
				for (Laboratory lab : labs) {
					if(lab.getName().equals(name)){
						return false;
					}
				}
			}
			activeLab = ((Director) activeUser).addLaboratory(name, fieldOfStudy, startDate, true, urlms);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		return false;
	}
	

	/**
	 * Director creates a funding account for the lab he/she is currently managing. 
	 * @param initialFunds The initial funds that are to be added into this funding account
	 * @param accountNumber Unique funding account number that will be assigned an account number
	 * @return True if the account has been created with the correct input values, false if the inputed amount is either negative or if the account number has already been assigned elsewhere
	 */
	public boolean createFundingAccount(double intialFunds, int accountNumber) {
		
		//We want to verify that the account being created does not already exist in the system
		if(activeUser instanceof Director) {
			if(urlms.hasLaboratories()) {
				List<Laboratory> labs = urlms.getLaboratories();
				for(Laboratory lab : labs) {
					List<FundingAccount> fundAccounts = lab.getFundingAccounts();
					for(FundingAccount account : fundAccounts) {
						if(account.getAccountNumber() == accountNumber)
							return false;
					}
				}
				//Make sure that the funds being added are positive
				if(intialFunds < 0 || accountNumber < 0) {
					System.out.println("Funds entered are negative, must be positive");
					return false;
				}
				//if account number is unique and funds are positive, you may create it
				activeLab.addFundingAccount(intialFunds, accountNumber);
				return PersistenceXStream.saveToXMLwithXStream(urlms);
			}
		}
		
		return false; 
	}
	
	/**
	 * Director can modify the funds in an existing funding account in the active lab 
	 * @param newFunds The initial funds that have been created in the lab are changed based on the value of the newFunds parameter. If newFunds is positive, funds are added and if newfunds are negative, funds are subtracted
	 * @param accountNumber Unique funding account number that will determine which funding account is being modified
	 * @return True if the account funds has been modified properly , false otherwise
	 */
	public boolean modifyFunds(double newFunds, int accountNumber) {
		String message;
		double result=0;
		List<FundingAccount> FundingAccounts = activeLab.getFundingAccounts();
		
		if(activeUser instanceof Director){
			for(FundingAccount FA : FundingAccounts) {
				if(FA.getAccountNumber()==(accountNumber)) {
					result=FA.getCurrentBalance() + newFunds;
					FA.setCurrentBalance(result);
					return PersistenceXStream.saveToXMLwithXStream(urlms);
				}
			}
		}
		
		return false; 
	}
	
	/**
	 * Staff has the capability of creating a weekly summary of what happened in the previous week (i.e. a weekly progress report) 
	 * @param Title The title of the weekly progress report
	 * @param report The actual report that the staff writes
	 * @param date The date that the staff is writing the weekly progress report
	 * @return True if the progress report was successfully created, false otherwise
	 */
	
	public boolean createWeeklyProgressReport(String Title, String report, Date date) {
		String reportPeriod = date.toString();
		Title += " " + reportPeriod; 
		if (activeUser instanceof Staff) {
			activeLab.addProgressUpdate(Title, report, (Staff)activeUser);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		System.out.println("User is not a Staff Member!");
		return false;
	}
	
	/**
	 * Member of the URLMS is allowed to view any weekly progress report of his/her requested Lab.  
	 * @param idNumber the progress report unique id Number
	 * @return The progress report on a success, error message on failure
	 */
	public String viewWeeklyProgressReport(int idNumber) {
		List<ProgressUpdate> ProUps = activeLab.getProgressUpdates();
		for(ProgressUpdate PU : ProUps) {
			if(PU.getId()==(idNumber)) {
				return PU.getReport();
			}
		}
		return "Requested Weekly Progress Report cannot be found!";
	}
	
	public ExpenseReport viewExpenseReport(int idNumber) {
		List<ExpenseReport> ExRep = activeLab.getExpenseReports();
		for(ExpenseReport ER : ExRep) {
			if(ER.getId()==(idNumber)) {
				return ER;
			}
		}
		return null;
	}
	/**
	 * Member of the URLMS can create an expense report based on the lab expenses 
	 * @param expenseReport the expense report that the user will write 
	 * @param price the price of the expense report
	 * @param day day of the date that the user will create the expense report
	 * @param month month of the date that the user will create the expense report
	 * @param year year of the date that the user will create the expense report
	 * @return The progress report on a success, error message on failure
	 */
	public boolean createExpenseReport(String expenseReport, double price, int day, int month, int year) {
		if(price < 0){
			return false;
		}
		String report = "Expenses for " + Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year) 
						+ System.lineSeparator() + expenseReport;
		ExpenseReport expense = activeLab.addExpenseReport(report); 
		expense.setAmount(price);
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	
	/**
	 * Member of the URLMS can update any piece of information currently in his/her profile
	 * @param Email the user can update the email of his account 
	 * @param password the user can update his/her account's password
	 * @return name the user can update the name in his/her account
	 */
	 public boolean updateProfile(String email, String password, String name) {
		
		//This checks is the email is unchanged, if so, only update password and Name
		if(activeUser.getEmail().equalsIgnoreCase(email)) {
			activeUser.setPassword(password);
			activeUser.setName(name);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		//Verify if the email address updated already exists in the system
		List<Laboratory> labs = urlms.getLaboratories();
		for (Laboratory lab : labs) {
			for (Staff member : lab.getStaffs()) {
				if(member.getEmail().equalsIgnoreCase(email)) {
					System.out.println("email exists already");
					return false;	
				}
			}
		}
		// Update all info
		activeUser.setEmail(email);
		activeUser.setPassword(password);
		activeUser.setName(name);
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	
	}
	/**
	 * A director can update the information currently in his/her laboratory
	 * @param name the director can update the name of his laboratory 
	 * @param startDate the user can update his/her laboratory's start date
	 * @param isActive the user can update if his lab is active or not
	 * @return true if the lab haas been properly updated, false otherwise
	 */
	 
	 public boolean updateLab(String name, Date startDate, boolean isActive) {
		if(activeLab.getName().equalsIgnoreCase(name)) {
			activeLab.setActive(isActive);
			activeLab.setStartDate(startDate);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		if(activeUser instanceof Director) {
			if(urlms.hasLaboratories()) {
				Director director = (Director)activeUser;
				List<Laboratory> labs = director.getLaboratories();
				for (Laboratory lab : labs) {
					if(lab.getName().equals(name)){
						return false;
					}
				}
			}
			activeLab.setName(name);
			activeLab.setActive(isActive);
			activeLab.setStartDate(startDate);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		return false;
	}
	
	/**
	 * Director adds a staff member to a selected laboratory. The parameters can be modified by the user within their profile. 
	 * @param name First and/or last name of the new member 
	 * @param email Director assigns the email of the staff member.
	 * @param password Director assigns a temporary password for the staff member.
	 * @param role Whether the member is a research associate or a research assistant. 
	 * @return True if the database successfully saved the laboratory. False if it did not and if a staff user tried to add a laboratory.
	 */
	public boolean addStaff(String email, String password, String name, Staff.StaffRole role) {
		email=email.trim();
		if (email.equals(" ") || email.equals("")) return false;
		if(activeUser instanceof Director) {
			// First we verify that the email address does not exist for a current staff member in the system
			List<Laboratory> labs = urlms.getLaboratories();
			for (Laboratory lab : labs) {
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equalsIgnoreCase(email)) {
						return false;	
					}
				}
			}
			// Secondly we verify that the email address does not belong to a current director
			List <Director> directors= urlms.getDirectors();
			for(Director director : directors) {
				if(director.getEmail().equalsIgnoreCase(email))
					return false;
			}
			// It is a unique email address, therefore we can create the new staff
			new Staff(email, password, name, role, activeLab);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		return false;
	}
	/**
	 * The director has the power to add/assign a currently existing staff to his/her desired lab
	 * @param email the user's email that will be added to the lab 
	 * @return true on a success of adding a staff to a lab, false otherwise
	 */
	public boolean addExistingStaff(String email) {
		boolean validAdd = false;
		Staff validStaff = null;
        if(activeUser instanceof Director) {
            List<Laboratory> labs = urlms.getLaboratories();
            for (Laboratory lab : labs) {
                for (Staff member : lab.getStaffs()) {
                    if(member.getEmail().equalsIgnoreCase(email)) { 
                    	if(lab.getName().equalsIgnoreCase(activeLab.getName())){ // below ensures existing user does not exist in active lab
                    		return false;
                    	}
                    	else{
                    		validAdd = true;
                    		validStaff = member;
                    	}
                    }
                }
            }
        }
        if(validAdd && validStaff != null){
        	activeLab.addStaff(validStaff);
            return PersistenceXStream.saveToXMLwithXStream(urlms);
        }
        return false;
	}

	/**
	 * Director removes a staff member to the active laboratory. The parameters can be modified by the user within their profile. 
	 * @param email of the staff member being removed from the system 
	 * @return True if the database successfully removed the user from the active laboratory. False if it did not.
	 */
	
	public boolean removeStaff(String email) {
		if(activeUser instanceof Director) {
			Laboratory lab = activeLab;
			for (Staff member : lab.getStaffs()) {
				if(member.getEmail().equals(email)) {
					if(member.getLaboratories().size() == 1) {
						member.delete();
					}
					else {
						lab.removeStaff(member);
					}
					return PersistenceXStream.saveToXMLwithXStream(urlms);
				}
			}
		}
		return false;
	}
	
	/**
	 * Director is allowed to create equipment types for his labs, which he can later add or remove quantities to
	 * @param equipment name of the equipment  
	 * @return True if the database successfully added the equipment to the XML file, false if it did not work (if the person is not director)
	 */
	public boolean createEquipment(String equipment, int quantity) {
		if(quantity < 0) {
			return false;
		}
		equipment=equipment.trim();
		if (equipment.equals(" ") || equipment.equals("")) return false;

		List<Equipment> equipments = activeLab.getEquipment();
		if(activeLab.hasEquipment()) {
			for(Equipment equip : equipments) {
				if(equip.getName().equalsIgnoreCase(equipment)) {
					return false;
				}
			}
		}	
		equipment = equipment.trim();
		activeLab.addEquipment(equipment, quantity);
		return PersistenceXStream.saveToXMLwithXStream(urlms);	

	}

	/**
	 * Director is allowed to modify (i.e. add or remove) the amounts of equipment for his labs
	 * @param equipment name of the equipment  
	 * @param quantity the amount that the director wants to update the equipment by
	 * @return True if the database successfully added the equipment to the XML file, false if it did not work (if the person is not director)
	 */
	public boolean modifyEquipment(String equipment,int quantity) {
		int result=0;
		List<Equipment> equipments = activeLab.getEquipment();
		
		for(Equipment equip : equipments) {
			if(equip.getName().equalsIgnoreCase(equipment)) {
				result=equip.getQuantity()+quantity;
				if (result<=0) {
					equip.setQuantity(0);
					return PersistenceXStream.saveToXMLwithXStream(urlms);
				}
				equip.setQuantity(result);
				return PersistenceXStream.saveToXMLwithXStream(urlms);
			}
		}
		
		return false; 
	}
	
	/**
	 * Director is allowed to remove equipment types for his/her labs, meaning remove the entire existing category of that same equipment
	 * @param String equipment of the equipment name 
	 * @return True if the database successfully removed the equipment and saved the update to the XML file, false if it did not work.
	 */	
	public boolean removeEquipments(String equipment) {
		List<Equipment> equipments = activeLab.getEquipment();
		
		for(Equipment equip : equipments) {
			if(equip.getName().equalsIgnoreCase(equipment)) {
				equip.delete();
				return PersistenceXStream.saveToXMLwithXStream(urlms);
			}
		}
		
		return false; 
	}
	
	/**
	 * Director is allowed to create supplies types for his/her labs, which he can later add or remove quantities to
	 * @param supplies name of the supply  
	 * @return True if the database successfully added the supply to the XML file, false if it did not work
	 */
	public boolean createSupplies(String supplies, int quantity) {
		
		if(quantity < 0) {
			return false;
		}
		supplies=supplies.trim();
		if (supplies.equals(" ") || supplies.equals("")) return false;
		List<Supplies> supply = activeLab.getSupplies();
		if(activeLab.hasSupplies()) {
			for(Supplies sup : supply) {
				if(sup.getName().equalsIgnoreCase(supplies)) {
					return false;
				}
			}
		}
		
		activeLab.addSupply(supplies, quantity);
		return PersistenceXStream.saveToXMLwithXStream(urlms);	
	}

	/**
	 * Director is allowed to modify (i.e. add or remove) the amounts of Supplies for his/her labs
	 * @param supplies name of the supplies to be updated  
	 * @param quantity the amount that the director wants to update the supply amounts by
	 * @return True if the database successfully added the equipment to the XML file, false if it did not work (if the person is not director)
	 */
	public boolean modifySupplies(String supplies, int quantity) {
		int result=0;
		List<Supplies> supply = activeLab.getSupplies();
		for(Supplies sup : supply) {
			if(sup.getName().equalsIgnoreCase(supplies)) {
				result=sup.getQuantity() + quantity;
				sup.setQuantity(result);
				if (result<=0) {
					sup.setQuantity(0);
					return PersistenceXStream.saveToXMLwithXStream(urlms);
				}
				return PersistenceXStream.saveToXMLwithXStream(urlms);
			}
		}
		return false; 
	}
	
	/**
	 * Director is allowed to remove Supply types for his/her labs, meaning remove the entire existing category of that same equipment
	 * @param supplies name of the supplies that will be removed  
	 * @return True if the database successfully removed the supplies and saved the update to the XML file, false if it did not work.
	 */
	public boolean removeSupplies(String supplies) {
		List<Supplies> supply = activeLab.getSupplies();
		
		for(Supplies sup : supply) {
			if(sup.getName().equalsIgnoreCase(supplies)) {
				sup.delete();
				return PersistenceXStream.saveToXMLwithXStream(urlms);
			}
		}
		
		return false; 	
	}
	
	/**
	 * Director is allowed to delete an entire lab, meaning remove the entire existing lab and all its belongings
	 * @param director the director
	 * @param Lab the lab that is wished to be deleted 
	 * @return True if the database successfully removed the equipment and saved the update to the XML file, false if it did not work.
	 */
	public boolean deleteLab(Director Dir, Laboratory Lab) {
		if (activeUser instanceof Director) {
			List<Laboratory>DLabs=Dir.getLaboratories();
			for( Laboratory lab : DLabs) {
				if(lab.getName().equalsIgnoreCase(Lab.getName())){
					lab.delete();
					return PersistenceXStream.saveToXMLwithXStream(urlms);
				}	
			}
		}
		return false;
	}

	/**
	 * Allows to keep track of the laboratory currently selected by the user. 
	 * @param lab The laboratory selected
	 */
	public void setActiveLaboratory(Laboratory lab){
		activeLab = lab;
	}
	
	/**
	 * Allows the system to keep track of the current user that is logged in. 
	 * @param user The user that is logged in. 
	 */
	public void setActiveUser(UserRole user) {
		activeUser = user;
	}
	
	/**
	 * Getter for the user currently logged in. 
	 * @return The active user (object).
	 */
	public UserRole getActiveUser() {
		return activeUser;
	}
	
	/**
	 * Getter for the laboratory currently selected.
	 * @return The selected laboratory (object).
	 */
	public Laboratory getActiveLaboratory() {
		return activeLab;
	}
	
	/**
	 * Determines if user is a director or not.
	 * @param email User's email.
	 * @return director If user is a director, director is returned.
	 */
	public Director getDirector(String email){
	
		List<Director> dirs = urlms.getDirectors();
		for (Director dir : dirs) {
			if(dir.getEmail().equals(email)) {
				return dir;
			}
		}
		return null;
	}
	/**
	 * Determines if user is a staff member or not.
	 * @param email User's email.
	 * @return staff If user is a staff, staff is is returned.
	 */
	public Staff getStaffMember(String email){
		List<Laboratory> labs = urlms.getLaboratories();
		if(urlms.hasLaboratories()) {
			for (Laboratory lab : labs) {
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equals(email)) {
						return member;	
					}
				}
			}
		}
		return null;
	}
	
}
