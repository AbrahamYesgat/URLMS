package ca.mcgill.ecse321.urlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.urlms.controller.TestURLMSController;
import ca.mcgill.ecse321.urlms.persistence.TestLogin;
// This JUnit test runs all tests at the same time to make sure they can be integrated together.
@RunWith(Suite.class)
@SuiteClasses({ TestURLMSController.class, TestLogin.class})
public class AllTests {
}
