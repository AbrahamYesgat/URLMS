package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set once a user signs into the system.
 * Depending on who logs in (Director or Staff) a different home page is used.
 */
public class HomePage extends AppCompatActivity {

    private List<Laboratory> labs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        String name;
        //Decides whether do show the director home page
        if(cont.getActiveUser() instanceof Director){
            setContentView(R.layout.dir_home_page);
            Director active = (Director)(cont.getActiveUser());
            labs = active.getLaboratories();
            name = active.getName();
        }
        //Or the staff home page
        else {
            setContentView(R.layout.staff_home_page);
            Staff active = (Staff)(cont.getActiveUser());
            labs = active.getLaboratories();
            name = active.getName();
            active.setLastLogin(getDateTime());
        }

        //Displays the name of the user
        TextView nameMessage = (TextView)findViewById(R.id.dir_name_message);
        nameMessage.setText("Welcome " + name);


        //Lists all the labs related to the user.
        int i= 0;
        String active;
        String[] labArray = new String[labs.size()];
        for (Laboratory lab : labs) {
            if(lab.getActive()) {
                active = "is active";
            }
            else {
                active = "is inactive";
            }
            labArray[i] = lab.getName() + ": " + active;
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, labArray);

        ListView listView = (ListView) findViewById(R.id.labs_list);
        listView.setAdapter(adapter);

        //What to do when a user clicks on a lab from the list.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Laboratory activelab = labs.get(position);
                //If the lab is active simply change the UI activity.
                if(activelab.getActive()){
                    cont.setActiveLaboratory(activelab);
                    Intent intent = new Intent(HomePage.this, LabPage.class);
                    startActivity(intent);
                    finish();
                }
                //If it inactive only allow the director to enter the next activity.
                else if(cont.getActiveUser() instanceof Director) {
                    cont.setActiveLaboratory(activelab);
                    Intent intent = new Intent(HomePage.this, LabPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    /**
     * Action listener for the add lab button from the director home page.
     * Opens a new activity associated to the AddNewLab activity.
     * @see AddNewLab
     * @param view
     */
    public void addLab(View view) {
        Intent intent = new Intent(HomePage.this, AddNewLab.class);
        startActivity(intent);
        finish();
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
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the update profile button for both the director and the staff home page.
     * Change the activity to the update profile activity.
     * @see UpdateProfile
     * @param view
     */
    public void updateProfile(View view) {
        Intent intent = new Intent(HomePage.this, UpdateProfile.class);
        startActivity(intent);
        finish();
    }

    /**
     * Function that gets the date and time of the login of a staff member.
     * Is used to define the attribute lastLogin of the staff. Will be used for the director
     * to know when each staff member has last logged into the system.
     * @return the date and time of the login of a staff member. Format yyyy-MM-dd HH:mm:ss .
     */
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
