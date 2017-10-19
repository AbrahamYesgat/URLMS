package ca.mcgill.ecse321.appurlms;

import android.support.test.runner.AndroidJUnit4;
import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.*;

@RunWith(AndroidJUnit4.class)
public class LoginPersistenceTest {
    // Set values for test cases
    private static String testEmail ="director@urlms.ca";
    private static String testPassword ="password";
    private static String testName ="Director";

    private static String testStaffEmail ="staff@urlms.ca";
    private static String testStaffPassword ="password1";
    private static String testStaffName ="Member";

    private URLMS urlms;

    @Before
    public void setUp() {
        urlms = new URLMS();
    }

    @After
    public void tearDown() {
        urlms.delete();
    }

    @Test
    public void test() {
        Director dr = new Director(testEmail, testPassword, testName, urlms);
        URLMSController sysC = new URLMSController(urlms);
        // Tests if the director login works
        assertEquals(true, sysC.login(testEmail, testPassword));
        assertEquals(false, sysC.login("random", "random"));

        Laboratory lab = new Laboratory("name", "study", new Date(2017, 10, 10), new Date(2017, 11, 11) , true, urlms, dr);
        Staff member = new Staff(testStaffEmail,testStaffPassword,testStaffName);
        lab.addStaff(member);
        // Tests if the director and a staff member can login
        assertEquals(true, sysC.login(testEmail, testPassword));
        assertEquals(true, sysC.login(testStaffEmail, testStaffPassword));
        assertEquals(false, sysC.login("not", "right"));
    }
}
