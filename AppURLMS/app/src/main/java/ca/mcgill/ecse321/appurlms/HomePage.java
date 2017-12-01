package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class HomePage extends AppCompatActivity {

    private List<Laboratory> labs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if(cont.getActiveUser() instanceof Director){
            Director active = (Director)(cont.getActiveUser());
            labs = active.getLaboratories();
        }
        else {
            Staff active = (Staff)(cont.getActiveUser());
            labs = active.getLaboratories();
        }

        int i= 0;
        String[] labArray = new String[labs.size()];
        for (Laboratory lab : labs) {
            labArray[i] = lab.getName();
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.lab_list, labArray);

        ListView listView = (ListView) findViewById(R.id.labs_list);
        listView.setAdapter(adapter);
    }

    public void addLab(View view) {
        Intent intent = new Intent(HomePage.this, ControlActivity.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
