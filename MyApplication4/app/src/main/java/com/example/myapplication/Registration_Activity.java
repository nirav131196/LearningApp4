package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration_Activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtName,edtEmail,edtPasword,edtGender,edtDOB,edtConfirmpass;
    Button btnsubmit;
    RecyclerView recyclerviewAdd;
    MyAdapter adapter;
    ArrayList<MyModelFile> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        recyclerviewAdd = findViewById(R.id.rvAdd);

        recyclerviewAdd.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        list = databaseHelper.getAllData();
        adapter = new MyAdapter(list);
        recyclerviewAdd.setAdapter(adapter);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPasword = findViewById(R.id.edtPasword);
        edtGender = findViewById(R.id.edtGender);
        edtDOB = findViewById(R.id.edtDOB);

        btnsubmit = findViewById(R.id.btnSubmit);
        edtConfirmpass = findViewById(R.id.edtConfirmPasword);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPasword.getText().toString();
                String gender = edtGender.getText().toString();
                String dob = edtDOB.getText().toString();

                if(name.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty() || dob.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"One of field is empty",Toast.LENGTH_LONG).show();
                    Log.e("ACTION","FIELD IS EMPTY");
                }
                else
                {
                    boolean data = databaseHelper.insertData(name,email,password,gender,dob);
                    if(data)
                    {
                        Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
                        Log.e("ACTION","Registered");
                        edtName.setText("");
                        edtEmail.setText("");
                        edtPasword.setText("");
                        edtGender.setText("");
                        edtDOB.setText("");
                        edtConfirmpass.setText("");

                        list = new ArrayList<>();
                        list = databaseHelper.getAllData();
                        adapter = new MyAdapter(list);
                        recyclerviewAdd.setAdapter(adapter);
                        Intent i  =new Intent(Registration_Activity.this,LoginActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_LONG).show();
                        Log.e("ACTION","Not Registered");
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}