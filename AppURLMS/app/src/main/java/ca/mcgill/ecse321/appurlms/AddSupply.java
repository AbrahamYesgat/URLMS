package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class AddSupply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_supply);


    }

    public void back(View view) {
        Intent intent = new Intent(AddSupply.this, ManageSupplies.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(AddSupply.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void addSupply(View view) {
        TextView addSupplyMessage = (TextView) findViewById(R.id.addSupplyMessage);
        EditText tv1 = (EditText) findViewById(R.id.supply_name);
        EditText tv2 = (EditText) findViewById(R.id.supply_quantity);

        if(TextUtils.isEmpty(tv1.getText().toString())) {
            addSupplyMessage.setText("Missing info to add the supplies!");
        }
        else if(TextUtils.isEmpty(tv2.getText().toString())){
            String name = tv1.getText().toString();
            boolean isValid = cont.createSupplies(name, 0);
            if(isValid) {
                Intent intent = new Intent(AddSupply.this, ManageSupplies.class);
                startActivity(intent);
                finish();
            }
            else {
                addSupplyMessage.setText("This supply type already exists. Please just change the amount needed.");
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
                addSupplyMessage.setText("This supply type already exists. Please just change the amount needed.");
            }
        }
    }
}
