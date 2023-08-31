package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SimCardInfo extends AppCompatActivity {

    TextView txtsim;
    String imei;
    String carreirname,operatorName,NetOpeName,softwareVersion,countryIso,simSerialNumber,subscriberid;
    int simState;
    TelephonyManager telephonyManager;
    final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_card_info);

        txtsim = findViewById(R.id.txtsimcard);
        txtsim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(SimCardInfo.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
                {
                    telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        imei = telephonyManager.getImei();
                        carreirname = telephonyManager.getSimOperatorName();
                        operatorName = telephonyManager.getSimOperator();
                        NetOpeName = telephonyManager.getNetworkOperatorName();
                        softwareVersion = telephonyManager.getDeviceSoftwareVersion();
                        countryIso = telephonyManager.getSimCountryIso();
                        simSerialNumber = telephonyManager.getSimSerialNumber();
                        simState = telephonyManager.getSimState();
                        subscriberid = telephonyManager.getSubscriberId();

                        txtsim.setText("IMEI NO : "+ imei +"\nSim Name : "+carreirname + "\nOperator Name : " +operatorName + "\nNetwork Operator Name : "+ NetOpeName +"\nSofetware Version : "+ softwareVersion +"\nCountry Iso : "+ countryIso +"\nSerial Number : "+ simSerialNumber +"\nSim state : "+ simState +"\nSubscriber Id : "+ subscriberid);

                    }
                }
                else
                {
                    Toast.makeText(SimCardInfo.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
        {
            telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imei = telephonyManager.getImei();
                carreirname = telephonyManager.getSimOperatorName();
                operatorName = telephonyManager.getSimOperator();
                NetOpeName = telephonyManager.getNetworkOperatorName();
                softwareVersion = telephonyManager.getDeviceSoftwareVersion();
                countryIso = telephonyManager.getSimCountryIso();
                simSerialNumber = telephonyManager.getSimSerialNumber();
                simState = telephonyManager.getSimState();
                subscriberid = telephonyManager.getSubscriberId();

                txtsim.setText("IMEI NO : "+ imei +"\nSim Name : "+carreirname + "\nOperator Name : " +operatorName + "\nNetwork Operator Name : "+ NetOpeName +"\nSofetware Version : "+ softwareVersion +"\nCountry Iso : "+ countryIso +"\nSerial Number : "+ simSerialNumber +"\nSim state : "+ simState +"\nSubscriber Id : "+ subscriberid);

            }
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        }
        }

    @Override
    public void onBackPressed() {
        finish();
    }
}