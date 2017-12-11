package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to update their profile.
 * This page displays the current email, the current password (hidden *) and the users name.
 * They can choose to modify any of the fields by erasing the field and inserting their own.
 */
public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //This gets the users current info.
        String email =cont.getActiveUser().getEmail();
        String password = cont.getActiveUser().getPassword();
        String name = cont.getActiveUser().getName();

        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);

        //This sets the fields to the users current info.
        tv1.setText(email);
        tv2.setText(password);
        tv3.setText(name);
    }

    /**
     * Action listener for the update profile button.
     * Used by both the director and a staff member.
     * If the update is successful the activity returns to the home page. If not an error message is shown.
     * @see HomePage
     * @param view
     */
    public void updateProfile(View view) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);
        TextView updateProfileMessage = (TextView) findViewById(R.id.updateProfileMessage);

        //Checks for empty fields
        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            updateProfileMessage.setText("Missing info to update user!");
        }
        else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(tv1.getText().toString()).matches())) {
            updateProfileMessage.setText("Must enter a valid email to sign up!");
        }
        else{
            boolean isValid = cont.updateProfile(tv1.getText().toString(), tv2.getText().toString(), tv3.getText().toString());
            if(isValid) {
                Intent intent = new Intent(UpdateProfile.this, HomePage.class);
                startActivity(intent);
                finish();
            }
            else {
                updateProfileMessage.setText("Cannot change email to the one specified!");
            }
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the home page.
     * @see HomePage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(UpdateProfile.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
