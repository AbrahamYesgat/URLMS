package ca.mcgill.ecse321.appurlms;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TestUpdateLab {
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
        sysC.createDirector("second", "second", "second");
        sysC.login(testEmail, testPassword);
        sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
        sysC.addStaff(testStaffEmail, testStaffPassword, testStaffName, role);

        //Case 1: Director successfully changes the lab
        assertEquals(true, sysC.updateLab("newName", new Date(2017, 10, 10), false));

        //Case 2: Director changes all attributes besides name
        assertEquals(true, sysC.updateLab("newName", new Date(2018, 11, 11), true));

        sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
        //Case 3: Director tries to change lab's name to another lab's name
        assertEquals(false, sysC.updateLab("newName", new Date(2017, 10, 10), true ));


        sysC.logout();
        sysC.login(testStaffEmail, testStaffPassword);
        Laboratory test = urlms.getLaboratory(0);
        sysC.setActiveLaboratory(test);

        //Case 4: Staff tries to change the lab's name
        assertEquals(false, sysC.updateLab("anything", new Date(2017, 10, 10), true ));

    }
}
