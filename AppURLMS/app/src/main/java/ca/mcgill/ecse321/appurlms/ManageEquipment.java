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

/**
 * This page is set when the user selects the manage equipment button from the lab page.
 */
public class ManageEquipment extends AppCompatActivity {

    Equipment modifyEquipment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_equipment);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Display lab name at the top of the page.
        TextView nameMessage = (TextView)findViewById(R.id.equip_message);
        nameMessage.setText("Equipment for " + cont.getActiveLaboratory().getName());

        List<Equipment> equipments = cont.getActiveLaboratory().getEquipment();

        //Display the equipments names and quantity of each.
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
                //If an equipment from the list is selected, open the pop up.
                modifyEquipment = cont.getActiveLaboratory().getEquipment(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;

    /**
     * This is used to have the associated pop up message page to appear on the screen.
     * It indicated whether the user wants to modify or delete the selected equipment.
     * Cancel button to dismiss the pop up.
     */
    private void showPopup() {
        hideSoftKeyboard(ManageEquipment.this);

        LayoutInflater inflater = LayoutInflater.from(ManageEquipment.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_equipment_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 600, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ManageEquipment.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button.
     * Logs the user out of the system and sets the activity to the login page
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ManageEquipment.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener associated to the pop up cancel button.
     * Dismisses the pop up.
     * @param view
     */
    public void cancel(View view) {
        pw.dismiss();
    }

    /**
     * Action listener for the delete button associated to the pop up.
     * Deletes the selected equipment.
     * @param view
     */
    public void delete(View view) {
        boolean isValid = cont.removeEquipments(modifyEquipment.getName());
        if(isValid) {
            finish();
            startActivity(getIntent());
        }
    }

    /**
     * Action listener for the modify equipment button associated to the pop up.
     * Changes the page to the modify page.
     * @param view
     */
    public void modify(View view) {
        pw.dismiss();
        setContentView(R.layout.modify);
    }

    /**
     * Action listener for the add equipment button associated to the modify equipment page.
     * Adds the specified amount for equipment to the current quantity.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
    public void addAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        //Checks for empty fields.
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

    /**
     * Action listener for the remove equipment button associated to the modify equipment page.
     * Removes the specified amount for equipment to the current quantity.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
    public void removeAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        //Checks for empty fields.
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

    /**
     * Action listener for the back button associated to the modify equipment page.
     * Restarts the activity to go back to the initial page.
     * @param view
     */
    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }

    /**
     * Action listener for the add new equipment button.
     * Changes the page to the add equipment activity.
     * @see AddEquipment
     * @param view
     */
    public void addEquipment(View view) {
        Intent intent = new Intent(ManageEquipment.this, AddEquipment.class);
        startActivity(intent);
        finish();
    }
}

