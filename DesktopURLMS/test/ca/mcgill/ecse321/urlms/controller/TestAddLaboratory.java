package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestAddLaboratory {
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
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMS.getInstance();
		// Create participant
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
	//Case 1: Director attempts and successfully to add a Laboratory 
	public void testDirAddLaboratory() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(urlms.hasLaboratories(),false);
		sysC.login(testEmail, testPassword);
		assertEquals(true, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
		sysC.addStaff(testStaffName, testStaffEmail, testStaffPassword, role);
		sysC.logout(); 
		urlms.delete();
	}
	
	@Test
	//Case 2: Staff member attempts to add Laboratory should result in fail
	public void testStaffAddLaboratory() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(urlms.hasLaboratories(),false);
		sysC.login(testStaffEmail, testStaffPassword);
		assertEquals(false, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
		sysC.logout();
		urlms.delete();
		
	}
	
	@Test
	//Case 3: Director attempts to add Laboratory that already exists
	public void testDirAddExistingLaboratory() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(urlms.hasLaboratories(),false);
		sysC.login(testEmail, testPassword);
		assertEquals(true, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
		assertEquals(false, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
		sysC.logout();
		urlms.delete();
		
	}
	//Case 4: Director attempts to add Laboratory that has as name NULL (i.e. space bar or empty string)
	public void testAddLaboratoryNULL() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(urlms.hasLaboratories(),false);
		sysC.login(testEmail, testPassword);
		assertEquals(false, sysC.addLaboratory("", "study", new Date(2017, 10, 10)));
		assertEquals(false, sysC.addLaboratory(" ", "study", new Date(2017, 10, 10)));
		sysC.logout();
		urlms.delete();
		
	}
	
	
	

}
