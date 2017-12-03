package ca.mcgill.ecse321.urlms.controller;

import java.io.File;
import java.sql.Date;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Equipment;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

public class TestManageEquipment {
	private static String testEmail ="director@urlms.ca";
	private static String testPassword ="password";
	private static String testName ="Director";
	private static String testStaffEmail ="staff@urlms.ca";
	private static String testStaffPassword ="password1";
	private static String testStaffName ="Member";
	private static StaffRole role = StaffRole.ResearchAssociate;
	
	
	private static URLMS urlms;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		urlms = URLMS.getInstance();
		// Create participants
		Director dr = new Director(testEmail, testPassword, testName, urlms); 
		// Create data file
		PersistenceXStream.initializeURLMS("output"+File.separator+"data.xml");
		PersistenceXStream.saveToXMLwithXStream(urlms);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		urlms.delete();
	}

	@Test
	public void test() throws InvalidInputException {
		URLMSController sysC = new URLMSController(urlms);
		Laboratory activeLab=sysC.getActiveLaboratory();
		
		sysC.login(testEmail, testPassword);
		sysC.addLaboratory("name", "study", new Date(2017, 10, 10));
	
		
		sysC.createEquipment("Computer", 3);
	//	sysC.createEquipment("ComPUTER", 6);
		//sysC.modifyEquipment("Cangaroo", 3);
		sysC.modifyEquipment("ComPuter", -1);
		sysC.modifyEquipment("ComPuter", -1);

//		sysC.addEquipments("Computer", 4);
	//	sysC.modifyEquipment("Computer", -1);
//		sysC.createEquipmentType("computer");


		sysC.logout();


	}

	  
}
