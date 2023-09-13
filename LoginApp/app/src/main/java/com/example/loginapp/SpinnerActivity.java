package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;

    EditText edtItem;
    Button btnEnter,btnDelete;

    String data;
    ArrayAdapter ad;


    String[] courses = {"C","Data structure","Algorithms","DSA with java","os"};

    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        edtItem = findViewById(R.id.edtItem);
        btnEnter = findViewById(R.id.btnEnter);
        btnDelete = findViewById(R.id.btnDelete);


        data = edtItem.getText().toString();



        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtItem.getText().toString().length() > 0)
                {
                    list.add(edtItem.getText().toString());
                    ad.notifyDataSetChanged();
                    edtItem.setText("");
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtItem.getText().toString().length() > 0)
                {
                    list.remove(edtItem.getText().toString());
                    ad.notifyDataSetChanged();
                    edtItem.setText("");
                }
                else
                {
                    Toast.makeText(SpinnerActivity.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });


        list = new ArrayList<>();
        list.add("C");
        list.add("Data structure");
        list.add("Algorithms");
        spinner = findViewById(R.id.spinner2);

        spinner.setOnItemSelectedListener(this);
        ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Toast.makeText(getApplicationContext(), list.get(position), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}