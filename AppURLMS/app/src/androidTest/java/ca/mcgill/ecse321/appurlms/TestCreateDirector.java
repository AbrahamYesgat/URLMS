package ca.mcgill.ecse321.appurlms;

import android.support.test.InstrumentationRegistry;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
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
		PersistenceXStream.initializeURLMS(InstrumentationRegistry.getTargetContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
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
		assertEquals(false, sysC.createDirector(testEmail, testPassword, testName));
		assertEquals(false, sysC.createDirector(testEmail, "test", "test"));
		assertEquals(true, sysC.createDirector("email", testPassword, testName));
		urlms.delete();
	    // Load model
	    urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
	    if (urlms == null)
	        fail("Could not load file.");
	    sysC.setURLMS(urlms);
	    assertEquals(true, sysC.login(testEmail, testPassword));
	}

}
