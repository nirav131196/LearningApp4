package com.example.loginapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class QRScanner_Activity extends BaseActivity {

    TextView txtLatitude, txtLongitude, txtLocationAddress;
    Button btnLocation, btnGoogleMap;
    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String latitude, longitude;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // FOR LAT LONG , CHECKING PERMISSION OF LOCATION
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        // FOR GOOGLE MAP , Assign variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //Initialize fused location
        client = LocationServices.getFusedLocationProviderClient(this);

        initView();
        ClickEventLocationButton();
        ClickEventGoogleMapButton();
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(QRScanner_Activity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private void initView()
    {
        txtLatitude =(TextView) findViewById(R.id.Location_Latitude);
        txtLongitude =(TextView) findViewById(R.id.Location_Longitude);
        txtLocationAddress =(TextView) findViewById(R.id.Location_Address);
        btnLocation=(Button) findViewById(R.id.location_button);
        btnGoogleMap=(Button) findViewById(R.id.google_map_button);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i =new Intent(QRScanner_Activity.this,Welcome_Main.class);
        startActivity(i);
        finish();
    }
    private void getCurrentlocation() {

        //Initializing task location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {

                            //Initializing lat lng
                            LatLng latLng =new LatLng(location.getLatitude(),location.getLongitude());

                            //Creating marker options
                            MarkerOptions options = new MarkerOptions().position(latLng).title("NIRAV GIRNARI");

                            //zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                            //add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 44)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentlocation();
            }
            else
            {
                showToast("Permission Denied");
            }
        }
    }

    private void ClickEventGoogleMapButton() {

        btnGoogleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    //checking permission
                    if (ActivityCompat.checkSelfPermission(QRScanner_Activity.this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        getCurrentlocation();
                    }
                    else
                    {
                        ActivityCompat.requestPermissions(QRScanner_Activity.this,new String[]{ACCESS_FINE_LOCATION},44);
                    }
                }
                catch (Exception ex)
                {
                    showToast("Exception : "+ex);
                }
            }
        });
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
}