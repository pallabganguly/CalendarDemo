package kgecproject.practice.demo.calendardemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
    String options [] = {"", "Edit Expenses", "Show Expenses", "Revise Estimate"};
    String dateString = "";
    int dateArray[] = new int[3];
    Point p;
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
                String mahina = getMonth(month+1);
                StringBuffer buff = new StringBuffer();
                buff.append(dayOfMonth+"-"+mahina+"-"+year+"\n");
                dateString = buff.toString();
                int res[] = dbref.SearchOneArgRecord(dateString);
                textView.setText(dateString+"Education: "+res[1]+"\nTransport: "+res[2]+"\nEntertainment: "+res[3]+"\nFood: "+res[4]);
                mySpinner1Initiate();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        //Initialize the Point with x, and y positions
        p = new Point();
        p.x = 40;
        p.y = 40;
    }

    // The method that displays the popup.
    private void showPopup(final Activity context, Point p) {
        int popupWidth = 300;
        int popupHeight = 400;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 30;
        int OFFSET_Y = 30;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY, 10, 10);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                popup.dismiss();
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
                if(i == 1) {//show popup

//                    if (p != null)
//                        showPopup(DateCal.this, p);
                    Intent nextScreen = new Intent(getApplicationContext(),DailyExpense.class);
                    nextScreen.putExtra("passarg", dateString);
                    startActivity(nextScreen);
                }
                if(i == 2){
                    Intent showScreen = new Intent(getApplicationContext(), ShowExpenses.class);
                    showScreen.putExtra("passarg", dateString);
                    startActivity(showScreen);
                }
                if(i == 3) {
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
    String getMonth(int moy) {
        if (moy == 1)
            return "Jan";
        if (moy == 2)
            return "Feb";
        if (moy == 3)
            return "Mar";
        if (moy == 4)
            return "Apr";
        if (moy == 5)
            return "May";
        if (moy == 6)
            return "Jun";
        if (moy == 7)
            return "Jul";
        if (moy == 8)
            return "Aug";
        if (moy == 9)
            return "Sep";
        if (moy == 10)
            return "Oct";
        if (moy == 11)
            return "Nov";
        if (moy == 12)
            return "Dec";
        return "";
    }

}
