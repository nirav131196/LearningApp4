package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sh;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sh = getSharedPreferences("DATA",MODE_PRIVATE);
        s1 = sh.getString("STUDENT","");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            /*    if(s1.isEmpty()){*/
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
              //  }
            /*    else{
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }*/


            }
        }, 3000);
    }
}