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
public class TestDeleteLab {
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

        //Case 1: Successful delete of a directors lab
        assertEquals(true, sysC.deleteLab((Director) sysC.getActiveUser(), sysC.getActiveLaboratory()));

        //Case 2: Director attempts to delete a non existent lab
        assertEquals(false, sysC.deleteLab((Director)sysC.getActiveUser(), Laboratory.getWithName("nothing")));

        sysC.createDirector(testStaffEmail, testStaffPassword, testStaffName);
        sysC.login(testStaffEmail, testStaffPassword);
        sysC.addLaboratory("Other", "study", new Date(2017, 10, 10));
        Laboratory otherLab = ((Director) sysC.getActiveUser()).getLaboratory(0);
        sysC.logout();
        sysC.login(testEmail, testPassword);
        assertEquals(testEmail, sysC.getActiveUser().getEmail());

        //Case 3: Director tries to delete another directors lab
        assertEquals(false, sysC.deleteLab((Director)sysC.getActiveUser(), otherLab));
    }
}
