package ca.mcgill.ecse321.appurlms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import ca.mcgill.ecse321.urlms.model.Supplies;

import static ca.mcgill.ecse321.appurlms.AddStaff.hideSoftKeyboard;
import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class ManageSupplies extends AppCompatActivity {

    Supplies modifySupply;

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
                modifySupply = cont.getActiveLaboratory().getSupply(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;
    private void showPopup() {
        hideSoftKeyboard(ManageSupplies.this);

        LayoutInflater inflater = LayoutInflater.from(ManageSupplies.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_supply_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 600, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
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
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ManageSupplies.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void cancel(View view) {
        pw.dismiss();
    }

    public void delete(View view) {
        boolean isValid = cont.removeSupplies(modifySupply.getName());
        if(isValid) {
            finish();
            startActivity(getIntent());
        }
    }

    public void modify(View view) {
        pw.dismiss();
        setContentView(R.layout.modify);
    }

    public void addAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifySupplyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        if(quantity.isEmpty()){
            modifySupplyMessage.setText("Please indicate a quantity to modify the supply.");
        }
        else {
            boolean isValid = cont.modifySupplies(modifySupply.getName(), Integer.parseInt(quantity));
            if (isValid) {
                finish();
                startActivity(getIntent());
            }
            else {
                modifySupplyMessage.setText("Error: Please try again.");
            }
        }
    }

    public void removeAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifySupplyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        if(quantity.isEmpty()){
            modifySupplyMessage.setText("Please indicate a quantity to modify the supply.");
        }
        else {
            boolean isValid = cont.modifySupplies(modifySupply.getName(), (-1*Integer.parseInt(quantity)));
            if(isValid) {
                finish();
                startActivity(getIntent());
            }
            else {
                modifySupplyMessage.setText("Error: Please try again.");
            }
        }
    }

    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }
}
