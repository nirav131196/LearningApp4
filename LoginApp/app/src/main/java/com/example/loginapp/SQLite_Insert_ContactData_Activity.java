package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_Insert_ContactData_Activity extends BaseActivity {

    EditText edtId,edtmobileno,edtemailid;
    Button btnInsert;
    SQLite_Database_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert_contact_data);

        databaseHelper = new SQLite_Database_Helper(this);

        edtId =findViewById(R.id.editEmpId2);
        edtmobileno =findViewById(R.id.editEmpMobileNo);
        edtemailid =findViewById(R.id.editEmpEmailid);
        btnInsert =findViewById(R.id.InsertContactButton2);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtId.getText().toString().trim().length() > 0 && edtmobileno.getText().toString().trim().length() > 0 && edtemailid.getText().toString().trim().length() > 0)
                {
                    boolean isinserted = databaseHelper.insertContactData(edtId.getText().toString(),edtmobileno.getText().toString(),edtemailid.getText().toString());
                    if(isinserted == true)
                    {
                        showToast("Contact Details Added");
                        edtId.setText("");
                        edtmobileno.setText("");
                        edtemailid.setText("");
                    }
                    else
                    {
                        showToast("Contact Details Not Added");
                    }
                }
                else
                {
                    showToast("Enter All Details");
                }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Insert_ContactData_Activity.this, SQLite_Insert_Activity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
}