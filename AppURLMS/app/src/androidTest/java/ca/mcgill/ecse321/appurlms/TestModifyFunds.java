package ca.mcgill.ecse321.appurlms;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TestModifyFunds {
    // Set values for test cases
    private static String testEmail ="director@urlms.ca";
    private static String testPassword ="password";
    private static String testName ="Director";

    private static String testStaffEmail ="staff@urlms.ca";
    private static String testStaffPassword ="password1";
    private static String testStaffName ="Member";
    private static Staff.StaffRole role = Staff.StaffRole.ResearchAssociate;

    private URLMS urlms;

    @Before
    public void setUp() {
        urlms = URLMS.getInstance();

        // Create data file
        PersistenceXStream.initializeURLMS(InstrumentationRegistry.getTargetContext().getApplicationContext().getFilesDir().getAbsolutePath()+"/data.xml");
        PersistenceXStream.saveToXMLwithXStream(urlms);
    }

    @After
    public void tearDown() {
        urlms.delete();
    }

    @Test
    public void test() {
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
