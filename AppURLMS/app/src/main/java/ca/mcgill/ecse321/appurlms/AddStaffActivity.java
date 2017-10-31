package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddStaffActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
    }

    public void addStaff(View view) {

    }

    public void logout(View view) {
        Intent intent = new Intent(AddStaffActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void returnToPrevious(View view) {
        Intent intent = new Intent(AddStaffActivity.this, AddLaboratoryActivity.class);
        startActivity(intent);
        finish();
    }
}
