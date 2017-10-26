package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestLogout {
	
	private static String testEmail ="director@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Director";
	
	private static String testStaffEmail ="staff@urlms.ca";
	private static String testStaffPassword ="password1";
	private static String testStaffName ="Member";
	
	private static URLMS urlms;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMS.getInstance();
		
		// Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms); 
		// Create data file
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {
		URLMSController sysC = new URLMSController(urlms);
		// Tests if the director logout works
	  	assertEquals(true, sysC.logout());
	  	assertEquals(null, sysC.getUser());
	    
	  	Staff member = new Staff(testStaffEmail,testStaffPassword,testStaffName);
	  	sysC.setActiveUser(member);
	  	
	  	//Tests if the staff logout works
	  	assertEquals(member, sysC.getUser());
	  	assertEquals(true, sysC.logout());
	  	assertEquals(null, sysC.getUser());
	    
	  	Laboratory lab = new Laboratory("name", "study", new Date(2017, 10, 10), true, urlms, dr);
	  	sysC.setActiveLaboratory(lab);
	  	lab.addStaff(member);
	  	
	  	//Tests if logout resets the active laboratory
	  	
	  	
	}

}
