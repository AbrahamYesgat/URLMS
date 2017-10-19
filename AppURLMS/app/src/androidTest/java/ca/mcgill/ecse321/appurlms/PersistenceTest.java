package ca.mcgill.ecse321.appurlms;

import android.support.test.runner.AndroidJUnit4;
import org.junit.*;
import org.junit.runner.RunWith;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

@RunWith(AndroidJUnit4.class)
public class PersistenceTest {
    // Tests values
    private static String testEmail ="director@urlms.ca";
    private static String testStaffEmail ="staff@urlms.ca";
    private static String testPassword ="password";
    private static String testDirName ="Director";
    private static String testStaffName ="Staff";

    private URLMS urlms;

    @Before
    public void setUp() {
        urlms = new URLMS();

        // Create participants
        Director dr = new Director(testEmail, testPassword, testDirName, urlms);
        urlms.addLaboratory("LabOne", "Test", new Date(2017, 10, 10), new Date(2017, 10, 10), true, dr);
        Staff staffMember = new Staff(testStaffEmail, testPassword, testStaffName);
        urlms.getLaboratory(0).addStaff(staffMember);

        // Create data file
        PersistenceXStream.initializeURLMS("/data.xml");
        PersistenceXStream.saveToXMLwithXStream(urlms);
    }

    @After
    public void tearDown() {
        urlms.delete();
    }

    @Test
    public void test() {
        // Clear the model in memory
        urlms.delete();
        assertEquals(false, urlms.hasDirectors());
        assertEquals(false, urlms.hasLaboratories());

        // Load model
        urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
        if (urlms == null)
            fail("Could not load file.");

        // Check participants (Staff and director)
        assertEquals(true, urlms.hasDirectors());
        assertEquals(true, urlms.hasLaboratories());
        assertEquals(true, urlms.getLaboratory(0).hasStaffs());
        assertEquals(testEmail, urlms.getDirector(0).getEmail());
        assertEquals(testPassword, urlms.getDirector(0).getPassword());
        assertEquals(testDirName, urlms.getDirector(0).getName());
        assertEquals(testStaffEmail, urlms.getLaboratory(0).getStaff(0).getEmail());
        assertEquals(testPassword, urlms.getLaboratory(0).getStaff(0).getPassword());
        assertEquals(testStaffName, urlms.getLaboratory(0).getStaff(0).getName());
    }
}
