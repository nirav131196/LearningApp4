package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2;
    final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // if permissions are not provided we are requesting for permissions.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);

        }

        aSwitch = findViewById(R.id.switch1);

        materialDesignFAM = findViewById(R.id.MyFloatingActionMenu);
        materialDesignFAM.setVisibility(View.GONE);

        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  =new Intent(MainActivity.this, PhoneInfo.class);
                startActivity(i);

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  =new Intent(MainActivity.this, SimCardInfo.class);
                startActivity(i);

            }
        });


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    startService(new Intent(MainActivity.this,MyService.class));
                    materialDesignFAM.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"Switch is on",Toast.LENGTH_LONG).show();
                }
                else
                {
                    stopService(new Intent(MainActivity.this, MyService.class));
                    materialDesignFAM.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(),"Switch is off",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}