package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome_Main extends AppCompatActivity {

    Button btnCamara,btnLocation,btnNotification,btnSqlite,btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_main);

        Log.e("STATE","OnCreate()");

        initView();
        clickCamaraButton();
        clickLocationButton();
        clickNotificationButton();
        clickSQliteButton();
        ClickDialogBox();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    private void ClickDialogBox() {

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Welcome_Main.this,DialogBox.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void clickSQliteButton() {
        btnSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Welcome_Main.this, SQLite_Insert_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void initView() {
        btnCamara = findViewById(R.id.QR_AND_CAMERA);
        btnLocation = findViewById(R.id.LOCATION);
        btnNotification =findViewById(R.id.PUSH_NOTIFICATION);
        btnSqlite =findViewById(R.id.SQLITE_CONNECTIVITY);
        btnDialog =findViewById(R.id.Dialogbox);

    }
    @Override
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(Welcome_Main.this, LoginActivity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_dialog_box,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.search:
                Toast.makeText(getApplicationContext(),"SEARCH",Toast.LENGTH_LONG).show();
            return true;
            case R.id.mail:
                Toast.makeText(getApplicationContext(),"E-MAIL",Toast.LENGTH_LONG).show();
                return true;
            case R.id.upload:
                Toast.makeText(getApplicationContext(),"UPLOAD",Toast.LENGTH_LONG).show();
                return true;
            case R.id.share:
                Toast.makeText(getApplicationContext(),"SHARE",Toast.LENGTH_LONG).show();
                return true;
            case R.id.i1:
                Toast.makeText(getApplicationContext(),"Menu 1",Toast.LENGTH_LONG).show();
                return true;
            case R.id.s11:
                Toast.makeText(getApplicationContext(),"sub-menu-item 1",Toast.LENGTH_LONG).show();
                return true;
            case R.id.s12:
                Toast.makeText(getApplicationContext(),"sub-menu-item 2",Toast.LENGTH_LONG).show();
                return true;
            case R.id.s13:
                Toast.makeText(getApplicationContext(),"sub-menu-item 3",Toast.LENGTH_LONG).show();
                return true;

            case R.id.i2:
                Toast.makeText(getApplicationContext(),"Menu 2",Toast.LENGTH_LONG).show();
                return true;
            case R.id.s21:
                Toast.makeText(getApplicationContext(),"sub-menu-item-4",Toast.LENGTH_LONG).show();
                return true;
            case R.id.s22:
                Toast.makeText(getApplicationContext(),"sub-menu-item-5",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("STATE","OnStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("STATE","OnResume()");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("STATE","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("STATE","onRestoreInstanceState");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("STATE","OnPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("STATE","OnStop()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("STATE","OnRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("STATE","OnDestroy()");
    }
}