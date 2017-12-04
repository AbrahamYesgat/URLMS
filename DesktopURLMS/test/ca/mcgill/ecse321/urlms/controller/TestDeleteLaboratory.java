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

public class TestDeleteLaboratory {
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
		// Create data file
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}
	
	@Test
	//Case 1: Director successfully deletes a lab.
	public void testDeleteLabDir() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(false, urlms.hasDirectors());
		assertEquals(true, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(false, urlms.hasLaboratories());
		assertEquals(true,sysC.login(testEmail,testPassword));
		assertEquals(true, sysC.addLaboratory("name", "fieldOfStudy", new Date(2017, 10, 10)));
		assertEquals(true, urlms.hasLaboratories());
		Director dir=sysC.getDirector(testEmail);
		assertEquals(true, sysC.deleteLab(dir,sysC.getActiveLaboratory()));
		assertEquals(false,urlms.hasLaboratories());
		urlms.delete();
	}
	
}
