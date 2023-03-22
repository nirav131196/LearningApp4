package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_Insert_Activity extends BaseActivity {

    EditText edtName,edtSurname,edtDesignation,edtDOB,edtJoiningDate,edtSalary,edtAddress,edtCity;
    Button btnInsert;
    SQLite_Database_Helper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);

        databaseHelper = new SQLite_Database_Helper(this);

        initView();
        ClickInsertButton();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void ClickInsertButton() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString().trim();
                String surname =edtSurname.getText().toString().trim();
                String post =edtDesignation.getText().toString().trim();
                String DOB =edtDOB.getText().toString().trim();
                String JoinDate =edtJoiningDate.getText().toString().trim();
                String Salary =edtSalary.getText().toString().trim();
                String Address =edtAddress.getText().toString().trim();
                String City =edtCity.getText().toString().trim();

                if(name.length() > 0 && surname.length() > 0 && post.length() > 0 && DOB.length() > 0 && JoinDate.length() > 0 && Salary.length() > 0 && Address.length() > 0 && City.length() > 0)
                {
                    try {
                        Log.e("DATA : ","DATA "+ name+surname+post+DOB+JoinDate+Salary+Address+City);
                        boolean isInserted = databaseHelper.insertData(name,surname,post,DOB,JoinDate,Salary,Address,City);

                        if(isInserted == true)
                        {
                            showToast("Employee Details Added");
                            Intent i = new Intent(SQLite_Insert_Activity.this,SQLite_Select_Record.class);
                            startActivity(i);
                            finish();
                        }
                        else
                        {
                            showToast("Employee Details Not Added");
                        }
                    }
                    catch (Exception ex)
                    {
                        showToast("Exception is : "+ex);
                    }
                }
                else
                {
                    showToast("Enter All Details");
                }
            }
        });
    }
    private void initView() {
        edtName = findViewById(R.id.editEmpName);
        edtSurname = findViewById(R.id.editEmpSurname);
        edtDesignation = findViewById(R.id.editEmpPost);
        edtDOB = findViewById(R.id.editEmpDOB);
        edtJoiningDate = findViewById(R.id.editEmpJoiningDate);
        edtSalary = findViewById(R.id.editEmpSalary);
        edtAddress = findViewById(R.id.editEmpAddress);
        edtCity = findViewById(R.id.editEmpCity);

        btnInsert=findViewById(R.id.InsertButton);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Insert_Activity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        Intent i =new Intent(SQLite_Insert_Activity.this, Welcome_Main.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}