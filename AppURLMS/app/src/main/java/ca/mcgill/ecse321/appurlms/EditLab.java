package ca.mcgill.ecse321.appurlms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;

import static ca.mcgill.ecse321.appurlms.MainActivity.cont;

public class EditLab extends AppCompatActivity {
    private EditText labName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_lab);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        labName = (EditText) findViewById(R.id.lab_name);
        labName.setText(cont.getActiveLaboratory().getName());
    }

    public void logout(View view) {
        boolean isValid = cont.logout();
        if(isValid) {
            Intent intent = new Intent(EditLab.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void returnToPrevious(View view) {
        Intent intent = new Intent(EditLab.this, LabPage.class);
        startActivity(intent);
        finish();
    }

    private boolean active;
    public void addDate(View view) {
        labName = (EditText) findViewById(R.id.lab_name);
        TextView labMessage = (TextView) findViewById(R.id.modifyLabMessage);
        CheckBox isActive = (CheckBox)findViewById(R.id.active_checkbox);

        if(TextUtils.isEmpty(labName.getText().toString())) {
            labMessage.setText("Cannot change lab name to specified name!");
        }
        else {
            active = isActive.isChecked();
            setContentView(R.layout.modify_lab_select_date);
        }
    }

    public void modifyLab(View view) {
        DatePicker labStartDate = (DatePicker) findViewById(R.id.lab_start_date);
        int day = labStartDate.getDayOfMonth();
        int month = labStartDate.getMonth() + 1;
        int year = labStartDate.getYear();
        String name = labName.getText().toString();

        boolean isValid = cont.updateLab(name, new Date(year, month,day), active);
        if(isValid) {
            Intent intent = new Intent(EditLab.this, LabPage.class);
            startActivity(intent);
            finish();
        }
        else {
            setContentView(R.layout.edit_lab);
            TextView addLabMessage = (TextView) findViewById(R.id.modifyLabMessage);
            addLabMessage.setText("Cannot change lab name to specified name!");
        }
    }
}
