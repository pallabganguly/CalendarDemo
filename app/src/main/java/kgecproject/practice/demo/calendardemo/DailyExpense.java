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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_expense);
        expense_entry=(EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        dateData = intent.getStringExtra("passarg");

    }

    public void cancel(View view) {
        Intent prevScreen = new Intent(getApplicationContext(),DateCal.class);
        startActivity(prevScreen);
    }

    public void save(View view)
    {
        if(category==1)
            myMessage("..1");
        if(category==2)
            myMessage("..2");
        if(category==3)
            myMessage("..3");
        if(category==4)
            myMessage("..4");

    }
    public  void image1(View view)
    {
        boolean status = ((RadioButton)view).isChecked();

        switch (view.getId())
        {
            case R.id.radioButton4 : //Education
                if(status)
                {
                    category= 1;

                    int value = Integer.parseInt(expense_entry.getText().toString());
                    long id = dbref.InsertRecord(dateData, value, 0, 0, 0);

                } break;

            case R.id.radioButton5: // transportation
                if(status)
                {
                    category=2;

                    int value = Integer.parseInt(expense_entry.getText().toString());
                    long id = dbref.InsertRecord(dateData, 0, value, 0, 0);
                } break;

            case R.id.radioButton6 : // entertainment
                if(status)
                {
                    category=3;

                    int value = Integer.parseInt(expense_entry.getText().toString());
                    long id = dbref.InsertRecord(dateData, 0, 0, value, 0);
                } break;

            case R.id.radioButton7 : // food
                if(status)
                {
                    category=4;

                    int value = Integer.parseInt(expense_entry.getText().toString());
                    long id = dbref.InsertRecord(dateData, 0, 0, 0, value);
                } break; // We were on a break!!!

        }
    }


    public void myMessage(String str)
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
}
