package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;
import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to view the weekly progress reports from the lab page.
 * Both the director and staff members have access to this page however their pages are different.
 */
public class ViewProgressReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Director progress report page
        if(cont.getActiveUser() instanceof Director) {
            setContentView(R.layout.view_progress_reports);
        }
        //Staff progress report page.
        else {
            setContentView(R.layout.view_staff_progress_reports);
        }
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Display lab name at the top of the page.
        TextView progressReportMessage = (TextView)findViewById(R.id.lab_progress_message);
        progressReportMessage.setText("Progress Reports for " + cont.getActiveLaboratory().getName());

        List<ProgressUpdate> reports = cont.getActiveLaboratory().getProgressUpdates();

        //Display the progress reports titles.
        int i= 0;
        String[] reportArray = new String[reports.size()];
        for (ProgressUpdate report : reports) {
            reportArray[i] = report.getTitle();
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, reportArray);

        ListView listView = (ListView) findViewById(R.id.progress_report_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //If an expense report is selected then display the expense report on a new page with the report.
                ProgressUpdate viewReport = cont.getActiveLaboratory().getProgressUpdate(position);
                setContentView(R.layout.view_report);
                TextView actualReport = (TextView) findViewById(R.id.actual_report);
                actualReport.setText(viewReport.getReport());
            }
        });
    }

    /**
     * Action listener for the back button for both the director and the staff progress report page.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ViewProgressReports.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button for both the director and the staff progress report page.
     * Logs the user out of the system and sets the activity to the login page
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ViewProgressReports.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button associated to the view report page.
     * Restarts the activity to go back to the initial page.
     * @param view
     */
    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }

    /**
     * Action listener for the create weekly progress report button.
     * Changes the page to the create weekly progress report activity.
     * @see CreateProgressReport
     * @param view
     */
    public void createReport(View view) {
        Intent intent = new Intent(ViewProgressReports.this, CreateProgressReport.class);
        startActivity(intent);
        finish();
    }
}
