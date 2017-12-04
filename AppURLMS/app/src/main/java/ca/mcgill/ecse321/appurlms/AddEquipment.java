package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class AddEquipment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_equipment);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    public void addEquipment(View view) {
        TextView addEquipMessage = (TextView) findViewById(R.id.addEquipMessage);
        EditText tv1 = (EditText) findViewById(R.id.equipment_name);
        EditText tv2 = (EditText) findViewById(R.id.equipment_quantity);

        if(TextUtils.isEmpty(tv1.getText().toString())) {
            addEquipMessage.setText("Missing info to add the equipment!");
        }
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

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(AddEquipment.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(AddEquipment.this, ManageEquipment.class);
        startActivity(intent);
        finish();
    }
}
