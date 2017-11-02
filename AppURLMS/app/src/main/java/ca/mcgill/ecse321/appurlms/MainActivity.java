package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
            Intent intent = new Intent(MainActivity.this, AddLaboratoryActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUp(View view) {
        Intent intent1 = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent1);
        finish();
    }
}
