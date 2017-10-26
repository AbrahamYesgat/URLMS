package ca.mcgill.ecse321.urlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.urlms.controller.TestAddLaboratory;
import ca.mcgill.ecse321.urlms.controller.TestAddStaff;
import ca.mcgill.ecse321.urlms.controller.TestLogin;
import ca.mcgill.ecse321.urlms.controller.TestLogout;
import ca.mcgill.ecse321.urlms.persistence.TestPersistence;
// This JUnit test runs all tests at the same time to make sure they can be integrated together.
@RunWith(Suite.class)
@SuiteClasses({ TestLogin.class, TestPersistence.class, TestAddStaff.class, TestAddLaboratory.class, TestLogout.class})
public class AllTests {
}
