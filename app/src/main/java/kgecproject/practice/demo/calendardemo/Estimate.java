package kgecproject.practice.demo.calendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pallab on 8/2/17.
 */

public class Estimate extends Main{

    EditText edu, foo, trans, enter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate);
        Intent i = getIntent();
        //String arg=i.getStringExtra("passarg");
        edu = (EditText) findViewById(R.id.editText1);
        trans = (EditText) findViewById(R.id.editText2);
        enter = (EditText) findViewById(R.id.editText3);
        foo = (EditText) findViewById(R.id.editText4);
    }

    public void submitData(View view) {
        education = Integer.valueOf(edu.getText().toString());
        transport = Integer.valueOf(trans.getText().toString());
        entertainment = Integer.valueOf(enter.getText().toString());
        food = Integer.valueOf(foo.getText().toString());
        Intent nextScreen = new Intent(getApplicationContext(),DateCal.class);
        startActivity(nextScreen);
    }
}
