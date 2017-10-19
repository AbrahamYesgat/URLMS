package ca.mcgill.ecse321.urlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.urlms.controller.TestURLMSController;
import ca.mcgill.ecse321.urlms.persistence.TestLoginPersistence;

@RunWith(Suite.class)
@SuiteClasses({ TestURLMSController.class, TestLoginPersistence.class})
public class AllTests {
}
