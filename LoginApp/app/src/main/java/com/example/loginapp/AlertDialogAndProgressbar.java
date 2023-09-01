package com.example.loginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class AlertDialogAndProgressbar extends AppCompatActivity {

    Button btnAlert,btnProgressDialog;

    ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_and_progressbar);
        btnAlert = findViewById(R.id.btnAlert);
        btnProgressDialog  = findViewById(R.id.btnProgressDialog);
        p1 =findViewById(R.id.progressBar2);

        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogAndProgressbar.this);
                builder.setMessage("Do you want to exit ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();

                    }
                }));
                builder.setNegativeButton("No",(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                }));

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    p1.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}