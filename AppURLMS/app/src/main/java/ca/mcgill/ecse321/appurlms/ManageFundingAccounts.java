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

import ca.mcgill.ecse321.urlms.model.FundingAccount;

import static ca.mcgill.ecse321.appurlms.AddStaff.hideSoftKeyboard;
import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

/**
 * This page is set when the user selects the manage funding accounts button from the lab page.
 * This is only allowed for the director of the current lab.
 */
public class ManageFundingAccounts extends AppCompatActivity {
    FundingAccount modifyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_funding_accounts);
        //This sets the logo in the bar at the top of the screen.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        //Displays the lab name on top of the screen
        TextView nameMessage = (TextView)findViewById(R.id.accounts_message);
        nameMessage.setText("Funding Accounts for " + cont.getActiveLaboratory().getName());

        List<FundingAccount> accounts = cont.getActiveLaboratory().getFundingAccounts();

        //Displays a list of all the funding accounts and their current balances.
        int i= 0;
        String[] accountArray = new String[accounts.size()];
        for (FundingAccount account : accounts) {
            accountArray[i] = Integer.toString(account.getAccountNumber()) +": "+ String.format("%.2f",account.getCurrentBalance()) + "$";
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, accountArray);

        ListView listView = (ListView) findViewById(R.id.account_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //If a funding account from the list is selected, open the pop up.
                modifyAccount = cont.getActiveLaboratory().getFundingAccount(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;

    /**
     * This is used to have the associated pop up message page to appear on the screen.
     * It indicated whether the user wants to modify the selected funding account.
     * Cancel button to dismiss the pop up.
     */
    private void showPopup() {
        hideSoftKeyboard(ManageFundingAccounts.this);

        LayoutInflater inflater = LayoutInflater.from(ManageFundingAccounts.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_account_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 500, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    /**
     * Action listener for the back button.
     * Brings the user back to the lab page.
     * @see LabPage
     * @param view
     */
    public void back(View view) {
        Intent intent = new Intent(ManageFundingAccounts.this, LabPage.class);
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
            Intent intent = new Intent(ManageFundingAccounts.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Action listener for the add new funding account button.
     * Changes the page to the add funding account activity.
     * @see AddFundingAccount
     * @param view
     */
    public void addAccount(View view) {
        Intent intent = new Intent(ManageFundingAccounts.this, AddFundingAccount.class);
        startActivity(intent);
        finish();
    }

    /**
     * Action listener for the modify funding account balance button associated to the pop up.
     * Changes the page to the modify funds page.
     * @param view
     */
    public void modifyAccount(View view) {
        pw.dismiss();
        setContentView(R.layout.modify_funds);
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
     * Action listener for the add funds button associated to the modify funds page.
     * Adds the specified amount to the funding account balance.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
    public void addFunds(View view) {
        EditText value = (EditText) findViewById(R.id.funds_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();
        int accID = modifyAccount.getAccountNumber();

        //Checks empty fields
        if(quantity.isEmpty()){
            modifyMessage.setText("Please indicate an amount to modify the account balance.");
        }
        else {
            boolean isValid = cont.modifyFunds(Double.parseDouble(quantity), accID);
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
     * Action listener for the remove funds button associated to the modify funds page.
     * Removes the specified amount from the funding account balance.
     * If successful then restarts this activity in order to update the list.
     * @param view
     */
    public void removeFunds(View view) {
        EditText value = (EditText) findViewById(R.id.funds_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();
        int accID = modifyAccount.getAccountNumber();

        //Checks for empty fields.
        if(quantity.isEmpty()){
            modifyMessage.setText("Please indicate an amount to modify the account balance.");
        }
        else {
            boolean isValid = cont.modifyFunds((-1*Double.parseDouble(quantity)), accID);
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
     * Action listener for the back button associated to the modify funds page.
     * Restarts the activity to go back to the initial page.
     * @param view
     */
    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }
}
