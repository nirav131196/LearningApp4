package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class OrderHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i =new Intent(OrderHistory.this,Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
    }
}