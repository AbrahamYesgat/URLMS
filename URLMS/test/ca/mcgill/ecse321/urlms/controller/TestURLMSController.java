package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.*;

public class TestURLMSController {
	
	private static String testEmail ="thing@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Shane Mac";
	
	private URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
	}

	@Before
	public void setUp() throws Exception {
		urlms = new URLMS();
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {
		Director dr = new Director(testEmail, testPassword, testName, urlms);
		URLMSController sysC = new URLMSController(urlms);
		
	  	assertEquals(true, sysC.login(testEmail, testPassword));
	  	assertEquals(false, sysC.login("random", "random"));
	  	
	  	Laboratory lab = new Laboratory("name", "study", new Date(2017, 10, 10), new Date(2017, 11, 11) , true, urlms, dr);
	  	Staff member = new Staff("username","password1","Sahil");
	  	lab.addStaff(member);
	  	
	  	assertEquals(true, sysC.login(testEmail, testPassword));
	  	assertEquals(true, sysC.login("username", "password1"));
	  	assertEquals(false, sysC.login("not", "right"));
	}
}
