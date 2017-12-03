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

public class ManageFundingAccounts extends AppCompatActivity {
    FundingAccount modifyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_funding_accounts);

        TextView nameMessage = (TextView)findViewById(R.id.accounts_message);
        nameMessage.setText("Funding Accounts for " + cont.getActiveLaboratory().getName());

        List<FundingAccount> accounts = cont.getActiveLaboratory().getFundingAccounts();

        int i= 0;
        String[] accountArray = new String[accounts.size()];
        for (FundingAccount account : accounts) {
            accountArray[i] = Integer.toString(account.getAccountNumber()) +": "+ Double.toString(account.getCurrentBalance()) + "$";
            i++;
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.list, accountArray);

        ListView listView = (ListView) findViewById(R.id.account_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                modifyAccount = cont.getActiveLaboratory().getFundingAccount(position);
                showPopup();
            }
        });

    }

    private PopupWindow pw;
    private void showPopup() {
        hideSoftKeyboard(ManageFundingAccounts.this);

        LayoutInflater inflater = LayoutInflater.from(ManageFundingAccounts.this);
        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.modify_account_pop_up,
                (ViewGroup) findViewById(R.id.popup_1));
        pw = new PopupWindow(layout, 1200, 500, true);
        pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
    }

    public void back(View view) {
        Intent intent = new Intent(ManageFundingAccounts.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(ManageFundingAccounts.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void addAccount(View view) {
        Intent intent = new Intent(ManageFundingAccounts.this, AddFundingAccount.class);
        startActivity(intent);
        finish();
    }

    public void modifyAccount(View view) {
        pw.dismiss();
        setContentView(R.layout.modify_funds);
    }

    public void cancel(View view) {
        pw.dismiss();
    }

    public void addFunds(View view) {
        EditText value = (EditText) findViewById(R.id.funds_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();
        int accID = modifyAccount.getAccountNumber();

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

    public void removeFunds(View view) {
        EditText value = (EditText) findViewById(R.id.funds_change);
        TextView modifyMessage = (TextView) findViewById(R.id.modify_message);
        String quantity = value.getText().toString();
        int accID = modifyAccount.getAccountNumber();

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

    public void previous(View view) {
        finish();
        startActivity(getIntent());
    }
}
