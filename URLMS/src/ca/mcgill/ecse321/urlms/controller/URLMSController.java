package ca.mcgill.ecse321.urlms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

/**
 * This the controller of the URLMS system. 
 * @author Group 1
 *
 */
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
		
			for (Laboratory lab : labs) {
				if(lab.getDirector().getEmail().equals(email) && lab.getDirector().getPassword().equals(password)) {
						activeUser = lab.getDirector(); 
						return true;
				}
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equals(email) && member.getPassword().equals(password)) {
						activeUser = member;
						return true;	
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
			activeLab = ((Director) activeUser).addLaboratory(name, fieldOfStudy, startDate, true, urlms);
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
	public boolean addStaff(String name, String email, String password, Staff.StaffRole role) {
		if(activeUser instanceof Director) {
			new Staff(name, email, password, role, activeLab);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
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
}
