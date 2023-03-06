package com.example.loginapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class QRScanner_Activity extends BaseActivity {

    TextView txtLatitude,txtLongitude,txtLocationAddress;
    Button btnLocation;
    private static final int REQUEST_LOCATION  = 1;
    LocationManager locationManager;
    String latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        initView();
        ClickEventLocationButton();
    }
    private void ClickEventLocationButton() {
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
                if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    OnGPS();
                }
                else
                {
                    getlocation();
                }
            }
        });
    }
    private void getlocation()
    {
            if(ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},REQUEST_LOCATION);
            }
            else
            {
                  Location locationGPS =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                  if(locationGPS != null)
                  {
                      double lat = locationGPS.getLatitude();
                      double lon = locationGPS.getLongitude();
                      latitude = String.valueOf(lat);
                      longitude = String.valueOf(lon);
                      txtLatitude.setText(latitude);
                      txtLongitude.setText(longitude);

                      Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                      List<Address> addresses;

                      try
                      {
                            addresses  =gcd.getFromLocation(locationGPS.getLatitude(),locationGPS.getLongitude(),1);
                            if (addresses.size() > 0)
                            {
                                String address = addresses.get(0).getAddressLine(0);  // complete address
                               /* String locality = addresses.get(0).getLocality();  // city
                                String sublocality = addresses.get(0).getSubLocality();  // sub area
                                String state = addresses.get(0).getAdminArea(); // state
                                String postalCode = addresses.get(0).getPostalCode(); // postal code
                                String knownname = addresses.get(0).getFeatureName();*/

                                txtLocationAddress.setText(address);
                            }
                            else
                            {
                                showToast("Address is null");
                            }
                      }
                      catch (Exception e)
                      {

                      }
                     // txtLocation.setText("Your Location is " + " Latitude : "+latitude +" Longitude : "+longitude);
                  }
                  else
                  {
                      showToast("Unable to Find Location");
                  }
                //  Location locationGPS =locationManager.getCurrentLocation(LocationManager.GPS_PROVIDER,null, ContextCompat.getMainExecutor(getApplicationContext()),null);

            }
    }
    private void OnGPS() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void initView()
    {
        txtLatitude =(TextView) findViewById(R.id.Location_Latitude);
        txtLongitude =(TextView) findViewById(R.id.Location_Longitude);
        txtLocationAddress =(TextView) findViewById(R.id.Location_Address);
        btnLocation=(Button) findViewById(R.id.location_button);
    }
}