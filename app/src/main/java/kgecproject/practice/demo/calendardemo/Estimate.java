package kgecproject.practice.demo.calendardemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pallab on 8/2/17.
 */

public class Estimate extends Main{

    EditText edu, foo, trans, enter;
    Context curconext;
    MySQLiteSecond dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate);
        Intent i = getIntent();
        //String arg=i.getStringExtra("passarg");
        curconext = this;
        dbref = new MySQLiteSecond(curconext);
        edu = (EditText) findViewById(R.id.editText1);
        trans = (EditText) findViewById(R.id.editText2);
        enter = (EditText) findViewById(R.id.editText3);
        foo = (EditText) findViewById(R.id.editText4);
    }

    public void submitData(View view) {
        education = Integer.parseInt(edu.getText().toString());
        transport = Integer.parseInt(trans.getText().toString());
        entertainment = Integer.parseInt(enter.getText().toString());
        food = Integer.parseInt(foo.getText().toString());
        long insid = dbref.InsertRecord("initial", education, transport, entertainment, food);
        if(insid<0)
            Toast.makeText(getApplicationContext(),"Oops, something went wrong!", Toast.LENGTH_LONG).show();
        else {
            Intent nextScreen = new Intent(getApplicationContext(), DateCal.class);
            startActivity(nextScreen);
        }
    }
}
