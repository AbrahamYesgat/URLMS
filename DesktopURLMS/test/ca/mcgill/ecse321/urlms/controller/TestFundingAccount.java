package ca.mcgill.ecse321.urlms.controller;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

import static org.junit.Assert.assertEquals;


public class TestFundingAccount {
    // Set values for test cases
    private static String testEmail ="director@urlms.ca";
    private static String testPassword ="password";
    private static String testName ="Director";

    private static String testStaffEmail ="staff@urlms.ca";
    private static String testStaffPassword ="password1";
    private static String testStaffName ="Member";
    private static Staff.StaffRole role = Staff.StaffRole.ResearchAssociate;

    private URLMS urlms;

	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		urlms = URLMS.getInstance();
		// Create participant
		Director dr = new Director(testEmail, testPassword, testName, urlms); 
		// Create data file
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}
	
    @Test
    public void test() {
        URLMSController sysC = new URLMSController(urlms);
        sysC.createDirector(testEmail,testPassword,testName);
        sysC.login(testEmail, testPassword);
        sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
        sysC.addStaff(testStaffEmail, testStaffPassword, testStaffName, role);

        //Case 1: Successful create funding account
        assertEquals(true, sysC.createFundingAccount(1000, 10));

        //Case 2: Create funding account with same account number
        assertEquals(false, sysC.createFundingAccount(100, 10));

        //Case 3: Create funding account with 0 funds
        assertEquals(true, sysC.createFundingAccount(0, 11));

        //Case 4: Create funding account with negative funds
        assertEquals(false, sysC.createFundingAccount(-1, 12));

        //Case 5: Create funding account with large funds
        assertEquals(true, sysC.createFundingAccount(999999999, 13));

        //Case 6: Created funding account with negative ID
        assertEquals(false, sysC.createFundingAccount(1, -1));

        //Case 7: Create funding account with ID 0
        assertEquals(true, sysC.createFundingAccount(1, 0));

        sysC.logout();
        sysC.login(testStaffEmail, testStaffPassword);
        Laboratory test = urlms.getLaboratory(0);
        sysC.setActiveLaboratory(test);

        //Case 8: Staff creates an funding account
        assertEquals(false, sysC.createFundingAccount(1, 14));
        sysC.logout();
        urlms.delete();
    }

    @Test
    public void testFUNDS() {
        URLMSController sysC = new URLMSController(urlms);
        sysC.createDirector(testEmail,testPassword,testName);
        sysC.login(testEmail, testPassword);
        sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
        sysC.addStaff(testStaffEmail, testStaffPassword, testStaffName, role);
        sysC.createFundingAccount(100, 1);

        //Case 1: Director removes funds
        assertEquals(true, sysC.modifyFunds(-10, 1));

        //Case 2: Director removes a large amount of funds
        assertEquals(true, sysC.modifyFunds(-9999999, 1));

        //Case 3: Director adds funds
        assertEquals(true, sysC.modifyFunds(9999999, 1));

        //Case 4: Director adds/removes funds to non existing account.
        assertEquals(false, sysC.modifyFunds(-9999999, 2));
        assertEquals(false, sysC.modifyFunds(9999999, 3));

        sysC.logout();
        sysC.login(testStaffEmail, testStaffPassword);
        Laboratory test = urlms.getLaboratory(0);
        sysC.setActiveLaboratory(test);

        //Case 5: Staff tries to modify funds
        assertEquals(false, sysC.modifyFunds(-9999999, 1));
        assertEquals(false, sysC.modifyFunds(9999999, 1));

        //Case 6: Staff tries to modify funds of an non existing account.
        assertEquals(false, sysC.modifyFunds(-9999999, 2));
        assertEquals(false, sysC.modifyFunds(9999999, 3));
    }
}
