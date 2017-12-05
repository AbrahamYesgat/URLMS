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
 * This page is set when the user selects to add a new equipment in the manage equipment page.
 * Can only be accessed if the lab is active.
 */
public class AddEquipment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_equipment);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    /**
     * Action listener for the add equipment button.
     * If successfully adds a new equipment then it reuturns to the manage equipment page. If not it displays an error message.
     * @see ManageEquipment
     * @param view
     */
    public void addEquipment(View view) {
        TextView addEquipMessage = (TextView) findViewById(R.id.addEquipMessage);
        EditText tv1 = (EditText) findViewById(R.id.equipment_name);
        EditText tv2 = (EditText) findViewById(R.id.equipment_quantity);

        //Checks for empty name field.
        if(TextUtils.isEmpty(tv1.getText().toString())) {
            addEquipMessage.setText("Missing info to add the equipment!");
        }
        //If the quantity is not specified then it sets it to 0.
        else if(TextUtils.isEmpty(tv2.getText().toString())){
            String name = tv1.getText().toString();
            boolean isValid = cont.createEquipment(name, 0);
            if(isValid) {
                Intent intent = new Intent(AddEquipment.this, ManageEquipment.class);
                startActivity(intent);
                finish();
            }
            else {
                addEquipMessage.setText("This supply type cannot be created!");
            }
        }
        else {
            boolean isValid = cont.createEquipment(tv1.getText().toString(), Integer.parseInt(tv2.getText().toString()));
            if(isValid) {
                Intent intent = new Intent(AddEquipment.this, ManageEquipment.class);
                startActivity(intent);
                finish();
            }
            else {
                addEquipMessage.setText("This supply type cannot be created!");
            }
        }
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
            Intent intent = new Intent(AddEquipment.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the manage equipment page.
     * @see ManageEquipment
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(AddEquipment.this, ManageEquipment.class);
        startActivity(intent);
        finish();
    }
}
