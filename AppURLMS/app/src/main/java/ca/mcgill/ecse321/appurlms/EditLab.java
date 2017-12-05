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

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to edit the info of the currently selected lab.
 * This is only allowed for director of the lab.
 */
public class EditLab extends AppCompatActivity {
    private EditText labName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_lab);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Sets the name field to the current name of the lab.
        labName = (EditText) findViewById(R.id.lab_name);
        labName.setText(cont.getActiveLaboratory().getName());
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
            Intent intent = new Intent(EditLab.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void returnToPrevious(View view) {
        Intent intent = new Intent(EditLab.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    private boolean active;

    /**
     * Action listener for the add date button.
     * Once the fields of the first page are set up, it changes the page ot the calender picker
     * in order to allow the user to select a new date the lab will start.
     * @param view
     */
    public void addDate(View view) {
        labName = (EditText) findViewById(R.id.lab_name);
        TextView labMessage = (TextView) findViewById(R.id.modifyLabMessage);
        CheckBox isActive = (CheckBox)findViewById(R.id.active_checkbox);

        if(TextUtils.isEmpty(labName.getText().toString())) {
            labMessage.setText("Cannot change lab name to specified name!");
        }
        else {
            active = isActive.isChecked();
            setContentView(R.layout.modify_lab_select_date);
        }
    }

    /**
     * Action lister for the modify lab info button.
     * Changes the labs info to that specified in this activity.
     * Unsuccessfully modifying the lab returns the page to the initial page of this
     * activity and displays an error message.
     * @param view
     */
    public void modifyLab(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.lab_start_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();
        String name = labName.getText().toString();

        boolean isValid = cont.updateLab(name, new Date(year, month,day), active);
        if(isValid) {
            Intent intent = new Intent(EditLab.this, LabPage.class);
            startActivity(intent);
            finish();
        }
        //Lab name already exists.
        else {
            setContentView(R.layout.edit_lab);
            TextView addLabMessage = (TextView) findViewById(R.id.modifyLabMessage);
            addLabMessage.setText("Cannot change lab name to specified name!");
        }
    }
}
