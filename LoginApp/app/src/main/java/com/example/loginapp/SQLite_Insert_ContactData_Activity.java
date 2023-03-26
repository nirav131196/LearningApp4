package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SQLite_Insert_ContactData_Activity extends BaseActivity {

    EditText edtId,edtName,edtName2;
    Button btnInsert,btnInsert2;
    SQLite_Database_Helper databaseHelper;

    RecyclerView recyclerView;
    List<SQLiteEmpData> list;
    SQLite_Adapter_ForeignKey_Table1 adapter;
    String id,name,name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_insert_contact_data);

        databaseHelper = new SQLite_Database_Helper(this);

        edtId =findViewById(R.id.editEmpId2);
        edtName =findViewById(R.id.editEmpName);
        edtName2 =findViewById(R.id.editEmpName2);
        btnInsert =findViewById(R.id.InsertContactButton2);
        btnInsert2 =findViewById(R.id.InsertContactButton3);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtId.getText().toString().trim().length() > 0 && edtName.getText().toString().trim().length() > 0)
                {
                    try
                    {
                        id=edtId.getText().toString();
                        name=edtName.getText().toString();
                        boolean isinserted = databaseHelper.insertEmp(name,id);

                        if(isinserted == true)
                        {

                            Log.e("DATA","DATA 1 "+id +" "+name);
                            showToast("Contact Details Added");
                            edtId.setText("");
                            edtName.setText("");
                        }
                        else
                        {
                            showToast("Contact Details Not Added");
                        }
                    }
                    catch(Exception ex)
                    {
                        showToast("Exception is "+ex);
                    }
                }
                else
                {
                    showToast("Enter All Details");
                }
            }
        });
        btnInsert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(edtName2.getText().toString().trim().length() > 0)
                    {
                        try
                        {
                            boolean isinserted = databaseHelper.insertEmpGroup(edtName2.getText().toString());

                            name2 =edtName2.getText().toString();
                            if(isinserted == true)
                            {
                                Log.e("DATA","DATA 2 "+name2);
                                showToast("Contact Details Added");
                                edtName2.setText("");
                            }
                            else
                            {
                                showToast("Contact Details Not Added");
                            }
                        }
                        catch(Exception ex)
                        {
                            showToast("Exception is "+ex);
                        }
                    }
                    else
                    {
                        showToast("Enter Name");
                    }
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView =(RecyclerView)findViewById(R.id.rvdetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        list =new ArrayList<>();
        databaseHelper =new SQLite_Database_Helper(this);
        list=databaseHelper.getData1();
        adapter = new SQLite_Adapter_ForeignKey_Table1(list);
        recyclerView.setAdapter(adapter);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Insert_ContactData_Activity.this, SQLite_Insert_Activity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
}