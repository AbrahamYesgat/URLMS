package ca.mcgill.ecse321.urlms.login;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestPersistence {

	private static String testEmail ="thing@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Shane Mac";
	
	private URLMS urlms;
	
	@Before
	public void setUp() throws Exception {
		urlms = new URLMS();
		
		//Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms);
		
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {

	    // clear the model in memory
	    urlms.delete();
	    assertEquals(false, urlms.hasDir());
	    assertEquals(false, urlms.hasLaboratory());

	    // load model
	    urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
	    if (urlms == null)
	        fail("Could not load file.");

	    // check participants
	    assertEquals(true, urlms.hasDir());
	    assertEquals(false, urlms.hasLaboratory());
	    assertEquals(testEmail, urlms.getDir(0).getEmail());
	    assertEquals(testPassword, urlms.getDir(0).getPassword());
	    assertEquals(testName, urlms.getDir(0).getName());
	}

}
