package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestCreateDirector {

	// Set values for test cases
	private static String testEmail ="director@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Director";
	
	private URLMS urlms;
	
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
	//Case 1: The User inputs a email or Name that is simply a space i.e. an empty string 
	public void testCreateDirectorNULL() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(false, sysC.createDirector("", testPassword, testName));
		assertEquals(false, urlms.hasDirectors());
		urlms.delete();
	}
	
	@Test
	//Case 2: The user attempts to create a director that is already in the system 
	public void testAddExistingDirector() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(false, urlms.hasDirectors());
		assertEquals(true, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(false, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(true, urlms.hasDirectors());
		urlms.delete();
	}
	
	@Test
	//Case 3: The user successfully creates a director 
	public void testCrreateDir() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(false, urlms.hasDirectors());
		assertEquals(true, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(false, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(true, urlms.hasDirectors());
		urlms.delete();
	}
	
	
}
