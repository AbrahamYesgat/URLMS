package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private void refreshData() {
        //Resets text entries values
        TextView tv1 = (TextView) findViewById(R.id.user_email);
        TextView tv2 = (TextView) findViewById(R.id.user_password);
        TextView tv3 = (TextView) findViewById(R.id.user_name);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
    }


    public void createDirector(View view) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);

        //cont = new URLMSController(urlms);

        if(tv1.getText().toString() == "" || tv2.getText().toString() == "" || tv3.getText().toString() == ""){
            TextView signUpMessage = (TextView) findViewById(R.id.signUpMessage);
            signUpMessage.setText("Missing info to create user!");
        }
        else {
            if(MainActivity.cont.createDirector(tv1.getText().toString(), tv2.getText().toString(), tv3.getText().toString())){
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }
        }
    }
}
