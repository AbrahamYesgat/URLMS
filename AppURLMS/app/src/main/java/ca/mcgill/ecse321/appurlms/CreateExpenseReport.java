package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class CreateExpenseReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_expense_report);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void addExpense(View view) {
        EditText expenseReport = (EditText) findViewById(R.id.expense_report);
        EditText amount = (EditText) findViewById(R.id.expense_amount);
        DatePicker labStartDate = (DatePicker) findViewById(R.id.expense_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();
        String report = expenseReport.getText().toString();
        String value = amount.getText().toString();

        if(!(TextUtils.isEmpty(expenseReport.getText().toString()))) {
            boolean isValid = cont.createExpenseReport(report, Double.parseDouble(value), day, month, year);
            if(isValid) {
                Intent intent = new Intent(CreateExpenseReport.this, ViewExpenseReport.class);
                startActivity(intent);
                finish();
            }
        }
    }

    public void back(View view) {
        Intent intent = new Intent(CreateExpenseReport.this, ViewExpenseReport.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(CreateExpenseReport.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
