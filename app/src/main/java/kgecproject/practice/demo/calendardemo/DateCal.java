package kgecproject.practice.demo.calendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

/**
 * Created by pallab on 8/2/17.
 */

public class DateCal extends Main {

    CalendarView calendar;
    TextView textView;

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

                Intent nextScreen = new Intent(getApplicationContext(),DailyExpense.class);
                int dateArray[] = new int[3];
                dateArray[0] = dayOfMonth;
                dateArray[1] = month+1;
                dateArray[2] = year;
                nextScreen.putExtra("passarg", dateArray);
                startActivity(nextScreen);


            }
        });
    }

}
