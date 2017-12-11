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
import android.widget.RadioButton;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to add a new staff member to the currently selected lab.
 * This is only allowed for director of the lab.
 */
public class AddStaff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_staff);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private EditText tv1;

    /**
     * Action listener for the add staff button.
     * Successfully adding a staff member refreshes the page and displays a success message.
     * Unsuccessful add displays a pop up indicating wheter the user wants to add the existing user to
     * the lab.
     * @param view
     */
    public void addStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        RadioButton researchAssistant = (RadioButton)findViewById(R.id.role2_box);
        RadioButton researchAssociate = (RadioButton)findViewById(R.id.role1_box);
        boolean isValid;

        //Check for empty strings.
        if(TextUtils.isEmpty(tv1.getText().toString().trim()) || TextUtils.isEmpty(tv2.getText().toString().trim())
                || TextUtils.isEmpty(tv3.getText().toString().trim()) || (!(researchAssistant.isChecked())
                && !(researchAssociate.isChecked())))
        {
            addStaffMessage.setText("Missing info to create user!");
        }
        else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(tv1.getText().toString()).matches())) {
            addStaffMessage.setText("Must enter a valid email to sign up!");
        }
        else {
            //Research assistant is checked.
            if(researchAssistant.isChecked() && !(researchAssociate.isChecked())) {
                isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                        tv3.getText().toString(), Staff.StaffRole.ResearchAssistant);
                if(isValid) {
                    refreshStaffData();
                    addStaffMessage.setText("Successfully added staff member");
                }
                //Email already exists.
                else {
                    showPopup();
                }
            }
            //Research associate is checked.
            else if(!(researchAssistant.isChecked()) && researchAssociate.isChecked()) {
                isValid = cont.addStaff(tv1.getText().toString(), tv2.getText().toString(),
                        tv3.getText().toString(), Staff.StaffRole.ResearchAssociate);
                if(isValid) {
                    refreshStaffData();
                    addStaffMessage.setText("Successfully added staff member");
                }
                //Email already exists.
                else {
                    showPopup();
                }
            }
            //Both roles are checked.
            else {
                addStaffMessage.setText("Role not indicated correctly!");
            }
        }
    }


    private PopupWindow pw;

    /**
     * This is used to have the associated pop up message page to appear on the screen.
     */
    private void showPopup() {
            hideSoftKeyboard(AddStaff.this);

            LayoutInflater inflater = LayoutInflater.from(AddStaff.this);
            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.staff_pop_up,
                    (ViewGroup) findViewById(R.id.popup_1));
            pw = new PopupWindow(layout, 1000, 600, true);
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    /**
     * This is used to hide the keyboard when a pop up appears on the screen.
     * This is done because sometimes the keyboard would prevent the user from viewing the message
     * or the buttons he needed to press to dismiss the pop up.
     * @param activity
     */
    public static void hideSoftKeyboard(AppCompatActivity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * Resets all the fields of this page back to the nothing.
     * This is used to allow the user to add multiple staff members at one time.
     */
    private void refreshStaffData() {
        //Resets text entries values
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        tv1 = (EditText) findViewById(R.id.staff_email);
        EditText tv2 = (EditText) findViewById(R.id.staff_password);
        EditText tv3 = (EditText) findViewById(R.id.staff_name);
        RadioButton researchAssistant = (RadioButton) findViewById(R.id.role2_box);
        RadioButton researchAssociate = (RadioButton) findViewById(R.id.role1_box);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        researchAssistant.setChecked(false);
        researchAssociate.setChecked(false);
        addStaffMessage.setText("");
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
            Intent intent = new Intent(AddStaff.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the manage staff page.
     * @see ManageStaff
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(AddStaff.this, ManageStaff.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the add existing staff button associated to the pop up message.
     * This occurs when the staff member the director wants to add already exists in the system.
     * The system prompts the director to whether they want to add the existing user.
     * @param view
     */
    public void addExistingStaff(View view) {
        TextView addStaffMessage = (TextView) findViewById(R.id.addStaffMessage);
        pw.dismiss();
        String email = tv1.getText().toString();
       boolean isValid = cont.addExistingStaff(email);
        refreshStaffData();
        //Adds the existing staff member
        if(isValid){
            addStaffMessage.setText("Successfully added staff member");
        }
        //Email associated to a director.
        else{
            addStaffMessage.setText("Could not find user with the specified email!");
       }
    }

    /**
     * Action listener for the cancel button associated to the pop up message.
     * This occurs when the staff member the director wants to add already exists in the system.
     * The system prompts the director to whether they want to add the existing user and he chooses not
     * to add the user.
     * Dismisses the pop and refreshes the text field.
     * @param view
     */
    public void cancel(View view) {
        pw.dismiss();
        refreshStaffData();
    }
}