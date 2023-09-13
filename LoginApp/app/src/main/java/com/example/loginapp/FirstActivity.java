package com.example.loginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements MyInterface, View.OnClickListener {


    Button btnSQLite,btnCalculator,btnCalculator2,btnRegisterAPIVolley,btnRecyclerAndListButton,btnFliterAPI,btnImageAPI,btnJSON,btnSplashscreen,btnButtonDesign;
    Button btnAlertDialog,btndialog,btnService,btnPushnotification,btnImageview,btnSpinner,btnNavigation;

    AirPlaneModeChangeReceiver receiver = new AirPlaneModeChangeReceiver();

    public String CHANNEL_ID = "PORBANDAR";

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


    // code of broadcast receiver
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
        btnService = findViewById(R.id.btnService);
        btnImageview =findViewById(R.id.btnImageview);
        btnSpinner = findViewById(R.id.btnSpinner);
        btnNavigation = findViewById(R.id.btnNavigation);
        btnNavigation.setOnClickListener(this);
        btnSpinner.setOnClickListener(this);
        btnImageview.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnPushnotification = findViewById(R.id.btnPushNotification);
        btnPushnotification.setOnClickListener(this);

    }
    @Override
    public String name(String name) {

        return name;
    }

    @Override
    public void onClick(View v) {
        if(v  == btnService)
        {
            Intent i = new Intent(FirstActivity.this,ServiceActivity.class);
            startActivity(i);
        }
        else if(v == btnPushnotification)
        {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.download_logo);

            Uri alermSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                    .setSmallIcon(R.drawable.favourite_icon)
                    .setContentTitle("HELLO")
                    .setContentText("I AM NIRAV GIRNARI")
                    .setSound(alermSound)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap)
                            .bigLargeIcon(bitmap))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            Vibrator phone  = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            phone.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));


            NotificationManagerCompat notif = NotificationManagerCompat.from(getApplicationContext());
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                Toast.makeText(getApplicationContext(), "Not Permission Granted", Toast.LENGTH_SHORT).show();
                return;
            }
            notif.notify(0,builder.build());
        }
        else if(v == btnImageview)
        {
            Intent i = new Intent(FirstActivity.this,ImageActivity.class);
            startActivity(i);
        }
        else if(v == btnSpinner)
        {
            Intent i = new Intent(FirstActivity.this,SpinnerActivity.class);
            startActivity(i);
        }
        else if(v == btnNavigation)
        {
            Intent i = new Intent(FirstActivity.this,NavigationActivity.class);
            startActivity(i);
        }
    }
}