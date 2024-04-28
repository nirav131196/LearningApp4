package com.example.myapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("ACTIVITY STATE","ON CREATE");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.e("ACTIVITY STATE","ON START");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("ACTIVITY STATE","ON RESUME");

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e("ACTIVITY STATE","ON PAUSE");

    }

    @Override
    protected void onRestart() {
        super.onRestart();


        Log.e("ACTIVITY STATE","ON RESTART");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.e("ACTIVITY STATE","ON STOP");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.e("ACTIVITY STATE","ON DESTRoy");

    }
}