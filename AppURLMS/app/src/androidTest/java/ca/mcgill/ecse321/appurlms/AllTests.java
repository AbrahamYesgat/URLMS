package ca.mcgill.ecse321.appurlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.appurlms.LoginPersistenceTest;
import ca.mcgill.ecse321.appurlms.PersistenceTest;

// This JUnit test runs all tests at the same time to make sure they can be integrated together.
@RunWith(Suite.class)
@SuiteClasses({ LoginPersistenceTest.class, PersistenceTest.class})
public class AllTests {
}