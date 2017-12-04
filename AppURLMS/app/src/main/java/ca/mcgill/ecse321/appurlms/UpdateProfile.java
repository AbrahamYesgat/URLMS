package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        String email =cont.getActiveUser().getEmail();
        String password = cont.getActiveUser().getPassword();
        String name = cont.getActiveUser().getName();

        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);

        tv1.setText(email);
        tv2.setText(password);
        tv3.setText(name);
    }

    public void updateProfile(View view) {
        EditText tv1 = (EditText) findViewById(R.id.user_email);
        EditText tv2 = (EditText) findViewById(R.id.user_password);
        EditText tv3 = (EditText) findViewById(R.id.user_name);
        TextView updateProfileMessage = (TextView) findViewById(R.id.updateProfileMessage);

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            updateProfileMessage.setText("Missing info to update user!");
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

    public void back(View view) {
        Intent intent = new Intent(UpdateProfile.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
