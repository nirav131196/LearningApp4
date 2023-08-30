package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class FaceAuthentication extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_authentication);
    }
    @Override
    public void onBackPressed() {
        Intent i  = new Intent(FaceAuthentication.this,DialogBox.class);
        startActivity(i);
        finish();
    }
}