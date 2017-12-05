package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;

import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class AddNewLab extends AppCompatActivity {

    private int current;
    private EditText labName;
    private EditText fieldOfStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(current = R.layout.add_lab);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void addLab(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.lab_start_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();

        boolean isValid = cont.addLaboratory(labName.getText().toString(), fieldOfStudy.getText().toString(), new Date(year, month,day));
        if(isValid) {
            Intent intent = new Intent(AddNewLab.this, LabPage.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.add_lab);
            TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
            addLabMessage.setText("Cannot create the lab with the name specified!");
        }
    }

    public void returnToPrevious(View view) {
        if(current == R.layout.add_lab){
            Intent intent = new Intent(AddNewLab.this, HomePage.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(current = R.layout.add_lab);
        }
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
            Intent intent = new Intent(AddNewLab.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void addDate(View view) {
        TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
        labName = (EditText) findViewById(R.id.lab_name);
        fieldOfStudy = (EditText) findViewById(R.id.field_of_study);
        if(TextUtils.isEmpty(labName.getText().toString()) || TextUtils.isEmpty(fieldOfStudy.getText().toString())) {
            addLabMessage.setText("Missing info to create laboratory!");
        }
        else {
            setContentView(current = R.layout.add_lab_select_date);
        }
    }
}
