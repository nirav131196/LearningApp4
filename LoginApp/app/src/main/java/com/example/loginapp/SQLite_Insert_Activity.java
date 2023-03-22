package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_Insert_Activity extends BaseActivity {

    EditText edtName,edtSurname,edtDesignation,edtDOB,edtJoiningDate,edtSalary,edtAddress,edtCity;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert);

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

                int name = edtName.getText().toString().trim().length();
                int surname =edtSurname.getText().toString().trim().length();
                int post =edtDesignation.getText().toString().trim().length();
                int DOB =edtDOB.getText().toString().trim().length();
                int JoinDate =edtJoiningDate.getText().toString().trim().length();
                int Salary =edtSalary.getText().toString().trim().length();
                int Address =edtAddress.getText().toString().trim().length();
                int City =edtCity.getText().toString().trim().length();

                if(name > 0 && surname > 0 && post > 0 && DOB > 0 && JoinDate > 0 && Salary > 0 && Address > 0 && City > 0)
                {
                    showToast("Employee Details Added");
                    Intent i = new Intent(SQLite_Insert_Activity.this,SQLite_Select_Record.class);
                    startActivity(i);
                    finish();
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