package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.mcgill.ecse321.urlms.model.Director;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class LabPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String labName;
        if(cont.getActiveUser() instanceof Director){
            setContentView(R.layout.dir_lab_page);
        }
        else {
            setContentView(R.layout.staff_lab_page);
        }
        labName = cont.getActiveLaboratory().getName();
        TextView labMessage = (TextView)findViewById(R.id.labname_message);
        labMessage.setText(labName);
    }

    public void addStaff(View view) {
        Intent intent = new Intent(LabPage.this, ManageStaff.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(LabPage.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void back(View view) {
        Intent intent = new Intent(LabPage.this, HomePage.class);
        startActivity(intent);
        finish();
    }

    public void manageSupplies(View view) {
        Intent intent = new Intent(LabPage.this, ManageSupplies.class);
        startActivity(intent);
        finish();
    }

    public void manageEquipment(View view) {
        Intent intent = new Intent(LabPage.this, ManageEquipment.class);
        startActivity(intent);
        finish();
    }

    public void editLab(View view) {
        Intent intent = new Intent(LabPage.this, EditLab.class);
        startActivity(intent);
        finish();
    }

    public void manageAccount(View view) {
        Intent intent = new Intent(LabPage.this, ManageFundingAccounts.class);
        startActivity(intent);
        finish();
    }
}
