package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class NavigationComponents_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_components);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}