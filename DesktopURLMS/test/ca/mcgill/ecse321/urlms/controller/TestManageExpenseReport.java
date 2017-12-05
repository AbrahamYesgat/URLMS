package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;
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
import ca.mcgill.ecse321.urlms.model.ExpenseReport;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestManageExpenseReport {
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
	public void testManageExpenses() {
	URLMSController sysC = new URLMSController(urlms);
	assertEquals(true, urlms.hasDirectors());
	assertEquals(true,sysC.login(testEmail,testPassword));
	
	//Case 1: Expense Report to be added to a lab 
	//assertEquals(true, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
	ExpenseReport ER= new ExpenseReport("ERPeriod", sysC.getActiveLaboratory());
	//assertEquals(true, sysC.createExpenseReport("ER", 23, 12, 2017));
	
	assertEquals(true,sysC.logout());
	urlms.delete();
	}
}
	