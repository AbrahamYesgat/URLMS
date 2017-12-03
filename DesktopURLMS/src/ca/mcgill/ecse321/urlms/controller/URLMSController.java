package ca.mcgill.ecse321.urlms.controller;

import java.sql.Date;
import java.util.ArrayList;
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
	 * @param email Desired email account (@urlms.ca by convention)
	 * @param password Desired password. No restrictions.
	 * @param name First and/or last name can be inputed.
	 * @return True if the database successfully saved the data. False for a saving error. 
	 */
	public boolean createDirector(String email, String password, String name) {
		List<Director> dirs = urlms.getDirectors();
		for (Director dir : dirs) {
			if(dir.getEmail().equalsIgnoreCase(email)) {
				return false;
			}
		} 
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
			}
		}
		
		//Make sure that the funds being added are positive
		if(intialFunds < 0) {
			System.out.println("Funds entered are negative, must be positive");
			return false;
		}
		//if account number is unique and funds are positive, you may create it
		new FundingAccount(intialFunds, accountNumber, activeLab);
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	
	}
	
	
	
	
	public boolean removeFunds(double removeFunds, int accountNumber) {
		
		//In case the value passed in is negative, switch it to postivie
		if(removeFunds < 0)
			removeFunds = removeFunds*-1;
				
		// First check that the user is a director of the current Lab
		if(activeUser instanceof Director) {
			// Get the account from the lab
			FundingAccount currAcct = activeLab.getFundingAccount(accountNumber);
			// Update the funds of the account 
			currAcct.setCurrentBalance(currAcct.getCurrentBalance() - removeFunds);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		return false;

	}
	
	
	
	
	public boolean addFunds(double newFunds, int accountNumber) {
		
		//Incase the parameter newFunds was negative, return and do not complete
		if(newFunds<0) {
			System.out.println("You cannot add a negative amount of funds");
			return false;
		}
	
		
		// First check that the user is a director of the current Lab
		if(activeUser instanceof Director) {
			// Get the account from the lab
			FundingAccount currAcct = activeLab.getFundingAccount(accountNumber);
			// Update the funds of the account 
			currAcct.setCurrentBalance(currAcct.getCurrentBalance() + newFunds);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		
		return false;
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
	
	/**
	 * Director adds a staff member to a selected laboratory. The parameters can be modified by the user within their profile. 
	 * @param name First and/or last name of the new member 
	 * @param email Director assigns the email of the staff member.
	 * @param password Director assigns a temporary password for the staff member.
	 * @param role Whether the member is a research associate or a research assistant. 
	 * @return True if the database successfully saved the laboratory. False if it did not and if a staff user tried to add a laboratory.
	 */
	public boolean addStaff(String email, String password, String name, Staff.StaffRole role) {
		if(activeUser instanceof Director) {
			List<Laboratory> labs = urlms.getLaboratories();
			for (Laboratory lab : labs) {
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equalsIgnoreCase(email)) {
						return false;	
					}
				}
			}
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
	public boolean createEquipment(String equipment, int quantity){

		String message = "";
		URLMS urlms = URLMS.getInstance();

		//checks the list if the item has already been create
		Iterator<Equipment> EquipmentIterator = activeLab.getEquipment().iterator();
		while (EquipmentIterator.hasNext()) {
			Equipment currEquipment = EquipmentIterator.next();
			if (currEquipment.getName().compareToIgnoreCase(equipment)==0){
				message = currEquipment.getName() + " was attempted to be added! This equipment type already exists. Please just change the amount needed.";
				System.out.println(message);
				return false;
			}
		
		
		equipment = equipment.trim();

		
		//add to system
		equipment = equipment.toUpperCase();
		Equipment AnEquip = new Equipment(equipment, quantity, activeLab);
		PersistenceXStream.saveToXMLwithXStream(urlms);
		return true;
		}
	return false;

	}
	
	public void addEquipments(String equipment,int quantity) {
		//if quantity > 0; add the amount, if quantity<0 take off that amount
		int result=0;
		Iterator<Equipment> EquipmentIterator = activeLab.getEquipment().iterator();
		while (EquipmentIterator.hasNext()) {
			Equipment currEquipment = EquipmentIterator.next();
			if (currEquipment.getName().compareToIgnoreCase(equipment)==0){
				result=currEquipment.getQuantity();
				currEquipment.setQuantity(result + quantity);
			}
		}
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	
	public boolean removeEquipments(String equipment, int quantity) {
		if(quantity<=0)
			return false;
		
		int currentQuant = 0;
		Iterator<Equipment> EquipmentIterator = activeLab.getEquipment().iterator();
		while (EquipmentIterator.hasNext()) {
		Equipment currEquipment = EquipmentIterator.next();
			if (currEquipment.getName().compareToIgnoreCase(equipment)==0){
				currentQuant=currEquipment.getQuantity();
					
					//delete the item if the quantity removed is the total
					if(currentQuant == quantity) {
						currEquipment.delete();
						System.out.println("Equipment deleted from system");
					}
					//edit the amount if the quantity is removed is partial
					else {
						currEquipment.setQuantity(currentQuant - quantity);
						System.out.println("Equipment quantity changed in system");
					}
				break;
			}
		}
		
		PersistenceXStream.saveToXMLwithXStream(urlms);
		
		return true;
		
	}
	
	public boolean createSupplies(String supplies, int quantity) {
		
		String message = "";
		URLMS urlms = URLMS.getInstance();
		
		List<Supplies> supply = activeLab.getSupplies();
		if(activeLab.hasSupplies()) {
			for(Supplies sup : supply) {
			
				if(sup.getName().equalsIgnoreCase(supplies)) {
					message = sup.getName() + " was attempted to be added! This equipment type already exists. Please just change the amount needed.";
					System.out.println(message);
					return false;
				}
			}
		}	
		supplies = supplies.trim();
			
		activeLab.addSupply(supplies, quantity);
		return PersistenceXStream.saveToXMLwithXStream(urlms);	
	}

	public boolean modifySupplies(String supplies, int quantity) {
		int result=0;
		List<Supplies> supply = activeLab.getSupplies();
		
		for(Supplies sup : supply) {
			if(sup.getName().equalsIgnoreCase(supplies)) {
				result=sup.getQuantity();
				sup.setQuantity(result + quantity);
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
		List<Laboratory> labs = urlms.getLaboratories();
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
