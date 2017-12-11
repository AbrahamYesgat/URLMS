package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to create a new expense report in the view expense report page.
 * This is only allowed for both the director and staff members.
 */
public class CreateExpenseReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_expense_report);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private String report;
    private String value;

    /**
     * Action listener for the add date button on the initial create expense report page.
     * Once the user has entered all the fields on the first page, the next page displays in
     * order for the user to select the date of the expense report.
     * @param view
     */
    public void addExpense(View view) {
        //Get all the fields.
        EditText expenseReport = (EditText) findViewById(R.id.expense_report);
        EditText amount = (EditText) findViewById(R.id.expense_amount);
        TextView errorMessage = (TextView) findViewById(R.id.error_expense_message);
        report = expenseReport.getText().toString();
        value = amount.getText().toString();

        //Checks if the fields are empty.
        if(!(TextUtils.isEmpty(expenseReport.getText().toString().trim()) || TextUtils.isEmpty(amount.getText().toString().trim()))) {
            setContentView(R.layout.create_expense_select_date);
        }
        else{
            errorMessage.setText("Missing info to create the expense report!");
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the view expense reports page.
     * @see ViewExpenseReport
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(CreateExpenseReport.this, ViewExpenseReport.class);
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
            Intent intent = new Intent(CreateExpenseReport.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button on the second page of the create expense report activity.
     * Brings the user back to the first page of this activity.
     * @param view
     */
    public void returnToPrevious(View view) {
        setContentView(R.layout.create_expense_report);
    }

    /**
     * Action listener for adding an expense report to the current lab.
     * If the expense report is successfully added it returns the user to
     * the view expense reports page.
     * @see ViewExpenseReport
     * @param view
     */
    public void createExpense(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.expense_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();

        boolean isValid = cont.createExpenseReport(report, Double.parseDouble(value), day, month, year);
        if(isValid) {
            Intent intent = new Intent(CreateExpenseReport.this, ViewExpenseReport.class);
            startActivity(intent);
            finish();
        }
    }
}
