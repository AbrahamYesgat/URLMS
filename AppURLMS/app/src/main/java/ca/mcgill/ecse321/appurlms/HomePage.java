package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.Director;
import ca.mcgill.ecse321.urlms.model.Laboratory;
import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.URLMS;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class HomePage extends AppCompatActivity {

    private  Director active = (Director)(cont.getActiveUser());
    private List<Laboratory> labs = active.getLaboratories();
    String [] labArray = labs.toArray(new String[labs.size()]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab_list);

        /*if(cont.getActiveUser() instanceof Director){
            Director active = (Director)(cont.getActiveUser());
            labs = active.getLaboratories();
        }
        else {
            Staff active = (Staff)(cont.getActiveUser());
            labs = active.getLaboratories();
        }*/

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.activity_home_page, labArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }

    public void addLab(View view) {
        Intent intent = new Intent(HomePage.this, ControlActivity.class);
        startActivity(intent);
        finish();
    }
}
