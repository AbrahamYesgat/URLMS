package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        String name;
        if(cont.getActiveUser() instanceof Director){
            setContentView(R.layout.dir_home_page);
            Director active = (Director)(cont.getActiveUser());
            labs = active.getLaboratories();
            name = active.getName();
        }
        else {
            setContentView(R.layout.staff_home_page);
            Staff active = (Staff)(cont.getActiveUser());
            labs = active.getLaboratories();
            name = active.getName();
            active.setLastLogin(getDateTime());
        }

        TextView nameMessage = (TextView)findViewById(R.id.dir_name_message);
        nameMessage.setText("Welcome " + name);

        int i= 0;
        String active;
        String[] labArray = new String[labs.size()];
        for (Laboratory lab : labs) {
            if(lab.getActive()) {
                active = "is active";
            }
            else {
                active = "is inactive";
            }
            labArray[i] = lab.getName() + ": " + active;
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, labArray);

        ListView listView = (ListView) findViewById(R.id.labs_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Laboratory activelab = labs.get(position);
                if(activelab.getActive()){
                    cont.setActiveLaboratory(activelab);
                    Intent intent = new Intent(HomePage.this, LabPage.class);
                    startActivity(intent);
                    finish();
                }
                else if(cont.getActiveUser() instanceof Director) {
                    cont.setActiveLaboratory(activelab);
                    Intent intent = new Intent(HomePage.this, LabPage.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void addLab(View view) {
        Intent intent = new Intent(HomePage.this, AddNewLab.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(HomePage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void updateProfile(View view) {
        Intent intent = new Intent(HomePage.this, UpdateProfile.class);
        startActivity(intent);
        finish();
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
