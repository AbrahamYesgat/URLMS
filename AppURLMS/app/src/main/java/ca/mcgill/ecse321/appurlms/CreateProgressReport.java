package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to create a weekly progress report for the current lab.
 * This is only allowed for staff members of the current lab.
 */
public class CreateProgressReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_progress_report);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    private EditText reportTitle;
    private EditText reportText;

    /**
     * Action listener for the add date button on the initial create progress report page.
     * Once the user has entered all the fields on the first page, the next page displays in
     * order for the user to select the date of the progress report.
     * @param view
     */
    public void addDate(View view) {
        reportTitle = (EditText) findViewById(R.id.report_title);
        reportText = (EditText) findViewById(R.id.report_text);
        TextView reportMessage = (TextView) findViewById(R.id.createReportMessage);

        //Checks for empty fields.
        if(TextUtils.isEmpty(reportTitle.getText().toString()) || TextUtils.isEmpty(reportText.getText().toString())) {
            reportMessage.setText("Missing info to create the progress report!");
        }
        else {
            setContentView(R.layout.create_progress_select_date);
        }
    }

    /**
     * Action listener for the back button on the initial create progress report page.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(CreateProgressReport.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button for both the director and the staff home page.
     * Logs the user out of the system and sets the activity to the login page
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(CreateProgressReport.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for create weekly progress report button associated to the second page.
     * Successfully creating the weekly progress report returns the user to the view weekly progress report activity.
     * @see ViewProgressReports
     * @param view
     */
    public void createReport(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.report_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();
        String title = reportTitle.getText().toString();
        String report = reportText.getText().toString();

        boolean isValid = cont.createWeeklyProgressReport(title, report, new Date(year, month,day));
        if(isValid){
            Intent intent = new Intent(CreateProgressReport.this, ViewProgressReports.class);
            startActivity(intent);
            finish();
        }
        //Error has occured.
        else {
            setContentView(R.layout.create_progress_report);
            TextView reportMessage = (TextView) findViewById(R.id.createReportMessage);
            reportMessage.setText("Error occurred when creating the progress report. Please try again.");
        }
    }

    /**
     * Action listener for the back button on the second page of the create progress report activity.
     * Brings the user back to the first page of this activity.
     * @param view
     */
    public void returnToPrevious(View view) {
        setContentView(R.layout.create_progress_report);
    }
}
