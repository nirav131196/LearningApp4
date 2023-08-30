package com.example.loginapp;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import org.w3c.dom.Text;

public class DialogBox extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    @RequiresApi(api = Build.VERSION_CODES.M)

    Button btnDialog, btnAlertDialog, btnCustomAlert, btnSimDeteaction,btnDevice,btnCaptcha,btnSplesh,btnAudio,btnCustomLayout,btnDarkMode,btnPdfDownload,btnFace,btnFingerPrint;
    Button btnPDFCreateDld,btnHoldState,btnRelativeLayout,btnLinearLayout;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;
    TextView t1,t2;
    //For Recaptcha
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    Spinner spDesignation;

    // For Recaptcha
    String sitekey ="6LeOwAYmAAAAADPe3zxUHoVWUkdsfSptCZIIRyXW";
    String secretkey = "";
    RequestQueue requestQueue;
    String TAG = DialogBox.class.getSimpleName();
    String[] items2 = {"Select Color","BLACK","WHITE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_box);

        initView();
        clickbtnDarkMode();
        clickbtnPdfDownload();
        clickbtnFace();
        clickbtnFingerPrint();
        clickPDFCreateAndDownload();
        clickbtnHoldState();
        clickbtnRelaviteLayout();
        clickbtnLinearLayout();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DialogBox.this,AudioRecord.class);
                startActivity(i);
                finish();
            }
        });
        btnCustomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent i = new Intent(DialogBox.this, CustomInputLayout.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception ex)
                {

                }
            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        spDesignation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    case 1:
                        // BLACK

                        break;
                    case 2:
                        // WHITE

                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // For Recaptcha
        checkBox = findViewById(R.id.checkBox);
        googleApiClient  = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(DialogBox.this)
                .build();
        googleApiClient.connect();

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked())
                {
                    Log.e("Error","POINT 1");
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,sitekey)

                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {

                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Log.e("Error","POINT 2");
                                    Status status = recaptchaTokenResult.getStatus();
                                    Log.e("Error","POINT 3");
                                    if((status != null)&& status.isSuccess()){
                                        Log.e("Error","POINT 4");
                                        Toast.makeText(getApplicationContext(), "Successfully Verified", Toast.LENGTH_SHORT).show();
                                    checkBox.setTextColor(Color.GREEN);
                                        Log.e("Error","POINT 5");
                                    }
                                    else
                                    {
                                            Log.e("Error","Status "+status);
                                    }
                                }
                            });
                }
                else
                {
                     checkBox.setTextColor(Color.BLACK);
                }
            }
        });
        builder = new AlertDialog.Builder(this);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(DialogBox.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.progressdialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            }
        });
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Do you want to close ?").setTitle("Do you want to close ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Toast.makeText(getApplicationContext(), "you choose yes action for alertbox", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "you choose no action for alertbox", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Alert Dialog");
                alert.show();
            }
        });
        btnCustomAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(DialogBox.this);
                builder1.setTitle("Name");
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder1.setView(customLayout);
                builder1.setPositiveButton("ENTER", (dialog, which) -> {

                            EditText editText = customLayout.findViewById(R.id.editText);

                            if (editText.getText().toString().equals("")) {
                                Toast.makeText(getApplicationContext(), "Name is not Entered", Toast.LENGTH_LONG).show();
                            } else {
                                sendDialogDatatoActivitty(editText.getText().toString());
                            }
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {

                            Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        });
                AlertDialog dialog = builder1.create();
                dialog.setTitle("Alert");
                dialog.show();
            }
            private void sendDialogDatatoActivitty(String data) {
                Toast.makeText(DialogBox.this, data, Toast.LENGTH_LONG).show();
            }
        });
        btnSimDeteaction.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("Error","Point 1");
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                Log.e("Error","Point 2");
                if (ActivityCompat.checkSelfPermission(DialogBox.this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DialogBox.this, android.Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DialogBox.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("Error","Point 3");
                    Toast.makeText(getApplicationContext(),"Required permission not granted(SMS,READ PHONE NUMBERS,READ PHONE STATE)",Toast.LENGTH_LONG).show();
                   /* ActivityCompat.requestPermissions(DialogBox.this,new String[]{Manifest.permission.READ_SMS},1);
                    ActivityCompat.requestPermissions(DialogBox.this,new String[]{Manifest.permission.READ_PHONE_NUMBERS},2);
                    ActivityCompat.requestPermissions(DialogBox.this,new String[]{Manifest.permission.READ_PHONE_STATE},3);*/
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                     return;
                }
                Log.e("Error","Point 4");
                String phoneNumber = telephonyManager.getSimSerialNumber();
                Log.e("Error","Point 5");
                Log.e("NUMBER","NUMBER "+phoneNumber);
                Log.e("Error","Point 6");
                t1.setText(telephonyManager.getSimSerialNumber());
                Log.e("Error","Point 7");
                t2.setText(telephonyManager.getLine1Number());
                Toast.makeText(getApplicationContext(),"Sim Serial No : "+phoneNumber,Toast.LENGTH_LONG).show();
            }
        });

        btnCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this, TabLayoutActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Product : "+Build.PRODUCT +" BRAND : "+Build.BRAND + " DEVICE : "+Build.DEVICE + " BOARD : " + Build.BOOTLOADER + " DISPLAY : "+Build.DISPLAY + " FINGERPRINT : "+Build.FINGERPRINT + " HARDWARE : "+Build.HARDWARE + " HOST : "+Build.HOST + " ID : "+Build.ID + " MENUFACTAURE "+Build.MANUFACTURER + " MODEL : "+Build.MODEL + " TAGS : " +Build.TAGS + " TYPE : "+Build.TYPE + " UNKNOWN " + Build.UNKNOWN + " USER : "+Build.USER + " TIME : "+Build.TIME;
              Toast.makeText(getApplicationContext(),"DEVICE INFO : "+data,Toast.LENGTH_LONG).show();
            }
        });

        btnSplesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this,  SpleshActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void clickbtnLinearLayout() {

        btnLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this,  Linear_Layout_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void clickbtnRelaviteLayout() {
        btnRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this,  Relative_Layout_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void clickbtnHoldState() {

        btnHoldState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this,HoldStateActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void clickPDFCreateAndDownload() {

        btnPDFCreateDld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this,Create_DownLoad_Pdf_Activity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(DialogBox.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    private void clickbtnFingerPrint() {
        btnFingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DialogBox.this,FingerPrintAuth.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void clickbtnFace() {

        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DialogBox.this,FaceAuthentication.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void clickbtnPdfDownload() {
        btnPdfDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this, PdfDownload.class);
                startActivity(i);
                finish();
            }
        });
    }
    // using inflater for menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_dialog_box,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void clickbtnDarkMode() {
        btnDarkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(DialogBox.this, DarkModeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void initView() {
        t1 = findViewById(R.id.textView2);
        t2 = findViewById(R.id.textView3);
        btnDialog = findViewById(R.id.button2);
        btnAlertDialog = findViewById(R.id.button3);
        btnCustomAlert = findViewById(R.id.button4);
        btnSimDeteaction = findViewById(R.id.button5);
        btnDevice = findViewById(R.id.button6);
        btnCaptcha = findViewById(R.id.button7);
        btnSplesh = findViewById(R.id.button8);
        spDesignation = findViewById(R.id.spinner);
        btnAudio = findViewById(R.id.button9);
        btnCustomLayout = findViewById(R.id.button10);
        btnDarkMode = findViewById(R.id.button11);
        btnPdfDownload = findViewById(R.id.button12);
        btnFace = findViewById(R.id.button13);
        btnFingerPrint =findViewById(R.id.button14);
        btnPDFCreateDld = findViewById(R.id.button15);
        btnHoldState =findViewById(R.id.button16);
        btnRelativeLayout = findViewById(R.id.button17);
        btnLinearLayout = findViewById(R.id.button18);

    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE}, 100);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this,
                        READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_LONG).show();

                    return;
                }
                else
                {
                    TelephonyManager telephonyManager2 = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                 /*   String phoneNumber = telephonyManager2.getLine1Number();*/
                 //   Log.e("NUMBER 2","NUMBER "+phoneNumber);
                    t1.setText(telephonyManager2.getSimSerialNumber());
                    t2.setText(telephonyManager2.getLine1Number());
                 //   Toast.makeText(getApplicationContext(), "Mobile No : " + phoneNumber, Toast.LENGTH_LONG).show();
                    break;
                }

            default:
                throw new IllegalStateException("UnExpected Value " + requestCode);
        }
    }
    @Override
    public void onBackPressed() {
        //  progressDialog.dismiss();
        finish();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

}