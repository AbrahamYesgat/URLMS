package ca.mcgill.ecse321.appurlms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
//import android.database.sqlite.SQLiteDatabase;

import ca.mcgill.ecse321.urlms.controller.URLMSController;
import ca.mcgill.ecse321.urlms.model.*;
import ca.mcgill.ecse321.urlms.persistence.PersistenceXStream;
//import ca.mcgill.ecse321.appurlms.persistence.SQLController;

public class MainActivity extends AppCompatActivity {

    private URLMS urlms = null;
    //private SQLController sqlController = null;
    private String fileName;
    String error = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //fileName = getFilesDir().getAbsolutePath() + "/output/database.db";
        //sqlController = new SQLController(this);
        //SQLiteDatabase mydb = sqlController.getWritableDatabase();
        //urlms = sqlController.loadURLMSFromDatabase(mydb);
        PersistenceXStream.initializeURLMS(getFilesDir().getAbsolutePath() +"data.xml");
        urlms = (URLMS) PersistenceXStream.loadFromXMLwithXStream();

        Button button = (Button) findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        refreshData();
    }

    private void refreshData() {
        TextView tv1 = (TextView) findViewById(R.id.user_email);
        TextView tv2 = (TextView) findViewById(R.id.user_password);
        tv1.setText("");
        tv2.setText("");
    }

    public void login(View v) {
        TextView tv1 = (TextView) findViewById(R.id.user_email);
        TextView tv2 = (TextView) findViewById(R.id.user_password);
        URLMSController cont = new URLMSController(urlms);

        boolean isValid = cont.login(tv1.getText().toString(), tv2.getText().toString());
        if(!isValid) {
            TextView loggingMessage = (TextView) findViewById(R.id.loggingMessage);
            loggingMessage.setText("Wrong username/password combination!");
        }
        else{
            TextView loggingMessage = (TextView) findViewById(R.id.loggingMessage);
            loggingMessage.setText("Successfully logged in as \n" + tv1.getText().toString());
            refreshData();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
