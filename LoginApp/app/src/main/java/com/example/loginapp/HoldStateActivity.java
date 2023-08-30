package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HoldStateActivity extends AppCompatActivity {

    Button btnIncrement;
    TextView tvView;
     int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_state);

        count = 0;
        initView();
        toolBar();
        clickbtnIncrement();
    }
    private void clickbtnIncrement() {

        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvView.setText(""+count);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("key_counter",count);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("key_counter",0);
        tvView.setText(""+count);
    }

    public void initView() {

        btnIncrement = findViewById(R.id.btnSubmit);
        tvView = findViewById(R.id.tvViewData);
    }
    private void toolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(HoldStateActivity.this, DialogBox.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
}