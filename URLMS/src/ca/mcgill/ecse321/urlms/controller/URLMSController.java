package ca.mcgill.ecse321.urlms.controller;

import java.util.ArrayList;

import ca.mcgill.ecse321.urlms.model.*;

public class URLMSController {

	private URLMS urlms;
	
	public URLMSController(URLMS urlms) {
		this.urlms = urlms;
	}
	public boolean login(String email, String password) {
		ArrayList<Laboratory> labs = (ArrayList<Laboratory>)urlms.getLaboratories();
		if(urlms.numberOfLaboratories() == 0) {
			ArrayList<Director> dirs = (ArrayList<Director>)urlms.getDirectors();
			for (Director dir : dirs) {
				if(dir.getEmail() == email && dir.getPassword() == password)
					return true;
			}
		}
		else {
			for (Laboratory lab : labs) {
				for (Staff member : lab.getStaffs()) {
					if(member.getEmail() == email && member.getPassword() == password)
						return true;	
				}
			}
		}
		return false;
	}
}
