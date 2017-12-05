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

public class CreateProgressReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_progress_report);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    private EditText reportTitle;
    private EditText reportText;

    public void addDate(View view) {
        reportTitle = (EditText) findViewById(R.id.report_title);
        reportText = (EditText) findViewById(R.id.report_text);
        TextView reportMessage = (TextView) findViewById(R.id.createReportMessage);

        if(TextUtils.isEmpty(reportTitle.getText().toString()) || TextUtils.isEmpty(reportText.getText().toString())) {
            reportMessage.setText("Missing info to create the progress report!");
        }
        else {
            setContentView(R.layout.create_progress_select_date);
        }
    }

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

    public void createReport(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.report_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();
        String title = reportTitle.getText().toString();
        String report = reportText.getText().toString();

        boolean isValid = cont.createWeeklyProgressReport(title, report, new Date(year, month,day));
        if(isValid){
            Intent intent = new Intent(CreateProgressReport.this, LabPage.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.create_progress_report);
            TextView reportMessage = (TextView) findViewById(R.id.createReportMessage);
            reportMessage.setText("Error occurred when creating the progress report. Please try again.");
        }
    }

    public void returnToPrevious(View view) {
        setContentView(R.layout.create_progress_report);
    }
}
