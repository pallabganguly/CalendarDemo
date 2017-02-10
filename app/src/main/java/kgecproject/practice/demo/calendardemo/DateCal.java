package kgecproject.practice.demo.calendardemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pallab on 8/2/17.
 */

public class DateCal extends Estimate {

    CalendarView calendar;
    TextView textView;
    Spinner mySpinner1;
    String options [] = {"Show Expenses", "Edit Expenses", "Revise Estimate"};
    String dateString = "";
    int dateArray[] = new int[3];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent = getIntent();

        calendar = (CalendarView)findViewById(R.id.calendarView1);
        textView = (TextView)findViewById(R.id.textView1);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // textView.setText("Date is : " + dayOfMonth +" / " + (month+1) + " / " + year);

                /*  call DayExpense screen and associated view
                    passing args dayOfMonth, month+1, year preferably as an Array
                 */
                dateArray[0] = dayOfMonth;
                dateArray[1] = month+1;
                dateArray[2] = year;

                StringBuffer buff = new StringBuffer();
                buff.append(dayOfMonth+""+(month+1)+""+year+"\n");
                dateString = buff.toString();
                mySpinner1Initiate();
            }
        });
    }

    private void mySpinner1Initiate() {
        mySpinner1=(Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner1.setAdapter(myAdapter1);
        mySpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0) //Go to view page
                    Toast.makeText(getApplicationContext(), "Hi :(", Toast.LENGTH_SHORT).show();
                if(i == 1){
                    Intent nextScreen = new Intent(getApplicationContext(),DailyExpense.class);
                    nextScreen.putExtra("passarg", dateString);
                    startActivity(nextScreen);
                }
                // myMessage1("Selected food is - " + options[i]);
                if(i == 2) {
                    Intent estimateScreen = new Intent(getApplicationContext(), Estimate.class);
                    startActivity(estimateScreen);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Select an option!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
