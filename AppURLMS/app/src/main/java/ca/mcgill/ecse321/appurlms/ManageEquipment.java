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

import ca.mcgill.ecse321.urlms.model.Equipment;

import static ca.mcgill.ecse321.appurlms.AddStaff.hideSoftKeyboard;
import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class ManageEquipment extends AppCompatActivity {

    Equipment modifyEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_equipment);

        TextView nameMessage = (TextView)findViewById(R.id.equip_message);
        nameMessage.setText("Equipment for " + cont.getActiveLaboratory().getName());

        List<Equipment> equipments = cont.getActiveLaboratory().getEquipment();

        int i= 0;
        String[] equipArray = new String[equipments.size()];
        for (Equipment equipment : equipments) {
            equipArray[i] = equipment.getName() +": "+ Integer.toString(equipment.getQuantity());
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, equipArray);

        ListView listView = (ListView) findViewById(R.id.equipment_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                modifyEquipment = cont.getActiveLaboratory().getEquipment(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;
    private void showPopup() {
        hideSoftKeyboard(ManageEquipment.this);

        LayoutInflater inflater = LayoutInflater.from(ManageEquipment.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_equipment_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 600, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    public void back(View view) {
        Intent intent = new Intent(ManageEquipment.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ManageEquipment.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void cancel(View view) {
        pw.dismiss();
    }

    public void delete(View view) {
        boolean isValid = cont.removeEquipments(modifyEquipment.getName());
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
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        if(quantity.isEmpty()){
            modifyMessage.setText("Please indicate a quantity to modify the supply.");
        }
        else {
            boolean isValid = cont.modifyEquipment(modifyEquipment.getName(), Integer.parseInt(quantity));
            if (isValid) {
                finish();
                startActivity(getIntent());
            }
            else {
                modifyMessage.setText("Error: Please try again.");
            }
        }
    }

    public void removeAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        if(quantity.isEmpty()){
            modifyMessage.setText("Please indicate a quantity to modify the supply.");
        }
        else {
            boolean isValid = cont.modifyEquipment(modifyEquipment.getName(), (-1*Integer.parseInt(quantity)));
            if(isValid) {
                finish();
                startActivity(getIntent());
            }
            else {
                modifyMessage.setText("Error: Please try again.");
            }
        }
    }

    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }

    public void addEquipment(View view) {
        Intent intent = new Intent(ManageEquipment.this, AddEquipment.class);
        startActivity(intent);
        finish();
    }
}

