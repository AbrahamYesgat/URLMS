package ca.mcgill.ecse321.appurlms;

import android.support.test.InstrumentationRegistry;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestAddLaboratory {
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
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMS.getInstance();
		// Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms);

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
		sysC.login(testEmail, testPassword);

		//Case 1: Simple add lab
		assertEquals(true, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));

		//Case 2: Conflicting lab names.
		assertEquals(false, sysC.addLaboratory("name", "study", new Date(2017, 10, 10)));
		sysC.addStaff(testStaffName, testStaffEmail, testStaffPassword, role);
		sysC.logout();

		//Case 3: Add lab by staff member.
		sysC.login(testStaffEmail, testStaffPassword);
		assertEquals(false, sysC.addLaboratory("value", "value", new Date(2017, 10, 10)));
	}

}
