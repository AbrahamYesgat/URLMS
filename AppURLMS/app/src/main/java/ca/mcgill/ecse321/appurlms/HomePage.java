package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        }

        TextView nameMessage = (TextView)findViewById(R.id.dir_name_message);
        nameMessage.setText("Welcome " + name);

        int i= 0;
        String[] labArray = new String[labs.size()];
        for (Laboratory lab : labs) {
            labArray[i] = lab.getName();
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
                cont.setActiveLaboratory(activelab);
                Intent intent = new Intent(HomePage.this, LabPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void addLab(View view) {
        Intent intent = new Intent(HomePage.this, AddNewLab.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
