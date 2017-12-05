package ca.mcgill.ecse321.appurlms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.AddStaff.hideSoftKeyboard;
import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects the manage funding accounts button from the lab page.
 * This is only allowed for the director of the current lab.
 */
public class ManageStaff extends AppCompatActivity {

    private Staff removeStaff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_staff);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Displays the lab name on top of the screen
        TextView nameMessage = (TextView)findViewById(R.id.lab_staff_message);
        nameMessage.setText("Staff for " + cont.getActiveLaboratory().getName());

        List<Staff> staff = cont.getActiveLaboratory().getStaffs();

        //Displays a list of all the staff members of the lab and their last login.
        int i= 0;
        String[] staffArray = new String[staff.size()];
        for (Staff member : staff) {
            staffArray[i] = member.getName() + " last active: " + member.getLastLogin();
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, staffArray);

        ListView listView = (ListView) findViewById(R.id.staff_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //If a staff member from the list is selected, open the pop up.
                removeStaff = cont.getActiveLaboratory().getStaff(position);
                showPopup();
            }
        });
    }

    private PopupWindow pw;

    /**
     * This is used to have the associated pop up message page to appear on the screen.
     * It indicated whether the user wants remove the staff member from the current lab.
     * Cancel button to dismiss the pop up.
     */
    private void showPopup() {
        hideSoftKeyboard(ManageStaff.this);

        LayoutInflater inflater = LayoutInflater.from(ManageStaff.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.remove_staff_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1000, 600, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    /**
     * Action listener for the add new staff member button.
     * Changes the page to the add new staff member activity.
     * @see AddStaff
     * @param view
     */
    public void addStaff(View view) {
        Intent intent = new Intent(ManageStaff.this, AddStaff.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the back button associated to the manage staff page.
     * Restarts the activity to go back to the initial page.
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ManageStaff.this, LabPage.class);
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
            Intent intent = new Intent(ManageStaff.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener associated to the pop up cancel button.
     * Dismisses the pop up.
     * @param view
     */
    public void cancel(View view) {
        pw.dismiss();
    }

    /**
     * Action listener for the remove button associated to the pop up.
     * Removes the selected staff member from the lab.
     * @param view
     */
    public void removeStaff(View view) {
        boolean isValid = cont.removeStaff(removeStaff.getEmail());
        if(isValid) {
            finish();
            startActivity(getIntent());
        }
    }
}
