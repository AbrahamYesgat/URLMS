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

public class ViewExpenseReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_expense_report);

        TextView progressReportMessage = (TextView)findViewById(R.id.lab_expense_message);
        progressReportMessage.setText("Expense Reports for " + cont.getActiveLaboratory().getName());

        List<ExpenseReport> reports = cont.getActiveLaboratory().getExpenseReports();

        int i= 0;
        String[] reportArray = new String[reports.size()];
        for (ExpenseReport report : reports) {
            reportArray[i] = Integer.toString(i);
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
                    ExpenseReport viewReport = cont.getActiveLaboratory().getExpenseReport(position);
                    setContentView(R.layout.view_report);
                    TextView actualReport = (TextView) findViewById(R.id.actual_report);
                    actualReport.setText(viewReport.getExpensePeriod());
                }
            }
        });
    }

    public void createExpense(View view) {
        Intent intent = new Intent(ViewExpenseReport.this, CreateExpenseReport.class);
        startActivity(intent);
        finish();
    }

    public void back(View view) {
        Intent intent = new Intent(ViewExpenseReport.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ViewExpenseReport.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }
}
