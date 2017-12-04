package ca.mcgill.ecse321.urlms.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.sql.Date;
import java.util.Iterator;
import java.util.Random;

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
	public void testCreateEquipment() throws InvalidInputException {
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		Laboratory activeLab = UCon.getActiveLaboratory(); 
		
		/* Test Begins here*/
		assertEquals(false, activeLab.hasEquipment());//ensures there are no Equipment to begin with
		String Equipment = "TestEquipment";
		UCon.createEquipment(Equipment,1);
		assertEquals(1,activeLab.getEquipment().size());
		assertEquals(Equipment ,activeLab.getEquipment(0).getName());
		UCon.logout();

	}
//	
//	@Test
//	public void testNegativeEquipmentAmount() throws InvalidInputException {
//		String error="";
//		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//		UCon.login(testEmail, testPassword);
//		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//		Laboratory activeLab = UCon.getActiveLaboratory(); 
//		
//		/* Test Begins here*/
//		assertEquals(false, activeLab.hasEquipment());//ensures there are no Equipment to begin with
//		String Equipment = "TestEquip";
//		UCon.createEquipment(Equipment,1);
//		try {
//			UCon.modifyEquipment(Equipment, -2);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//		}
//		
//		assertEquals("There are no more " +Equipment+"s in this Laboratory!", error);
//		assertEquals(0,activeLab.getEquipment(0).getQuantity());		
//		
//	}
//
//	@Test
//	public void testAddEquipment() throws InvalidInputException{
//		String error="";
//		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//		UCon.login(testEmail, testPassword);
//		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//		Laboratory activeLab = UCon.getActiveLaboratory(); 
//	
//		 /* Test Begins Here*/
//		assertEquals(false, activeLab.hasEquipment());//ensures there are no Equipment to begin with
//		String Equipment = "TestEquip";
//		Random rand= new Random();
//		int randA=rand.nextInt(50);
//		int randB=rand.nextInt(50);
//		UCon.createEquipment(Equipment,randA);
//		UCon.modifyEquipment(Equipment, randB);
//		assertEquals(randA+randB, activeLab.getEquipment(0).getQuantity());
//		
//	}
//	
//	@Test
//	public void testSubtractEquipment() throws InvalidInputException{
//		String error="";
//		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//		UCon.login(testEmail, testPassword);
//		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//		Laboratory activeLab = UCon.getActiveLaboratory(); 
//		 /* Test Begins Here*/
//		assertEquals(false, activeLab.hasEquipment());//ensures there are no Equipment to begin with
//		String Equipment = "TestEquip";
//		Random rand= new Random();
//		int randA=rand.nextInt(50);
//		int randB=rand.nextInt(randA-1);
//		UCon.createEquipment(Equipment,randA);
//		UCon.modifyEquipment(Equipment, -randB);
//		assertEquals(randA-randB, activeLab.getEquipment(0).getQuantity());
//		
//	}
//	@Test
//	public void testRemoveEquipment() throws InvalidInputException{
//		String error="";
//		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//		UCon.login(testEmail, testPassword);
//		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//		Laboratory activeLab = UCon.getActiveLaboratory(); 
//		 /* Test Begins Here*/
//		assertEquals(false, activeLab.hasEquipment());//ensures there are no Equipment to begin with
//		String Equipment="TestEquip";
//		Random rand= new Random();
//		int randA = rand.nextInt(50);
//		UCon.createEquipment(Equipment,randA);
//		UCon.removeEquipments(Equipment);
//		assertEquals(0, activeLab.getEquipment().size());	
//
//	}
	

	  
}
