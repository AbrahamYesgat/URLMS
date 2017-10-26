package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class TestLogout {
	
	private static String testEmail ="director@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Director";
	private static URLMS urlms;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Director dr = new Director(testEmail, testPassword, testName, urlms);
		URLMSController sysC = new URLMSController(urlms);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
