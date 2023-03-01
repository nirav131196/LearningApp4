package com.example.loginapp;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

        public  void showToast(String message)
        {
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
        }
}
