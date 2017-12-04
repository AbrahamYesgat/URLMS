package ca.mcgill.ecse321.appurlms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class AddStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_staff);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
    private EditText tv1;
    public void addStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        tv1 = (EditText) findViewById(R.id.staff_email);
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
                    showPopup();
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
                    showPopup();
                }
            }
            else {
                addStaffMessage.setText("Role not indicated correctly!");
            }
        }
    }

    private PopupWindow pw;
    private void showPopup() {
            hideSoftKeyboard(AddStaff.this);

            LayoutInflater inflater = LayoutInflater.from(AddStaff.this);
            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.staff_pop_up,
                    (ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, 1000, 600, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void refreshStaffData() {
        //Resets text entries values
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        CheckBox researchAssistant = (CheckBox) findViewById(R.id.role2_box);
        CheckBox researchAssociate = (CheckBox) findViewById(R.id.role1_box);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        researchAssistant.setChecked(false);
        researchAssociate.setChecked(false);
        addStaffMessage.setText("");
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(AddStaff.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(AddStaff.this, ManageStaff.class);
        startActivity(intent);
        finish();
    }

    public void addExistingStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        pw.dismiss();
        String email = tv1.getText().toString();
       boolean isValid = cont.addExistingStaff(email);
        refreshStaffData();
        if(isValid){
            addStaffMessage.setText("Successfully added staff member");
        }
        else{
            addStaffMessage.setText("Could not find user with the specified email!");
       }
    }

    public void cancel(View view) {
        pw.dismiss();
        refreshStaffData();
    }
}