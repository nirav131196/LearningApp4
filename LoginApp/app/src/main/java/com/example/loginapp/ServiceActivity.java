package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnstart,btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnstart = findViewById(R.id.startButton);
        btnStop = findViewById(R.id.stopButton);

        btnstart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnstart)
        {
            startService(new Intent(this,NewService.class));
        }
        else if(v == btnStop)
        {
            stopService(new Intent(this,NewService.class));
        }

    }
}