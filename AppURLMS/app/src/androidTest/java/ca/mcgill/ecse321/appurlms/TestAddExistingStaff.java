package ca.mcgill.ecse321.appurlms;

import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

import static org.junit.Assert.assertEquals;

public class TestAddExistingStaff {

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
		urlms = URLMS.getInstance();
		// Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms);

		// Create data file
		PersistenceXStream.initializeURLMS(InstrumentationRegistry.getTargetContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() {
		URLMSController sysC = new URLMSController(urlms);
		sysC.login(testEmail, testPassword);
		sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
		sysC.addStaff(testStaffEmail, testStaffPassword, testName, role);
		sysC.addLaboratory("other", "study",new Date(2017, 10, 10) );
		sysC.addStaff("other", "other", "other", role);
		assertEquals("other", sysC.getActiveLaboratory().getName());

		//Case 1: Successfully adding a staff member to the active lab.
		assertEquals(true, sysC.addExistingStaff(testStaffEmail));

		//Case 2: Adding a director as a staff member.
		assertEquals(false, sysC.addExistingStaff(testEmail));

		//Case 3: Adding a staff member already part of the lab.
		assertEquals(false, sysC.addExistingStaff("other"));
	}

}
