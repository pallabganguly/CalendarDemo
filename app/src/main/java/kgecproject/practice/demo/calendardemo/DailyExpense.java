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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_expense);
        expense_entry=(EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        times += 1;
        dateData = intent.getStringExtra("passarg");
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
            String getResult = dbref.DisplayByDate(dateData);
            if (category == 1) { // education
                int value = Integer.parseInt(expense_entry.getText().toString());
                long id;

                if (getResult.isEmpty())
                    id = dbref.InsertRecord(dateData, value, 0, 0, 0);
                else
                    id = dbref.UpdateOneArgRecord(dateData, value, 0, 0, 0);
            }
            if (category == 2) { // transport
                int value = Integer.parseInt(expense_entry.getText().toString());
                long id;
                if (getResult.isEmpty())
                    id = dbref.InsertRecord(dateData, 0, value, 0, 0);
                else
                    id = dbref.UpdateOneArgRecord(dateData, 0, value, 0, 0);
            }
            if (category == 3) { // entertainment
                int value = Integer.parseInt(expense_entry.getText().toString());
                long id;
                if (getResult.isEmpty())
                    id = dbref.InsertRecord(dateData, 0, 0, value, 0);
                else
                    id = dbref.UpdateOneArgRecord(dateData, 0, 0, value, 0);
            }
            if (category == 4) { // food
                int value = Integer.parseInt(expense_entry.getText().toString());
                long id;
                if (getResult.isEmpty())
                    id = dbref.InsertRecord(dateData, 0, 0, 0, value);
                else
                    id = dbref.UpdateOneArgRecord(dateData, 0, 0, 0, value);
            }
        }

    }
    public  void image1(View view)
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