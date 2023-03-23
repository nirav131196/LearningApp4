package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SQLiteFilterDate extends BaseActivity implements AdapterView.OnItemSelectedListener {

    String[] items = {"Select Salary","Salary > 50k","Same Designation"};
    String[] items2 = {"Select Designation","Salary > 50k","Same Designation"};
    String salary2;
    Button btnApply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_filter_date);

        btnApply =(Button)findViewById(R.id.applybutton);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SQLiteFilterDate.this,SQLite_Select_Record.class);
                i.putExtra("SALARY",salary2);
                i.putExtra("POST",122);
                startActivity(i);
                finish();
                Log.e("My salary 1","SALARY "+salary2);
            }
        });

        Spinner spino = findViewById(R.id.Spinner2);
        spino.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);

        Spinner spino2 = findViewById(R.id.Spinner3);
        spino2.setOnItemSelectedListener(this);

        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items2);

        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad2);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(view.getId())
        {

        }
        if(position == 1)
        {
            salary2 = "50000";

           showToast("Salary Selected");
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}