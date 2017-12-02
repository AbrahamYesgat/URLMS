package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ca.mcgill.ecse321.urlms.model.Director;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class LabPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(cont.getActiveUser() instanceof Director){
            setContentView(R.layout.dir_lab_page);
        }
        else {
            setContentView(R.layout.staff_lab_page);
        }
    }

    public void addStaff(View view) {
        Intent intent = new Intent(LabPage.this, AddStaff.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(LabPage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void back(View view) {
        Intent intent = new Intent(LabPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
