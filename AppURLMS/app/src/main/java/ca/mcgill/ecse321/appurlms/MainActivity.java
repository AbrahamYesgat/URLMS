package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;

/**
 * This the main activity of the app. This is the first page it opens when the app is run.
 * This activity allows the user to log in based on their email and password.
 * It also allows a director to create an account.
 * Staff members cannot create an account because they must be associated to a lab and appointed by the director.
 */
public class MainActivity extends AppCompatActivity {

    public static URLMS urlms;
    public static URLMSController cont;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Load persistence and create the controller.
        PersistenceXStream.initializeURLMS(getFilesDir().getAbsolutePath() +"/data.xml");
        urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
        cont = new URLMSController(urlms);
    }

    /**
     * Action listener for the login button.
     * If the login is successful, then go to the home page activity.
     * If the login is not successful, then display an error message.
     * @param v
     */
    public void login(View v) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);

        boolean isValid = cont.login(tv1.getText().toString(), tv2.getText().toString());
        if(!isValid) {
            TextView loggingMessage = (TextView) findViewById(R.id.loggingMessage);
            loggingMessage.setText("Wrong username/password combination!");
        }
        else{
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the sign up button.
     * Sets the page to the sign up page that will allow a user to create a new director account.
     * @param view
     */
    public void signUp(View view) {
        setContentView(R.layout.sign_up);
    }

    /**
     * Action listener for the create direcetor button (sign up)
     * If the account creation is successful then return to the login page.
     * If the account creation is not successful then display an error message.
     * @param view
     */
    public void createDirector(View view) {
        TextView signUpMessage = (TextView) findViewById(R.id.signUpMessage);
        EditText tv3 = (EditText) findViewById(R.id.dir_email);
        EditText tv4 = (EditText) findViewById(R.id.dir_password);
        EditText tv5 = (EditText) findViewById(R.id.user_name);

        if(TextUtils.isEmpty(tv3.getText().toString()) || TextUtils.isEmpty(tv4.getText().toString())
                || TextUtils.isEmpty(tv5.getText().toString()))
        {
            signUpMessage.setText("Missing info to create user!");
        }
        else {
            boolean valid = cont.createDirector(tv3.getText().toString(), tv4.getText().toString(), tv5.getText().toString());
            if(valid) {
                setContentView(R.layout.login);
            }
            else {
                signUpMessage.setText("Cannot create account with this email!");
            }
        }
    }

    /**
     * Action listener for the back button in the sign up page.
     * If the user changes his mind and does not want to create an account then the back
     * button sends them back to the login.
     * @param view
     */
    public void returnToLogin(View view) {
        setContentView(R.layout.login);
    }
}
