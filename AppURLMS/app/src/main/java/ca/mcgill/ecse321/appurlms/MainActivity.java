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

public class MainActivity extends AppCompatActivity {

    public URLMS urlms;
    public static URLMSController cont;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        PersistenceXStream.initializeURLMS(getFilesDir().getAbsolutePath() +"/data.xml");
        urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
    }

    public void login(View v) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);

        //Call controller method to verify if in xml
        cont = new URLMSController(urlms);
        boolean isValid = cont.login(tv1.getText().toString(), tv2.getText().toString());
        if(!isValid) {
            TextView loggingMessage = (TextView) findViewById(R.id.loggingMessage);
            loggingMessage.setText("Wrong username/password combination!");
        }
        else{
            Intent intent = new Intent(MainActivity.this, ControlActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUp(View view) {
        setContentView(R.layout.sign_up);
    }

    public void createDirector(View view) {
        TextView signUpMessage = (TextView) findViewById(R.id.signUpMessage);
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            signUpMessage.setText("Missing info to create user!");
        }
        else {
            boolean isValid = cont.createDirector(tv1.getText().toString(), tv2.getText().toString(), tv3.getText().toString());
            if(isValid) {
                setContentView(R.layout.login);
            }
            else {
                signUpMessage.setText("This email already exists!");
            }
        }
    }

    public void returnToLogin(View view) {
        setContentView(R.layout.login);
    }
}
