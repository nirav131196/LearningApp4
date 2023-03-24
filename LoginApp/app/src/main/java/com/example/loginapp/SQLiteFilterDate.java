package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    String[] items = {"Select Salary","Salary > 50k"};
    String[] items2 = {"Select Designation","IOS","Angular","REACT NATIVE","ANDROID","FLUTTER","PYTHON","C#","JAVA","UI DESIGNER","PHP","LARAVEL"};
    String salary2,designation;
    Button btnApply;
    Object aa;

    Spinner post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_filter_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnApply =(Button)findViewById(R.id.applybutton);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(SQLiteFilterDate.this,SQLite_Select_Record.class);
                i.putExtra("SALARY",salary2);
                i.putExtra("POST",designation);
                startActivity(i);
                finish();
            }
        });
        Spinner salary = findViewById(R.id.Spinner2);
        salary.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salary.setAdapter(ad);

        post = findViewById(R.id.Spinner3);
        post.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                     designation = post.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items2);

        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        post.setAdapter(ad2);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLiteFilterDate.this, SQLite_Select_Record.class);
        startActivity(i);
        finish();

        return super.onSupportNavigateUp();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position ==1){
            salary2 = "50000";
            showToast("Salary Selected");
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}