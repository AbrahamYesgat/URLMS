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

public class ViewProgressReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(cont.getActiveUser() instanceof Director) {
            setContentView(R.layout.view_progress_reports);
        }
        else {
            setContentView(R.layout.view_staff_progress_reports);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        TextView progressReportMessage = (TextView)findViewById(R.id.lab_progress_message);
        progressReportMessage.setText("Progress Reports for " + cont.getActiveLaboratory().getName());

        List<ProgressUpdate> reports = cont.getActiveLaboratory().getProgressUpdates();

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
                ProgressUpdate viewReport = cont.getActiveLaboratory().getProgressUpdate(position);
                setContentView(R.layout.view_report);
                TextView actualReport = (TextView) findViewById(R.id.actual_report);
                actualReport.setText(viewReport.getReport());
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(ViewProgressReports.this, LabPage.class);
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
            Intent intent = new Intent(ViewProgressReports.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }

    public void createReport(View view) {
        Intent intent = new Intent(ViewProgressReports.this, CreateProgressReport.class);
        startActivity(intent);
        finish();
    }
}
