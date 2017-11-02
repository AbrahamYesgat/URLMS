package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
    }

    private void refreshData() {
        //Resets text entries values
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        EditText tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        EditText tv4 = (EditText) findViewById(R.id.staff_role);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");
        addStaffMessage.setText("");
    }

    public void addStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        EditText tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        EditText tv4 = (EditText) findViewById(R.id.staff_role);

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            addStaffMessage.setText("Missing info to create user!");
        }
        else {
            refreshData();
        }
    }

    public void logout(View view) {
        Intent intent = new Intent(AddStaffActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void returnToPrevious(View view) {
        Intent intent = new Intent(AddStaffActivity.this, AddLaboratoryActivity.class);
        startActivity(intent);
        finish();
    }
}
