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

public class ControlActivity extends AppCompatActivity {

    private int current;
    private int previous;
    EditText labName;
    EditText fieldOfStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(current = R.layout.add_lab);
    }

    public void addLab(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.lab_start_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();

        boolean isValid = cont.addLaboratory(labName.getText().toString(), fieldOfStudy.getText().toString(), new Date(year, month,day));
        if(isValid) {
            previous = R.layout.add_lab;
            setContentView(current = R.layout.add_staff);
        }
        else {
            setContentView(previous);
            current = previous;
            TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
            addLabMessage.setText("Laboratory name already exists!");
        }
    }

    private void refreshStaffData() {
        //Resets text entries values
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        EditText tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        CheckBox researchAssistant = (CheckBox)findViewById(R.id.role2_box);
        CheckBox researchAssociate = (CheckBox)findViewById(R.id.role1_box);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        researchAssistant.setChecked(false);
        researchAssociate.setChecked(false);
        addStaffMessage.setText("");
    }

    public void addStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        EditText tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        CheckBox researchAssistant = (CheckBox)findViewById(R.id.role2_box);
        CheckBox researchAssociate = (CheckBox)findViewById(R.id.role1_box);
        boolean isValid;

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()) || (!(researchAssistant.isChecked())
                && !(researchAssociate.isChecked())))
        {
            addStaffMessage.setText("Missing info to create user!");
        }
        else {
            if(researchAssistant.isChecked() && !(researchAssociate.isChecked())) {
                isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                        tv3.getText().toString(), Staff.StaffRole.ResearchAssistant);
                if(isValid) {
                    refreshStaffData();
                    addStaffMessage.setText("Successfully added staff member");
                }
                else {
                    addStaffMessage.setText("Email already exists!");
                }
            }
            else if(!(researchAssistant.isChecked()) && researchAssociate.isChecked()) {
                isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                        tv3.getText().toString(), Staff.StaffRole.ResearchAssociate);
                if(isValid) {
                    refreshStaffData();
                    addStaffMessage.setText("Successfully added staff member");
                }
                else {
                    addStaffMessage.setText("Email already exists!");
                }
            }
            else {
                addStaffMessage.setText("Role not indicated correctly!");
            }
        }
    }

    public void returnToPrevious(View view) {
        setContentView(previous);
        int temp = current;
        current = previous;
        previous = temp;

    }

    public void logout(View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void addDate(View view) {
        TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
        labName = (EditText) findViewById(R.id.lab_name);
        fieldOfStudy = (EditText) findViewById(R.id.field_of_study);
        if(TextUtils.isEmpty(labName.getText().toString()) || TextUtils.isEmpty(fieldOfStudy.getText().toString()))
        {
            addLabMessage.setText("Missing info to create laboratory!");
        }
        else {
            previous = current;
            setContentView(current = R.layout.add_lab_select_date);
        }
    }
}
