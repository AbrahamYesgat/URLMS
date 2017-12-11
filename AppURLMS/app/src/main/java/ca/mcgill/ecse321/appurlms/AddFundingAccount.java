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
 * This page is set when the user selects to add a funding account to the currently selected lab.
 * This can only be accessed by a director.
 */
public class AddFundingAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_funding_account);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    /**
     * Action listener for the add account button.
     * If it successfully adds a funding account then it returns to the manage funding accounts page.
     * If it is not successful then it displays an error message.
     * @param view
     */
    public void addAccount(View view) {
        TextView addAccountMessage = (TextView) findViewById(R.id.addAccountMessage);
        EditText tv1 = (EditText) findViewById(R.id.account_number);
        EditText tv2 = (EditText) findViewById(R.id.account_balance);

        //Checks if the account number field is empty.
        if(TextUtils.isEmpty(tv1.getText().toString().trim())) {
            addAccountMessage.setText("Missing info to add the account!");
        }
        //If funds are not specified then it sets it to 0.
        else if(TextUtils.isEmpty(tv2.getText().toString().trim())){
            String id = tv1.getText().toString();
            boolean isValid = cont.createFundingAccount(0,Integer.parseInt(id));
            if(isValid) {
                Intent intent = new Intent(AddFundingAccount.this, ManageFundingAccounts.class);
                startActivity(intent);
                finish();
            }
            else {
                addAccountMessage.setText("This account number cannot be used!");
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
                addAccountMessage.setText("This account number cannot be used!");
            }
        }
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the manage funding account page.
     * @see ManageFundingAccounts
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(AddFundingAccount.this, ManageFundingAccounts.class);
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
            Intent intent = new Intent(AddFundingAccount.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
