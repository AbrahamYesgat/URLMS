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
//Case 1: equipement attempted to be created 
	public void testCreateEquipment(){
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		Laboratory activeLab = UCon.getActiveLaboratory(); 
		
		/* Test Begins here*/
		assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
		String Equipment = "TestEquipment";
		UCon.createEquipment(Equipment,1);
		assertEquals(1,activeLab.getEquipment().size());
		assertEquals(Equipment ,activeLab.getSupply(0).getName());
		assertEquals(activeLab.hasEquipment(),true);
		UCon.logout();
	}
	
	
	//Case 2: Subtract equipments, make sure we never get negative equipments 
	@Test
	public void testNegativeModifyEquipment() {
		String error="";
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		Laboratory activeLab = UCon.getActiveLaboratory(); 
		
		/* Test Begins here*/
		assertEquals(false, activeLab.hasEquipment());//ensures there are no supplies to begin with
		String Equipment = "TestEquip";
		UCon.createEquipment(Equipment,1);
		UCon.modifyEquipment(Equipment, -2);
		assertEquals(0,activeLab.getEquipment(0).getQuantity());		
	}

//Case 3: adding amounts of equipments
	@Test 
	public void testAddSupplies(){
		String error="";
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		Laboratory activeLab = UCon.getActiveLaboratory(); 
		
		 /* Test Begins Here*/
		assertEquals(false, activeLab.hasEquipment());//ensures there are no supplies to begin with
		String Equip = "TestEquip";
		Random rand= new Random();
		int randA=rand.nextInt(50);
		int randB=rand.nextInt(50);
		UCon.createEquipment(Equip,randA);
		UCon.modifyEquipment(Equip, randB);
		assertEquals(randA+randB, activeLab.getEquipment(0).getQuantity());
		
	}
	
	// Case 4: This test makes sure that we can remove supply amounts from one lab for any random number
	 
	@Test
	public void testRemoveEquipments(){
		String error="";
		URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
		UCon.login(testEmail, testPassword);
		UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
		Laboratory activeLab = UCon.getActiveLaboratory(); 
		
		 /* Test Begins Here*/
		assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
		String Equip = "TestEquip";
		Random rand= new Random();
		int randA=rand.nextInt(50);
		int randB=rand.nextInt(randA-1);
		UCon.createEquipment(Equip,randA);
		UCon.modifyEquipment(Equip, -randB);
		assertEquals(randA-randB, activeLab.getSupply(0).getQuantity());
		
	}
	
	  
}
