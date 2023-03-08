package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome_Main extends AppCompatActivity {

    Button btnCamara,btnLocation,btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);

        initView();
        clickCamaraButton();
        clickLocationButton();
        clickNotificationButton();
    }

    private void clickNotificationButton() {
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Welcome_Main.this,PushNotoficationActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void clickLocationButton() {
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Welcome_Main.this,QRScanner_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void clickCamaraButton() {

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Welcome_Main.this,WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initView() {
        btnCamara = findViewById(R.id.QR_AND_CAMERA);
        btnLocation = findViewById(R.id.LOCATION);
        btnNotification =findViewById(R.id.PUSH_NOTIFICATION);
    }
}