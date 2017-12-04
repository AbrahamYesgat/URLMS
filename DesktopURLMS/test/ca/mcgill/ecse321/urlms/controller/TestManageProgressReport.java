package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Date;
import java.util.Iterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Equipment;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestManageProgressReport {
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
	public void testManageWeeklyProgressReport(){
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
	
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		UCon.addStaff(testStaffEmail, testStaffPassword, "Alex", role);

		Laboratory tempLab=UCon.getActiveLaboratory();
		UCon.logout();
		UCon.login(testStaffEmail, testStaffPassword);
		UCon.setActiveLaboratory(tempLab);
		Laboratory activeLab=UCon.getActiveLaboratory();
		
		/* Test Begins here*/
		assertEquals(false, activeLab.hasProgressUpdates());//ensures there are no Equipment to begin with
		String ProgressRep = "This week we did the UMPLE MODEL, The software validation";
		UCon.createWeeklyProgressReport(ProgressRep, ProgressRep,new Date(2017, 10, 10) );
		PersistenceXStream.saveToXMLwithXStream(urlms);

		assertEquals(true, activeLab.hasProgressUpdates());//ensures there are no Equipment to begin with

		
	}
}