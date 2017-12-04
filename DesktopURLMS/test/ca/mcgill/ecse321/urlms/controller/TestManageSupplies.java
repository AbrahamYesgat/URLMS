package ca.mcgill.ecse321.urlms.controller;
import java.io.File;
import java.sql.Date;
import java.util.Iterator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Equipment;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.URLMS;
import ca.mcgill.ecse321.urlms.model.Staff.StaffRole;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;


public class TestManageSupplies {

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
		//Case 1: Create a Supply that does not exist yet
		public void testCreateSupply(){
			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
			UCon.login(testEmail, testPassword);
			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
			Laboratory activeLab = UCon.getActiveLaboratory(); 
			
			/* Test Begins here*/
			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
			String Supply = "TestSupply";
			UCon.createSupplies(Supply,1);
			assertEquals(1,activeLab.getSupplies().size());
			assertEquals(Supply ,activeLab.getSupply(0).getName());
			assertEquals(activeLab.hasSupplies(),true);
			UCon.removeSupplies(Supply);
			UCon.logout();
			urlms.delete();

		}
		
		//Case 2: Ensure that we do not have negative Supplies amounts 
		@Test
		public void testNegativeModifySupply() {
			String error="";
			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
			UCon.login(testEmail, testPassword);
			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
			Laboratory activeLab = UCon.getActiveLaboratory(); 
			
			/* Test Begins here*/
			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
			String Supply = "TestSupply";
			UCon.createSupplies(Supply,1);
			UCon.modifySupplies(Supply, -2);
			assertEquals(0,activeLab.getSupply(0).getQuantity());	
			UCon.removeSupplies(Supply);
			UCon.logout();
			urlms.delete();
			
		}

		// Case 3: Adding Supplies of random amounts
		@Test 
		public void testAddSupplies(){
			String error="";
			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
			UCon.login(testEmail, testPassword);
			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
			Laboratory activeLab = UCon.getActiveLaboratory(); 
			
			 /* Test Begins Here*/
			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
			String Supply = "TestSupply";
			Random rand= new Random();
			int randA=rand.nextInt(50);
			int randB=rand.nextInt(50);
			UCon.createSupplies(Supply,randA);
			UCon.modifySupplies(Supply, randB);
			assertEquals(randA+randB, activeLab.getSupply(0).getQuantity());
			UCon.removeSupplies(Supply);
			UCon.logout();
			urlms.delete();
			
		}
		
		//
		@Test
		//Case 4: Removing amounts of supplies
		public void testRemoveSupplies(){
			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
			UCon.login(testEmail, testPassword);
			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
			Laboratory activeLab = UCon.getActiveLaboratory(); 
			
			 /* Test Begins Here*/
			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
			String Supply = "TestSupply";
			Random rand= new Random();
			int randA=rand.nextInt(50);
			int randB=rand.nextInt(randA-1);
			UCon.createSupplies(Supply,randA);
			UCon.modifySupplies(Supply, -randB);
			assertEquals(randA-randB, activeLab.getSupply(0).getQuantity());
			UCon.removeSupplies(Supply);
			UCon.logout();
			urlms.delete();
	
			
		}
		
		@Test
		//Case 5: Adding a supply with name NULL (i.e. space character or empty string)
		
		public void testAddSuppliesNULL(){
			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
			UCon.login(testEmail, testPassword);
			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
			Laboratory activeLab = UCon.getActiveLaboratory(); 
			
			 /* Test Begins Here*/
			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
			String Supply = "TestSupply";
			Random rand= new Random();
			int randA=rand.nextInt(50);
			int randB=rand.nextInt(randA-1);
			UCon.createSupplies(" ",randA);
			assertEquals(false, activeLab.hasSupplies());
			UCon.removeSupplies(Supply);
			UCon.logout();
			urlms.delete();
		}
		
		  
	}


