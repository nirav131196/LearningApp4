package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StateList_with_buttons_Activity extends AppCompatActivity {

    TextView txtMyItem;
    Button btnBack,btnNext;
    ArrayList<String> MyStateList = new ArrayList<>();

    int pos = 0, itemPosition,countryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list_with_buttons);

        txtMyItem = findViewById(R.id.txtMyItem);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);

        countryPosition = getIntent().getIntExtra("COUNTRYPOSITION",1);

        itemPosition = getIntent().getIntExtra("STATEPOSITION",1);

        if(countryPosition == 0)
        {
            MyStateList.add("GUJARAT");
            MyStateList.add("MAHARASHTRA");
            MyStateList.add("PUNJAB");
        }
        else if(countryPosition == 1)
        {
            MyStateList.add("A1");
            MyStateList.add("A2");
            MyStateList.add("A3");
        }
        else if(countryPosition == 2)
        {
            MyStateList.add("C 1");
            MyStateList.add("C 2");
            MyStateList.add("C 3");
        }
        else
        {
            MyStateList.add("USA 1");
            MyStateList.add("USA 2");
            MyStateList.add("USA 3");
        }


        txtMyItem.setText(MyStateList.get(itemPosition));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemPosition != 0)
                {
                    --itemPosition;
                    txtMyItem.setText(MyStateList.get(itemPosition));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Try Next item", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemPosition != 2)
                {
                    ++itemPosition;
                    txtMyItem.setText(MyStateList.get(itemPosition));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Try Previous item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}