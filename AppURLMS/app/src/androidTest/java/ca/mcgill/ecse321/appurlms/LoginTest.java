package ca.mcgill.ecse321.appurlms;

import android.support.test.runner.AndroidJUnit4;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.*;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
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
    }

    @After
    public void tearDown() {
        urlms.delete();
    }

    @Test
    public void test() {
        Director dr = new Director(testEmail, testPassword, testName, urlms);
        URLMSController sysC = new URLMSController(urlms);

        // Case 1: Successful director login
        assertEquals(true, sysC.login(testEmail, testPassword));

        //Case 2: Not in the system
        assertEquals(false, sysC.login("random", "random"));

        Laboratory lab = new Laboratory("name", "study", new Date(2017, 10, 10), true, urlms, dr);
        Staff member = new Staff(testStaffEmail,testStaffPassword,testStaffName, role, lab);

        //Case 3: Successful staff login.
        assertEquals(true, sysC.login(testStaffEmail, testStaffPassword));

        //Case 4: Right email, wrong password.
        assertEquals(false, sysC.login(testStaffEmail, "no"));
        assertEquals(false, sysC.login(testEmail, "no"));

        //Case 5: Wrong email, right password.
        assertEquals(false, sysC.login("no", testPassword));
        assertEquals(false, sysC.login("no", testStaffPassword));
    }
}
