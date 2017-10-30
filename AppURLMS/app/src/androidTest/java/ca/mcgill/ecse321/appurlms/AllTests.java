package ca.mcgill.ecse321.appurlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// This JUnit test runs all tests at the same time to make sure they can be integrated together.
@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, PersistenceTest.class, TestAddLaboratory.class, TestAddStaff.class,
                TestLogout.class, TestCreateDirector.class})
public class AllTests {
}