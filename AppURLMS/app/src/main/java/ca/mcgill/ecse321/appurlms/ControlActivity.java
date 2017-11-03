package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class ControlActivity extends AppCompatActivity {

    private int current;
    private int previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(current = R.layout.add_lab);
    }

    public void addLab(View view) {
        TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
        EditText tv1 = (EditText) findViewById(R.id.lab_name);
        EditText tv2 = (EditText) findViewById(R.id.field_of_study);
        EditText tv3 = (EditText) findViewById(R.id.date);

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            addLabMessage.setText("Missing info to create laboratory!");
        }
        else {
            previous = current;
            setContentView(current = R.layout.add_staff);
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
                //isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                       // tv3.getText().toString(), Staff.StaffRole.ResearchAssistant);
                isValid = true;
                if(isValid) {
                    refreshStaffData();
                    addStaffMessage.setText("Successfully added staff member");
                }
                else {
                    addStaffMessage.setText("Email already exists!");
                }
            }
            else if(!(researchAssistant.isChecked()) && researchAssociate.isChecked()) {
                //isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                  //      tv3.getText().toString(), Staff.StaffRole.ResearchAssociate);
                isValid = true;
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
    }

    public void logout(View view) {
        Intent intent = new Intent(ControlActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
