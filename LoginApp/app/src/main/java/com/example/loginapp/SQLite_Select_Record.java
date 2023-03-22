package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SQLite_Select_Record extends AppCompatActivity {

    RecyclerView recyclerView;
     SQLite_Database_Helper databaseHelper;
    List<SQLiteEmployeeData> employeelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_select_record);

        recyclerView =(RecyclerView)findViewById(R.id.ShowEmpData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        employeelist =new ArrayList<>();
        databaseHelper =new SQLite_Database_Helper(this);
        employeelist=databaseHelper.getEmployeeData();
        SQLite_RecyclerAdapter adapter = new SQLite_RecyclerAdapter(employeelist);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Select_Record.this, SQLite_Insert_Activity.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(SQLite_Select_Record.this, SQLite_Insert_Activity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}