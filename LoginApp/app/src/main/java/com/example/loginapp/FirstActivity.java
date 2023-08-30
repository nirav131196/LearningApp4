package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {


    Button btnSQLite,btnCalculator,btnCalculator2,btnRegisterAPIVolley,btnRecyclerAndListButton,btnFliterAPI,btnImageAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();

        btnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,SplashScreen.class);
                startActivity(i);

            }
        });
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,CalculatorActivity.class);
                startActivity(i);

            }
        });
        btnCalculator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,CalculatorActivity2.class);
                startActivity(i);

            }
        });
        btnRegisterAPIVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FirstActivity.this,RegistrationAPIActivity.class);
                startActivity(i);

            }
        });
        btnRecyclerAndListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,RecyclerAndListView_Activity.class);
                startActivity(i);

            }
        });
        btnFliterAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, FliterAPI_Activity.class);
                startActivity(i);

            }
        });
        btnImageAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, ImageAPI.class);
                startActivity(i);

            }
        });
    }

    private void initView() {

        btnSQLite = findViewById(R.id.btnSQLite);
        btnCalculator = findViewById(R.id.btnCalculator);
        btnCalculator2 = findViewById(R.id.btnCalculator2);
        btnRegisterAPIVolley  = findViewById(R.id.btnRegisterAPIVolley);
        btnRecyclerAndListButton = findViewById(R.id.btnRecyclerAndList);
        btnFliterAPI = findViewById(R.id.btnFliterAPI);
        btnImageAPI = findViewById(R.id.btnImageAPI);

    }
}