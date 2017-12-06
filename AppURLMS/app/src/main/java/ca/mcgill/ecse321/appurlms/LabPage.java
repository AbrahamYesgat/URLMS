package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mcgill.ecse321.urlms.model.Director;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects the lab they want to do things in.
 * Depending on which user is using the system (Director or Staff) a different lab page is used.
 * This is due to the restricted functionality related to the staff member.
 */
public class LabPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        String labName;
        //Decides whether do show the director lab page
        if(cont.getActiveUser() instanceof Director){
            setContentView(R.layout.dir_lab_page);
        }
        //Or the staff lab page.
        else {
            setContentView(R.layout.staff_lab_page);
        }
        //Displays the name of the lab.
        labName = cont.getActiveLaboratory().getName();
        TextView labMessage = (TextView)findViewById(R.id.labname_message);
        labMessage.setText(labName);
    }

    /**
     * Action listener for the add staff button from the director lab page.
     * Opens a new activity associated to the AddStaff activity.
     * @see AddStaff
     * @param view
     */
    public void addStaff(View view) {
        Intent intent = new Intent(LabPage.this, ManageStaff.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button for both the director and the staff lab page.
     * Logs the user out of the system and sets the activity to the login page
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(LabPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button for both the director and the staff lab page.
     * Brings the user back to the home page.
     * @see HomePage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(LabPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the manage supplies button for both the director and the staff lab page.
     * Changes the activity to the manage supply activity only if the lab is set to active.
     * @see ManageSupplies
     * @param view
     */
    public void manageSupplies(View view) {
        if(cont.getActiveLaboratory().getActive()) {
            Intent intent = new Intent(LabPage.this, ManageSupplies.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView labMessage = (TextView) findViewById(R.id.lab_message);
            labMessage.setText("Cannot do this because the lab is inactive.");
        }
    }

    /**
     * Action listener for the manage equipment button for both the director and the staff lab page.
     * Changes the activity to the manage equipment activity only if the lab is set to active.
     * @see ManageEquipment
     * @param view
     */
    public void manageEquipment(View view) {
        if(cont.getActiveLaboratory().getActive()) {
            Intent intent = new Intent(LabPage.this, ManageEquipment.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView labMessage = (TextView) findViewById(R.id.lab_message);
            labMessage.setText("Cannot do this because the lab is inactive.");
        }
    }

    /**
     * Action listener for the edit lab button for only the director lab page.
     * Changes the activity to the edit lab activity.
     * @see EditLab
     * @param view
     */
    public void editLab(View view) {
        Intent intent = new Intent(LabPage.this, EditLab.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the manage funding accounts button for only the director lab page.
     * Changes the activity to the manage funding accounts activity.
     * @see ManageFundingAccounts
     * @param view
     */
    public void manageAccount(View view) {
        Intent intent = new Intent(LabPage.this, ManageFundingAccounts.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the create weekly progress report button for only the staff lab page.
     * Changes the activity to the view progress report activity only if the lab is active.
     * @see ViewProgressReports
     * @param view
     */
    public void createProgressReport(View view) {
        if(cont.getActiveLaboratory().getActive()) {
            Intent intent = new Intent(LabPage.this, ViewProgressReports.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView labMessage = (TextView) findViewById(R.id.lab_message);
            labMessage.setText("Cannot do this because the lab is inactive.");
        }
    }

    /**
     * Action listener for the view weekly progress report button for only the director lab page.
     * Changes the activity to the view progress report activity only if the lab is active.
     * @see ViewProgressReports
     * @param view
     */
    public void viewProgressReport(View view) {
        if(cont.getActiveLaboratory().getActive()) {
            Intent intent = new Intent(LabPage.this, ViewProgressReports.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView labMessage = (TextView) findViewById(R.id.lab_message);
            labMessage.setText("Cannot do this because the lab is inactive.");
        }
    }

    /**
     * Action listener for the delete lab button for only the director lab page.
     * Deletes the lab and all associates objects and changes the activity back to the home page if successful.
     * Throws an error if it was not able to delete the lab.
     * @param view
     */
    public void deleteLab(View view) {
        if(cont.getActiveUser() instanceof Director) {
            boolean isValid = cont.deleteLab((Director)cont.getActiveUser(), cont.getActiveLaboratory());
            if(isValid){
                Intent intent = new Intent(LabPage.this, HomePage.class);
                startActivity(intent);
                finish();
            }
            else{
                TextView labMessage = (TextView) findViewById(R.id.lab_message);
                labMessage.setText("Could not delete the lab!");
            }
        }
    }

    /**
     * Action listener for the create expense report button for both the director and staff lab page.
     * Changes the activity to the view expense report activity only if the lab is active.
     * @see ViewExpenseReport
     * @param view
     */
    public void createExpenseReport(View view) {
        if(cont.getActiveLaboratory().getActive()) {
            Intent intent = new Intent(LabPage.this, ViewExpenseReport.class);
            startActivity(intent);
            finish();
        }
        else {
            TextView labMessage = (TextView) findViewById(R.id.lab_message);
            labMessage.setText("Cannot do this because the lab is inactive.");
        }
    }
}
