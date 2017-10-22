package ca.mcgill.ecse321.urlms.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class URLMSController {

	private URLMS urlms;
	
	// Constructor of the controller
	public URLMSController(URLMS urlms) {
		this.urlms = urlms;
	}
	
	// Login method
	public boolean login(String email, String password) {
		List<Laboratory> labs = urlms.getLaboratories();
		// Tries to find a director or a staff member that matches the input.
		if(urlms.numberOfLaboratories() == 0) {
			List<Director> dirs = urlms.getDirectors();
			for (Director dir : dirs) {
				if(dir.getEmail().equals(email) && dir.getPassword().equals(password)) {
					return true;
				}
			}
		}
		else {
			for (Laboratory lab : labs) {
				if(lab.getDirector().getEmail().equals(email) && lab.getDirector().getPassword().equals(password))
						return true;
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail().equals(email) && member.getPassword().equals(password))
						return true;	
				}
			}
		}
		// If it does not find a user, it returns false (failed login).
		return false;
	}
	
	// Logout method
	public boolean logout() {
		return PersistenceXStream.saveToXMLwithXStream(urlms);
	}
	
	// Add a new laboratory method
	public boolean addLaboratory(String name, String fieldOfStudy, Date startDate) {
		return false;
	}
	
	// Add a new staff member to a laboratory method
	public boolean addStaff(String name, String email, String password, StaffRole role) {
		return false;
	}
}
