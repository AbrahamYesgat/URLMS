package ca.mcgill.ecse321.appurlms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// This JUnit test runs all tests at the same time to make sure they can be integrated together.
@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, PersistenceTest.class, TestAddLaboratory.class, TestAddStaff.class,
                TestLogout.class, TestCreateDirector.class, TestCreateEquipment.class, TestCreateFundingAccount.class,
                TestCreateSupply.class, TestDeleteEquipment.class, TestDeleteStaff.class, TestDeleteSupply.class,
                TestUpdateProfile.class, TestModifyEquipment.class, TestModifyFunds.class, TestModifySupply.class,
                TestDeleteLab.class, TestUpdateLab.class, TestCreateProgressReport.class, TestAddExistingStaff.class,
                TestCreateExpenseReport.class})
public class AllTests {
}