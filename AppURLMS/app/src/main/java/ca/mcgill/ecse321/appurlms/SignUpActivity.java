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

    private URLMS urlms;
    private URLMSController cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        urlms = URLMS.getInstance();

        Button button1 = (Button) findViewById(R.id.sign_up_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDirector(view);
            }
        });

        refreshData();
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

    private void createDirector(View view) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);

        cont = new URLMSController(urlms);

        if(tv1.getText().toString() == "" || tv2.getText().toString() == "" || tv3.getText().toString() == ""){
            TextView loggingMessage = (TextView) findViewById(R.id.loggingMessage);
            loggingMessage.setText("Missing info to create user!");
        }
        else {
            if(cont.createDirector(tv1.getText().toString(), tv2.getText().toString(), tv3.getText().toString())){
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            }
        }
    }
}
