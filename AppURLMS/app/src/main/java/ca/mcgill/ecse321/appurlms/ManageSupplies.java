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

/**
 * This page is set when the user selects the manage supplies button from the lab page.
 */
public class ManageSupplies extends AppCompatActivity {

    Supplies modifySupply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_supplies);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Display lab name at the top of the page.
        TextView nameMessage = (TextView)findViewById(R.id.supply_message);
        nameMessage.setText("Supplies for " + cont.getActiveLaboratory().getName());

        List<Supplies> supplies = cont.getActiveLaboratory().getSupplies();

        //Display the supplies names and quantity of each.
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
                //If a supply from the list is selected, open the pop up.
                modifySupply = cont.getActiveLaboratory().getSupply(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;

    /**
     * This is used to have the associated pop up message page to appear on the screen.
     * It indicated whether the user wants to modify or delete the selected supply.
     * Cancel button to dismiss the pop up.
     */
    private void showPopup() {
        hideSoftKeyboard(ManageSupplies.this);

        LayoutInflater inflater = LayoutInflater.from(ManageSupplies.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_supply_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 600, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    /**
     *
     * Action listener for the add new supply button.
     * Changes the page to the add supply activity.
     * @see AddSupply
     * @param view
     */
    public void addSupply(View view) {
        Intent intent = new Intent(ManageSupplies.this, AddSupply.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ManageSupplies.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the logout button.
     * Logs the user out of the system and sets the activity to the login page.
     * @see MainActivity
     * @param view
     */
    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ManageSupplies.this, MainActivity.class);
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
     * Deletes the selected supply.
     * @param view
     */
    public void delete(View view) {
        boolean isValid = cont.removeSupplies(modifySupply.getName());
        if(isValid) {
            finish();
            startActivity(getIntent());
        }
    }

    /**
     * Action listener for the modify supply button associated to the pop up.
     * Changes the page to the modify page.
     * @param view
     */
    public void modify(View view) {
        pw.dismiss();
        setContentView(R.layout.modify);
    }

    /**
     * Action listener for the add supply button associated to the modify supply page.
     * Adds the specified amount for supply to the current quantity.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
    public void addAmount(View view) {
        EditText value = (EditText) findViewById(R.id.delta_change);
        TextView modifySupplyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();

        //Checks for empty fields.
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

    /**
     * Action listener for the remove supply button associated to the modify supply page.
     * Removes the specified amount for supply to the current quantity.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
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

    /**
     * Action listener for the back button associated to the modify supply page.
     * Restarts the activity to go back to the initial page.
     * @param view
     */
    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }
}
