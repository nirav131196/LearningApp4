package com.example.loginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements MyInterface{


    Button btnSQLite,btnCalculator,btnCalculator2,btnRegisterAPIVolley,btnRecyclerAndListButton,btnFliterAPI,btnImageAPI,btnJSON,btnSplashscreen,btnButtonDesign;
    Button btnAlertDialog,btndialog;

    AirPlaneModeChangeReceiver receiver = new AirPlaneModeChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initView();


        FirstActivity f1= new FirstActivity();
        String name = f1.name("NIRAV");
        Toast.makeText(getApplicationContext(), "NAME IS : "+name, Toast.LENGTH_SHORT).show();

        btnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,SplashScreen.class);
                startActivity(i);

            }
        });
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,CalculatorActivity.class);
                startActivity(i);

            }
        });
        btnCalculator2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,CalculatorActivity2.class);
                startActivity(i);

            }
        });
        btnRegisterAPIVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FirstActivity.this,RegistrationAPIActivity.class);
                startActivity(i);

            }
        });
        btnRecyclerAndListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,RecyclerAndListView_Activity.class);
                startActivity(i);

            }
        });
        btnFliterAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, FliterAPI_Activity.class);
                startActivity(i);

            }
        });
        btnImageAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, ImageAPI.class);
                startActivity(i);

            }
        });
        btnJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, JSON_DATA.class);
                startActivity(i);

            }
        });
        btnSplashscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, SplashScreen2.class);
                startActivity(i);

            }
        });
        btnButtonDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, Button_Design.class);
                startActivity(i);

            }
        });
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this, AlertDialogAndProgressbar.class);
                startActivity(i);

            }
        });
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(FirstActivity.this);
                dialog.setContentView(R.layout.back_ui);
                dialog.setCancelable(false);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView yes,no;
                yes = dialog.findViewById(R.id.bt_yes);
                no = dialog.findViewById(R.id.bt_no);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    private void initView() {

        btnSQLite = findViewById(R.id.btnSQLite);
        btnCalculator = findViewById(R.id.btnCalculator);
        btnCalculator2 = findViewById(R.id.btnCalculator2);
        btnRegisterAPIVolley  = findViewById(R.id.btnRegisterAPIVolley);
        btnRecyclerAndListButton = findViewById(R.id.btnRecyclerAndList);
        btnFliterAPI = findViewById(R.id.btnFliterAPI);
        btnImageAPI = findViewById(R.id.btnImageAPI);
        btnJSON = findViewById(R.id.btnJSON);
        btnSplashscreen = findViewById(R.id.btnSplashscreen);
        btnButtonDesign = findViewById(R.id.btnButtonDesign);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btndialog = findViewById(R.id.btndialog);

    }


    @Override
    public String name(String name) {

        return name;
    }
}