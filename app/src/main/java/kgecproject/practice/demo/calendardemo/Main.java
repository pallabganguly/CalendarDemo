package kgecproject.practice.demo.calendardemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static kgecproject.practice.demo.calendardemo.R.layout.estimate;

/**
 * Created by pallab on 7/2/17.
 */

public class Main extends AppCompatActivity {


    EditText usrfld, pwfld;
    MySQLiteAdapter mySQLiteAdapter;
    Context context;
    //protected int education, transport, entertainment, food;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Expense Calculator");

        usrfld = (EditText)findViewById(R.id.editText0);
        pwfld = (EditText)findViewById(R.id.editText5);
        context = this;
        mySQLiteAdapter = new MySQLiteAdapter(context);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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

    public void uAuth(View view) {
        String username = usrfld.getText().toString();
        String password = pwfld.getText().toString();

        // Query users db to match username und password
        String searchrecords = mySQLiteAdapter.SearchVoidArgRecord(username, password);

        if (searchrecords.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Oops! Does\'nt seem right!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "You\'re logged in!", Toast.LENGTH_SHORT).show();
            Intent estimateScreen = new Intent(getApplicationContext(),DateCal.class);
            startActivity(estimateScreen);
        }
    }

    public void newUser(View view) {
        String username = usrfld.getText().toString();
        String password = pwfld.getText().toString();

        // insert as new User
        long id=mySQLiteAdapter.InsertRecord(username, password);

        if (id<0) { //Unsucsessful Insertion
            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "You\'re signed up!", Toast.LENGTH_SHORT).show();
            Intent estimateScreen = new Intent(getApplicationContext(),Estimate.class);
            startActivity(estimateScreen);
        }
    }
}
