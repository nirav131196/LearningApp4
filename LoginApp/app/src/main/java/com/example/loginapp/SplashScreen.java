package com.example.loginapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.share.Share;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {


   //ProgressDialog dialog;

    Button btnInsert,btnUpdate,btnDelete;
    EditText edtId,edtName,edtSurname;
    RecyclerView rvSelectData;

    MyDatabaseHandler databaseHandler;

    ArrayList<MyDataModel> list;

    MyAdapterFile adapterFile;

    String id,name,surname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        btnInsert =findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        id = edtId.getText().toString();
        name = edtName.getText().toString();
        surname = edtSurname.getText().toString();

        rvSelectData = findViewById(R.id.rvSelectData);
        rvSelectData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));


        list = new ArrayList<>();
        databaseHandler = new MyDatabaseHandler(this);
        list= databaseHandler.getData();
        adapterFile = new MyAdapterFile(list);
        rvSelectData.setAdapter(adapterFile);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtName.getText().toString();
                surname = edtSurname.getText().toString();

                if(name.isEmpty() || surname.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean data = databaseHandler.insertData(name,surname);

                    if(data)
                    {
                        Toast.makeText(SplashScreen.this, "Inserted", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtSurname.setText("");

                        list= databaseHandler.getData();
                        adapterFile = new MyAdapterFile(list);
                        rvSelectData.setAdapter(adapterFile);
                    }
                    else
                    {
                        Toast.makeText(SplashScreen.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                        edtName.setText("");
                        edtSurname.setText("");
                    }
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edtId.getText().toString();

                if(id.isEmpty())
                {
                    Toast.makeText(SplashScreen.this, "Enter Id to delete", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean delete = databaseHandler.deleteRecord(id);
                    if(delete)
                    {
                        Toast.makeText(SplashScreen.this, "Deleted", Toast.LENGTH_SHORT).show();
                        list= databaseHandler.getData();
                        adapterFile = new MyAdapterFile(list);
                        rvSelectData.setAdapter(adapterFile);
                        edtId.setText("");

                    }
                    else
                    {
                        Toast.makeText(SplashScreen.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                        edtId.setText("");
                    }
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = edtId.getText().toString();
                name = edtName.getText().toString();
                surname = edtSurname.getText().toString();

                if(id.isEmpty() || name.isEmpty() || surname.isEmpty())
                {
                    Toast.makeText(SplashScreen.this, "Field is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean update = databaseHandler.updateRecord(id,name,surname);
                    if(update)
                    {
                        Toast.makeText(SplashScreen.this, "Updated", Toast.LENGTH_SHORT).show();
                        edtId.setText("");edtName.setText("");edtSurname.setText("");
                        list= databaseHandler.getData();
                        adapterFile = new MyAdapterFile(list);
                        rvSelectData.setAdapter(adapterFile);
                    }
                    else
                    {
                        Toast.makeText(SplashScreen.this, "Not Updated", Toast.LENGTH_SHORT).show();
                        edtId.setText("");edtName.setText("");edtSurname.setText("");
                    }
                }
            }
        });
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

     /*   dialog = new ProgressDialog(this);
        dialog.setMessage("Downloading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setIndeterminate(true);
        dialog.setProgress(0);
        dialog.show();*/






     }

    @Override
    public void onBackPressed() {
        finish();
    }
}