package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class AddLaboratoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab);
    }

    public void addLab(View view) {
        TextView addLabMessage = (TextView) findViewById(R.id.addLabMessage);
        EditText tv1 = (EditText) findViewById(R.id.lab_name);
        EditText tv2 = (EditText) findViewById(R.id.field_of_study);
        EditText tv3 = (EditText) findViewById(R.id.date);

        if(TextUtils.isEmpty(tv1.getText().toString()) || TextUtils.isEmpty(tv2.getText().toString())
                || TextUtils.isEmpty(tv3.getText().toString()))
        {
            addLabMessage.setText("Missing info to create laboratory!");
        }
        else {
            Intent intent = new Intent(AddLaboratoryActivity.this, AddStaffActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void logout(View view) {
        Intent intent = new Intent(AddLaboratoryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
