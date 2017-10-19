package ca.mcgill.ecse321.urlms.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

// This tests if the persistence works (specifically for the login values).
public class TestLoginPersistence {

	// Tests values
	private static String testEmail ="director@urlms.ca";
	private static String testStaffEmail ="staff@urlms.ca";
	private static String testPassword ="password";
	private static String testDirName ="Director";
	private static String testStaffName ="Staff";
	
	private URLMS urlms;
	
	@Before
	public void setUp() throws Exception {
		urlms = new URLMS();
		
		// Create participants
		Director dr = new Director(testEmail, testPassword, testDirName, urlms); 
		urlms.addLaboratory("LabOne", "Test", new Date(2017, 10, 10), new Date(2017, 10, 10), true, dr);
		Staff staffMember = new Staff(testStaffEmail, testPassword, testStaffName); 
		urlms.getLaboratory(0).addStaff(staffMember);
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
	    // Clear the model in memory
	    urlms.delete();
	    assertEquals(false, urlms.hasDirectors());
	    assertEquals(false, urlms.hasLaboratories());

	    // Load model
	    urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
	    if (urlms == null)
	        fail("Could not load file.");

	    // Check participants (Staff and director)
	    assertEquals(true, urlms.hasDirectors());
	    assertEquals(true, urlms.hasLaboratories());
	    assertEquals(true, urlms.getLaboratory(0).hasStaffs());
	    assertEquals(testEmail, urlms.getDirector(0).getEmail());
	    assertEquals(testPassword, urlms.getDirector(0).getPassword());
	    assertEquals(testDirName, urlms.getDirector(0).getName());
	    assertEquals(testStaffEmail, urlms.getLaboratory(0).getStaff(0).getEmail());
	    assertEquals(testPassword, urlms.getLaboratory(0).getStaff(0).getPassword());
	    assertEquals(testStaffName, urlms.getLaboratory(0).getStaff(0).getName());
	}

}
