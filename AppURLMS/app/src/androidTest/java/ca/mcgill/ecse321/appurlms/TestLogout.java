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
import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestLogout {
	
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

		//Case 1: Director logout
		sysC.login(testEmail, testPassword);
	  	assertEquals(true, sysC.logout());
	  	assertEquals(null, sysC.getActiveUser());
	    
	  	Laboratory lab = new Laboratory("name", "study", new Date(2017, 10, 10), true, urlms, urlms.getDirector(0));
	  	Staff member = new Staff(testStaffEmail,testStaffPassword,testStaffName, role, lab);
	  	sysC.setActiveUser(member);
	  	sysC.setActiveLaboratory(lab);
	  	sysC.login(testStaffEmail, testStaffPassword);

	  	//Case 2: Staff logout.
	  	assertEquals(member, sysC.getActiveUser());
	  	assertEquals(lab, sysC.getActiveLaboratory());
	  	assertEquals(true, sysC.logout());
	  	assertEquals(null, sysC.getActiveUser());
	  	assertEquals(null, sysC.getActiveLaboratory());
	}

}
