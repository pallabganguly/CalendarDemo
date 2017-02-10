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

public class DailyExpense extends Main{

    EditText expense_entry;
    int category = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_expense);
        expense_entry=(EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        String dateData = intent.getStringExtra("passarg");

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
                    myMessage("3 selected");


                }
                else
                    myMessage("not selected");
                break;
            case R.id.radioButton5: // transportation
                if(status)
                {

                    // StringBuffer str= new StringBuffer(expense_entry.getText().toString());

                    // str= str.append("...4");

                    category=2;

                    myMessage("4 selected");
                }break;

            case R.id.radioButton6 : // entertainment
                if(status)
                {

                    category=3;
                    myMessage("5 selected");
                }
                break;

            case R.id.radioButton7 : // food
                if(status)
                {

                    category=4;
                    myMessage("6 selected");
                }
                break;

        }


    }


    public void myMessage(String str)
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
    }
}
