package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLite_Select_All_Records extends AppCompatActivity {

    RecyclerView recyclerView;
    SQLite_Database_Helper databaseHelper;
    List<SQLiteEmployeeData_All> employeefulllist;
    SQLite_RecyclerAdapter_Full_Data adapter;
    String newstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_select_all_records);

        Bundle extra = getIntent().getExtras();
        if(extra != null)
        {
            newstring = extra.getString("myid");
        }
        Log.e("ID IS","ID "+newstring);
        recyclerView =(RecyclerView)findViewById(R.id.ShowEmpAllData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        employeefulllist =new ArrayList<>();
        databaseHelper =new SQLite_Database_Helper(this);

        employeefulllist=databaseHelper.getEmployeeALlData(newstring);
        adapter = new SQLite_RecyclerAdapter_Full_Data(employeefulllist);
        recyclerView.setAdapter(adapter);

      androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Select_All_Records.this, SQLite_Select_Record.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }
}