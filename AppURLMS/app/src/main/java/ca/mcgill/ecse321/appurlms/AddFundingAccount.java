package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class AddFundingAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_funding_account);
    }

    public void addAccount(View view) {
        TextView addAccountMessage = (TextView) findViewById(R.id.addAccountMessage);
        EditText tv1 = (EditText) findViewById(R.id.account_number);
        EditText tv2 = (EditText) findViewById(R.id.account_balance);

        if(TextUtils.isEmpty(tv1.getText().toString())) {
            addAccountMessage.setText("Missing info to add the account!");
        }
        else if(TextUtils.isEmpty(tv2.getText().toString())){
            String id = tv1.getText().toString();
            boolean isValid = cont.createFundingAccount(0,Integer.parseInt(id));
            if(isValid) {
                Intent intent = new Intent(AddFundingAccount.this, ManageFundingAccounts.class);
                startActivity(intent);
                finish();
            }
            else {
                addAccountMessage.setText("This account number already exists. Please just change the balance.");
            }
        }
        else {
            boolean isValid = cont.createFundingAccount(Double.parseDouble(tv2.getText().toString()), Integer.parseInt(tv1.getText().toString()));
            if(isValid) {
                Intent intent = new Intent(AddFundingAccount.this, ManageFundingAccounts.class);
                startActivity(intent);
                finish();
            }
            else {
                addAccountMessage.setText("This account number already exists. Please just change the amount needed.");
            }
        }
    }

    public void back(View view) {
        Intent intent = new Intent(AddFundingAccount.this, ManageFundingAccounts.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(AddFundingAccount.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
