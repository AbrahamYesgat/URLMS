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
	
	//Need to figure out how expenses will work
	public boolean addExpenses(String data, String note, double expenses) {
		
		if(expenses < 0)
			expenses = expenses * (-1.0);
		
		
		
		return false;
	}
	
	
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
	
	public String viewWeeklyProgressReport(int idNumber) {
		String message;
		double result=0;
		List<ProgressUpdate> ProUps = activeLab.getProgressUpdates();
		for(ProgressUpdate PU : ProUps) {
			if(PU.getId()==(idNumber)) {
				return PU.getReport();
			}
		}
		return "Requested Weekly Progress Report cannot be found!";
	}
	
	
	
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
	
	public boolean updateLab(String name, Date startDate, boolean isActive) {
		if(activeLab.getName().equalsIgnoreCase(name)) {
			activeLab.setActive(isActive);
			activeLab.setStartDate(startDate);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		if(activeUser instanceof Director) {
			if(urlms.hasLaboratories()) {
				List<Laboratory> labs = urlms.getLaboratories();
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

	public boolean addExistingStaff(String email) {
        if(activeUser instanceof Director) {
            List<Laboratory> labs = urlms.getLaboratories();
            for (Laboratory lab : labs) {
                for (Staff member : lab.getStaffs()) {
                    if(member.getEmail().equalsIgnoreCase(email)) {
                        activeLab.addStaff(member);
                        return PersistenceXStream.saveToXMLwithXStream(urlms);
                    }
                }
            }
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
	 * @param String of the equipment name 
	 * @return True if the database successfully added the equipment to the XML file, false if it did not work (if the person is not director)
	 */
	public boolean createEquipment(String equipment, int quantity) {
		if(quantity < 0) {
			return false;
		}
		equipment=equipment.trim();
		if (equipment.equals(" ") || equipment.equals("")) return false;

		URLMS urlms = URLMS.getInstance();
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
	
	
	
	public boolean createSupplies(String supplies, int quantity) {
		
		if(quantity < 0) {
			return false;
		}
		supplies=supplies.trim();
		if (supplies.equals(" ") || supplies.equals("")) return false;

		
		URLMS urlms = URLMS.getInstance();
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
	public boolean deleteLab(Director Dir, Laboratory Lab) {
		if (activeUser instanceof Director) {
		List<Laboratory>DLabs=Dir.getLaboratories();
		for( Laboratory lab : DLabs) {
			if(lab.getName().equalsIgnoreCase(Lab.getName())){
				lab.delete();
				return true;
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
