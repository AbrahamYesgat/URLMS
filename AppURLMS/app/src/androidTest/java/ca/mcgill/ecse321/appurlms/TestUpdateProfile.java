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
public class TestUpdateProfile {
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
        sysC.createDirector(testEmail, testPassword, testName);
        sysC.login(testEmail, testPassword);
        sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
        sysC.addStaff(testStaffEmail, testStaffPassword, testStaffName, role);

        //Case 1: Director changes profile
        assertEquals(true, sysC.updateProfile("newEmail", "newPassword", "newName"));
        assertEquals("newEmail", sysC.getActiveUser().getEmail());
        assertEquals("newPassword", sysC.getActiveUser().getPassword());
        assertEquals("newName", sysC.getActiveUser().getName());
        sysC.logout();
        assertEquals(true, sysC.login("newEmail", "newPassword"));

        //Case 2: Conflicting emails
        assertEquals(false, sysC.updateProfile(testStaffEmail, "newPassword", "newName"));
        assertEquals(true, sysC.updateProfile(testEmail, testStaffPassword, testStaffName));
        sysC.logout();

        //Case 3: Staff changes profile
        sysC.login(testStaffEmail, testStaffPassword);
        assertEquals(true, sysC.updateProfile("newEmail", "newPassword", "newName"));
        assertEquals("newEmail", sysC.getActiveUser().getEmail());
        assertEquals("newPassword", sysC.getActiveUser().getPassword());
        assertEquals("newName", sysC.getActiveUser().getName());
        sysC.logout();
        assertEquals(true, sysC.login("newEmail", "newPassword"));

    }
}
