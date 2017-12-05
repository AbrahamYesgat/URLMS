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
import ca.mcgill.ecse321.urlms.model.ExpenseReport;
import ca.mcgill.ecse321.urlms.model.ProgressUpdate;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to view the expense reports from the lab page.
 * Both the director and staff members have access to this page,
 * however functionality is restricted for staff.
 */
public class ViewExpenseReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_expense_report);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Display lab name at the top of the page.
        TextView progressReportMessage = (TextView)findViewById(R.id.lab_expense_message);
        progressReportMessage.setText("Expense Reports for " + cont.getActiveLaboratory().getName());

        List<ExpenseReport> reports = cont.getActiveLaboratory().getExpenseReports();
        //Display the expense reports numbers and date of creation.
        int i= 0;
        String[] reportArray = new String[reports.size()];
        for (ExpenseReport report : reports) {
            reportArray[i] = "Expense report " + Integer.toString(i+1);
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, reportArray);

        ListView listView = (ListView) findViewById(R.id.expense_report_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(cont.getActiveUser() instanceof Director) {
                    //If an expense report is selected then display the expense report on a new page with the amount spent.
                    //Only for Directors.
                    ExpenseReport viewReport = cont.getActiveLaboratory().getExpenseReport(position);
                    setContentView(R.layout.view_report);
                    TextView actualReport = (TextView) findViewById(R.id.actual_report);
                    actualReport.setText(viewReport.getExpense() + " amount spent: " + Double.toString(viewReport.getAmount()) + "$");
                }
            }
        });
    }

    /**
     * Action listener for the create expense report button.
     * Changes the page to the create expense report activity.
     * @see CreateExpenseReport
     * @param view
     */
    public void createExpense(View view) {
        Intent intent = new Intent(ViewExpenseReport.this, CreateExpenseReport.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ViewExpenseReport.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button.
     * Logs the user out of the system and sets the activity to the login page
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ViewExpenseReport.this, MainActivity.class);
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
}
