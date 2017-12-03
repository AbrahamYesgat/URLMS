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

//		@Test
//		public void testCreateSupply() throws InvalidInputException {
//			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//			UCon.login(testEmail, testPassword);
//			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//			Laboratory activeLab = UCon.getActiveLaboratory(); 
//			
//			/* Test Begins here*/
//			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
//			String Supply = "TestSupply";
//			UCon.createSupplies(Supply,1);
//			assertEquals(1,activeLab.getSupplies().size());
//			assertEquals(Supply ,activeLab.getSupply(0).getName());
//			UCon.logout();
//
//
//		}
		
//		@Test
//		public void testNegativeModifySupply() throws InvalidInputException {
//			String error="";
//			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//			UCon.login(testEmail, testPassword);
//			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//			Laboratory activeLab = UCon.getActiveLaboratory(); 
//			
//			/* Test Begins here*/
//			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
//			String Supply = "TestSupply";
//			UCon.createSupplies(Supply,1);
//			try {
//				UCon.modifySupplies(Supply, -2);
//			} catch (InvalidInputException e) {
//				error = e.getMessage();
//			}
//			
//			assertEquals("There are no more" + Supply +"s left in this lab!", error);
//			assertEquals(0,activeLab.getSupply(0).getQuantity());		
//			
//		}
//
//		@Test
//		public void testAddSupplies() throws InvalidInputException{
//			String error="";
//			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//			UCon.login(testEmail, testPassword);
//			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//			Laboratory activeLab = UCon.getActiveLaboratory(); 
//			 /* Test Begins Here*/
//			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
//			String Supply = "TestSupply";
//			Random rand= new Random();
//			int randA=rand.nextInt(50);
//			int randB=rand.nextInt(50);
//			UCon.createSupplies(Supply,randA);
//			UCon.modifySupplies(Supply, randB);
//			assertEquals(randA+randB, activeLab.getSupply(0).getQuantity());
//			
//		}
//		@Test
//		public void testARemoveSupplies() throws InvalidInputException{
//			String error="";
//			URLMSController UCon = new URLMSController(urlms); //controller for the URLMS
//			UCon.login(testEmail, testPassword);
//			UCon.addLaboratory("name", "study", new Date(2017, 10, 10));
//			Laboratory activeLab = UCon.getActiveLaboratory(); 
//			 /* Test Begins Here*/
//			assertEquals(false, activeLab.hasSupplies());//ensures there are no supplies to begin with
//			String Supply = "TestSupply";
//			Random rand= new Random();
//			int randA=rand.nextInt(50);
//			int randB=rand.nextInt(randA-1);
//			UCon.createSupplies(Supply,randA);
//			UCon.modifySupplies(Supply, -randB);
//			assertEquals(randA-randB, activeLab.getSupply(0).getQuantity());
//			
//		}
		
		  
	}


