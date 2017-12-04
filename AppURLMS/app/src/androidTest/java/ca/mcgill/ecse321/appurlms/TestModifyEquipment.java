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
public class TestModifyEquipment {
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
        sysC.createEquipment("computer", 10);

        //Case 1: Director add equipment of the same type
        assertEquals(true, sysC.modifyEquipment("computer", 10));

        //Case 2: Director remove equipment of the same type
        assertEquals(true, sysC.modifyEquipment("computer", -5));

        //Case 3: Director add/remove equipment of a non existent type.
        assertEquals(false, sysC.modifyEquipment("wrong", 10));
        assertEquals(false, sysC.modifyEquipment("wrong", -10));

        //Case 4: Director removes more than total amount of equipment
        assertEquals(true, sysC.modifyEquipment("computer", -50));
        assertEquals(0, sysC.getActiveLaboratory().getEquipment(0).getQuantity());

        sysC.logout();
        sysC.login(testStaffEmail, testStaffPassword);
        Laboratory test = urlms.getLaboratory(0);
        sysC.setActiveLaboratory(test);

        //Case 5: Repeat all cases with staff member.
        //Case 5.1: Staff add equipment of the same type
        assertEquals(true, sysC.modifyEquipment("computer", 10));

        //Case 5.2: Staff remove equipment of the same type
        assertEquals(true, sysC.modifyEquipment("computer", -5));

        //Case 5.3: Staff add/remove equipment of a non existent type.
        assertEquals(false, sysC.modifyEquipment("wrong", 10));
        assertEquals(false, sysC.modifyEquipment("wrong", -10));

        //Case 5.4: Staff removes more than total amount of equipment
        assertEquals(true, sysC.modifyEquipment("computer", -50));
        assertEquals(0, sysC.getActiveLaboratory().getEquipment(0).getQuantity());
    }
}
