package ca.mcgill.ecse321.urlms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class URLMSController {

	private URLMS urlms;
	private UserRole activeUser;
	private Laboratory activeLab;
	
	// Constructor of the controller
	public URLMSController(URLMS urlms) {
		this.urlms = urlms;
	}
	
	public void setURLMS(URLMS urlms) {
		this.urlms = urlms;
	}
	
	public URLMS getURLMS() {
		return this.urlms;
	}
	
	// Login method
	public boolean login(String email, String password) {
		List<Laboratory> labs = urlms.getLaboratories();
		// Tries to find a director or a staff member that matches the input.
		if(urlms.numberOfLaboratories() == 0) {
			List<Director> dirs = urlms.getDirectors();
			for (Director dir : dirs) {
				if(dir.getEmail().equals(email) && dir.getPassword().equals(password)) {
					activeUser = dir;
					return true;
				}
			}
		}
		else {
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
		}
		// If it does not find a user, it returns false (failed login).
		return false;
	}
	
	// Create a director method
	public boolean createDirector(String email, String password, String name) {
		new Director(email, password, name, urlms);
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	
	// Logout method
	public boolean logout() {
		activeUser = null;
		activeLab = null; 
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	
	// Add a new laboratory method
	public boolean addLaboratory(String name, String fieldOfStudy, Date startDate) {
		if(activeUser instanceof Director) {
			activeLab = ((Director) activeUser).addLaboratory(name, fieldOfStudy, startDate, true, urlms);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		return false;
	}
	
	// Add a new staff member to a laboratory method
	public boolean addStaff(String name, String email, String password, Staff.StaffRole role) {
		if(activeUser instanceof Director) {
			new Staff(name, email, password, role, activeLab);
			return PersistenceXStream.saveToXMLwithXStream(urlms);
		}
		return false;
	}
	
	public void setActiveLaboratory(Laboratory lab){
		activeLab = lab;
	}
	public void setActiveUser(UserRole user) {
		activeUser = user;
	}
	public UserRole getActiveUser() {
		return activeUser;
	}
	public Laboratory getActiveLaboratory() {
		return activeLab;
	}
}
