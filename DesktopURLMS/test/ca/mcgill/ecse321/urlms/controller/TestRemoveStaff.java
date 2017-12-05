package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.assertEquals;

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

public class TestRemoveStaff {
	
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
		//Tests successful removeStaff
		sysC.login(testEmail, testPassword);
		sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
		assertEquals(true, sysC.addStaff(testStaffEmail,testStaffPassword,"name",role));
		assertEquals(true, sysC.getActiveLaboratory().hasStaffs());
		assertEquals(true, sysC.removeStaff(testStaffEmail));
		assertEquals(false, sysC.getActiveLaboratory().hasStaffs());
		sysC.logout();

		//Tests unsuccessful removeStaff (if staff tries to add staff)
		sysC.login(testStaffEmail, testStaffPassword);
		assertEquals(false, sysC.addStaff("value", "value", "value", role));
	}

}
