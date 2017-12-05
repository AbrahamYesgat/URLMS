package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Date;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestManageLab {
    // Set values for test cases
    private static String testEmail ="director@urlms.ca";
    private static String testPassword ="password";
    private static String testName ="Director";

    private static String testStaffEmail ="staff@urlms.ca";
    private static String testStaffPassword ="password1";
    private static String testStaffName ="Member";
    private static Staff.StaffRole role = Staff.StaffRole.ResearchAssociate;

    private URLMS urlms;

	@BeforeClass
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
    public void testManageLab() {
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
        sysC.setActiveLaboratory(urlms.getLaboratory(0));

        //Case 4: Staff tries to change the lab's name
        assertEquals(false, sysC.updateLab("anything", new Date(2017, 10, 10), true ));

    }
}

