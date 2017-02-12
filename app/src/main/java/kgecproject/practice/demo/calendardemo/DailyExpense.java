package kgecproject.practice.demo.calendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by pallab on 9/2/17.
 */

public class DailyExpense extends Estimate{

    EditText expense_entry;
    int category = 0;
    String dateData;
    int times = 0;
    int value1 = 0,value2 = 0,value3 = 0,value4 = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_expense);
        expense_entry=(EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        times += 1;
        dateData = intent.getStringExtra("passarg");
        Toast.makeText(getApplicationContext(), dateData, Toast.LENGTH_LONG).show();
        curconext = this;
        dbref = new MySQLiteSecond(curconext);
        //myMessage(""+times);
    }

    public void cancel(View view) {
        Intent prevScreen = new Intent(getApplicationContext(),DateCal.class);
        startActivity(prevScreen);
    }

    public void save(View view)
    {
        if(expense_entry.getText().toString().isEmpty()) {
            myMessage("Enter some expenses");
        }
        else {
            if (category == 1) // education
                value1 = Integer.parseInt(expense_entry.getText().toString());

            if (category == 2) // transport
                value2 = Integer.parseInt(expense_entry.getText().toString());

            if (category == 3) // entertainment
                value3 = Integer.parseInt(expense_entry.getText().toString());

            if (category == 4) // food
                value4 = Integer.parseInt(expense_entry.getText().toString());

            put(view);
        }

    }
    protected void put(View view) {
        long id;
        String getResult = dbref.DisplayByDate(dateData);
        if (getResult.isEmpty()){
            id = dbref.InsertRecord(dateData, value1, value2, value3, value4);
            myMessage("inserted");
        }
        else{
            id = dbref.UpdateOneArgRecord(dateData, value1, value2, value3, value4);
            myMessage("updated");
        }
        String hi = dbref.DisplayByDate(dateData);
        Toast.makeText(getApplicationContext(), hi, Toast.LENGTH_LONG).show();
    }
    public void image1(View view)
    {
        boolean status = ((RadioButton)view).isChecked();

        switch (view.getId())
        {
            case R.id.radioButton4 : //Education
                if(status)
                    category= 1;
                break;

            case R.id.radioButton5: // transportation
                if(status)
                    category=2;
                break;

            case R.id.radioButton6 : // entertainment
                if(status)
                    category=3;
                break;

            case R.id.radioButton7 : // food
                if(status)
                    category=4;
                break; // We were on a break!!!
        }
    }


    public void myMessage(String str) // for debugging purposes
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
}