package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestURLMSController {

	private URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
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
		assertEquals(false, urlms.hasDirectors());

		String name = "Oscar";

		  URLMSController sysC = new URLMSController(urlms);
		  //sysC.login();

		  // check model in memory
		 // assertEquals(1, rm.getParticipants().size());
		  //assertEquals(name, rm.getParticipant(0).getName());
		  //assertEquals(0, rm.getEvents().size());
		  //assertEquals(0, rm.getRegistrations().size());

		  //rm = (RegistrationManager) PersistenceXStream.loadFromXMLwithXStream();

		  // check file contents
		  //assertEquals(1, rm.getParticipants().size());
		  //assertEquals(name, rm.getParticipant(0).getName());
		  //assertEquals(0, rm.getEvents().size());
		  //assertEquals(0, rm.getRegistrations().size());
		fail("Not yet implemented");
	}

}
