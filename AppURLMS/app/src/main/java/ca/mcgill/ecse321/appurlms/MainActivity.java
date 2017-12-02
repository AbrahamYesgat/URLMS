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

    public static URLMS urlms;
    public static URLMSController cont;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        PersistenceXStream.initializeURLMS(getFilesDir().getAbsolutePath() +"/data.xml");
        urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();
        cont = new URLMSController(urlms);
    }

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

    public void signUp(View view) {
        setContentView(R.layout.sign_up);
    }

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
                signUpMessage.setText("This email already exists!");
            }
        }
    }

    public void returnToLogin(View view) {
        setContentView(R.layout.login);
    }
}
