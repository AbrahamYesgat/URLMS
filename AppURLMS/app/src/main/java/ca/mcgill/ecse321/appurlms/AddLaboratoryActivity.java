package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ca.mcgill.ecse321.urlms.model.URLMS;

public class AddLaboratoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab);
    }

    public void addLab(View view) {
        Intent intent = new Intent(AddLaboratoryActivity.this, AddStaffActivity.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(AddLaboratoryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
