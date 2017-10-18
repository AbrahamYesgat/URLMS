package ca.mcgill.ecse321.urlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.urlms.controller.TestURLMSController;
import ca.mcgill.ecse321.urlms.login.TestPersistence;

@RunWith(Suite.class)
@SuiteClasses({ TestURLMSController.class, TestPersistence.class})
public class AllTests {
}
