package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

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
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {
		URLMSController sysC = new URLMSController(urlms);
		assertEquals(true, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(true, sysC.login(testEmail, testPassword));
		urlms.delete();
	    // Load model
	    urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
	    if (urlms == null)
	        fail("Could not load file.");
	    assertEquals(true, sysC.login(testEmail, testPassword));
	}

}
