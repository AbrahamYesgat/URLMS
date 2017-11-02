package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.URLMS;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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

        }
    }
}
