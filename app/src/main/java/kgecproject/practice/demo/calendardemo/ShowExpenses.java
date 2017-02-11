package kgecproject.practice.demo.calendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by pallab on 12/2/17.
 */

public class ShowExpenses extends DateCal{

    TextView first, second, third, fourth, fifth;
    String arg;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_page);
        Intent i = getIntent();
        arg = i.getStringExtra("passarg");
        first = (TextView) findViewById(R.id.textView11);
        second = (TextView) findViewById(R.id.textView12);
        third = (TextView) findViewById(R.id.textView13);
        fourth = (TextView) findViewById(R.id.textView14);
        fifth = (TextView) findViewById(R.id.textView15);

        first.setText(arg);
        int res[] = dbref.SearchOneArgRecord(arg);
        second.setText("Education "+res[1]);
        third.setText("Transport "+res[2]);
        fourth.setText("Entertainment "+res[3]);
        fifth.setText("Food "+res[4]);
    }
    public void backToSquareOne(View view) {
        Intent previousScreen = new Intent(getApplicationContext(),DateCal.class);
        startActivity(previousScreen);
    }
}
