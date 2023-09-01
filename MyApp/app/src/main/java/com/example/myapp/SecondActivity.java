package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    Dialog dialog_backPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        dialog_backPress = new Dialog(SecondActivity.this);
        dialog_backPress.setContentView(R.layout.back_ui);
        dialog_backPress.setCancelable(false);
        dialog_backPress.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog_backPress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView yes, no;

        yes = dialog_backPress.findViewById(R.id.bt_yes);

        no = dialog_backPress.findViewById(R.id.bt_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i  = new Intent(SecondActivity.this,HomeActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog_backPress.dismiss();
                Toast.makeText(getApplicationContext(), "You need to allow for selecting images", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        dialog_backPress.show();

    }
    @Override
    public void onBackPressed() {


        dialog_backPress.dismiss();

        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert !");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}