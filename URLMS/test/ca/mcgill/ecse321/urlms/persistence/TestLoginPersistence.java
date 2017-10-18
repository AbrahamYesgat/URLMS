package ca.mcgill.ecse321.urlms.persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestLoginPersistence {

	private static String testEmail ="thing@urlms.ca";
	private static String testStaffEmail ="Staff@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Shane Mac";
	
	private URLMS urlms;
	
	@Before
	public void setUp() throws Exception {
		urlms = new URLMS();
		
		//Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms);
		Staff staffMember = new Staff(testStaffEmail, testPassword, testName); 
		urlms.addLaboratory("LabOne", "Test", new Date(2017, 10, 10), new Date(2017, 10, 10), true, dr);
		urlms.getLaboratory(0).addStaff(staffMember);
		
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
		//new File("output"+File.separator+"data.xml").delete();
	}

	@Test
	public void test() { // will test if both a director and staff member can log in successfully.

	    // clear the model in memory
	    urlms.delete();
	    assertEquals(false, urlms.hasDirectors());
	    assertEquals(false, urlms.hasLaboratories());

	    // load model
	    urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
	    if (urlms == null)
	        fail("Could not load file.");

	    // check participants
	    assertEquals(true, urlms.hasDirectors());
	    assertEquals(true, urlms.hasLaboratories());
	    assertEquals(true, urlms.getLaboratory(0).hasStaffs());
	    assertEquals(testEmail, urlms.getDirector(0).getEmail());
	    assertEquals(testPassword, urlms.getDirector(0).getPassword());
	    assertEquals(testName, urlms.getDirector(0).getName());
	    assertEquals(testStaffEmail, urlms.getLaboratory(0).getStaff(0).getEmail());
	    assertEquals(testPassword, urlms.getLaboratory(0).getStaff(0).getPassword());
	    assertEquals(testName, urlms.getLaboratory(0).getStaff(0).getName());
	}

}
