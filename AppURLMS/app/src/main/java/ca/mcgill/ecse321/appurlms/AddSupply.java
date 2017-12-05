package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects to add a new supply to the currently selected lab.
 * This is only allowed for both the director and the staff members of the lab.
 */
public class AddSupply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_supply);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the manage supplies page.
     * @see ManageSupplies
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(AddSupply.this, ManageSupplies.class);
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
            Intent intent = new Intent(AddSupply.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the add supply button.
     * Successful add returns the page to he manage supply activity.
     * @see ManageSupplies
     * @param view
     */
    public void addSupply(View view) {
        TextView addSupplyMessage = (TextView) findViewById(R.id.addSupplyMessage);
        EditText tv1 = (EditText) findViewById(R.id.supply_name);
        EditText tv2 = (EditText) findViewById(R.id.supply_quantity);

        //Check for empty name
        if(TextUtils.isEmpty(tv1.getText().toString())) {
            addSupplyMessage.setText("Missing info to add the supplies!");
        }
        //If quantity is unspecified, set it to 0.
        else if(TextUtils.isEmpty(tv2.getText().toString())){
            String name = tv1.getText().toString();
            boolean isValid = cont.createSupplies(name, 0);
            if(isValid) {
                Intent intent = new Intent(AddSupply.this, ManageSupplies.class);
                startActivity(intent);
                finish();
            }
            else {
                addSupplyMessage.setText("This supply type cannot be created!");
            }
            }
        else {
            boolean isValid = cont.createSupplies(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            if(isValid) {
                Intent intent = new Intent(AddSupply.this, ManageSupplies.class);
                startActivity(intent);
                finish();
            }
            else {
                addSupplyMessage.setText("This supply type cannot be created!");
            }
        }
    }
}
