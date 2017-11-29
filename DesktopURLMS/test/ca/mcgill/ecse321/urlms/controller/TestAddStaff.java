package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestAddStaff {

	private static String testEmail ="director@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Director";
	
	private static String testStaffEmail ="staff@urlms.ca";
	private static String testStaffPassword ="password1";
	private static String testStaffName ="Member";
	private static StaffRole role = StaffRole.ResearchAssociate;
	
	private static URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		urlms = URLMS.getInstance();
		// Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms); 
		// Create data file
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {
		URLMSController sysC = new URLMSController(urlms);
		//Tests successful addStaff
		sysC.login(testEmail, testPassword);
		sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
		assertEquals(true, sysC.addStaff(testStaffEmail, testStaffPassword, testName, role));
		assertEquals(false, sysC.addStaff(testStaffEmail, testStaffPassword, testName, role));
		sysC.logout();
		
		//Tests unsuccessful addStaff (if staff tries to add staff)
		sysC.login(testStaffEmail, testStaffPassword);
		assertEquals(false, sysC.addStaff("value", "value", "value", role));
	}

}
