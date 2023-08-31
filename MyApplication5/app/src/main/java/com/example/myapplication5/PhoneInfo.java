package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class PhoneInfo extends AppCompatActivity {

    TextView txtphoneinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_info);

        txtphoneinfo = findViewById(R.id.txtphoneinfo);

        String info = getDeviceName();
        txtphoneinfo.setText(" DEVICE INFO : "+info);
    }
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}