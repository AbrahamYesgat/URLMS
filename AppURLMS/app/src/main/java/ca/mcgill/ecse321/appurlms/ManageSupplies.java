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

import ca.mcgill.ecse321.urlms.model.Staff;
import ca.mcgill.ecse321.urlms.model.Supplies;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class ManageSupplies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_supplies);

        TextView nameMessage = (TextView)findViewById(R.id.supply_message);
        nameMessage.setText("Supplies for " + cont.getActiveLaboratory().getName());

        List<Supplies> supplies = cont.getActiveLaboratory().getSupplies();

        int i= 0;
        String[] supplyArray = new String[supplies.size()];
        for (Supplies supply : supplies) {
            supplyArray[i] = supply.getName() +": "+ Integer.toString(supply.getQuantity());
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, supplyArray);

        ListView listView = (ListView) findViewById(R.id.supply_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //removeStaff = cont.getActiveLaboratory().getStaff(position);
                //showPopup();
            }
        });

    }

    public void addSupply(View view) {
        Intent intent = new Intent(ManageSupplies.this, AddSupply.class);
        startActivity(intent);
        finish();
    }

    public void back(View view) {
        Intent intent = new Intent(ManageSupplies.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        Intent intent = new Intent(ManageSupplies.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
