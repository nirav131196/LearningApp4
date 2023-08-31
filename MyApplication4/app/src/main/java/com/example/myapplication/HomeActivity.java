package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtid;
    Button btnDelete,btnUpdate;

    RecyclerView recyclerviewHome;
    MyAdapterHome adapterHome;
    ArrayList<MyModelFile> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        databaseHelper = new DatabaseHelper(getApplicationContext());
        edtid = findViewById(R.id.edtidtoDelete);
        btnDelete  =findViewById(R.id.btnDelete);
        btnUpdate  =findViewById(R.id.btnUpdate);

        SharedPreferences sh = getSharedPreferences("MyData", MODE_PRIVATE);
        String myid = sh.getString("MYID", "");


        recyclerviewHome = findViewById(R.id.rvHome);
        recyclerviewHome.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        list = new ArrayList<>();
        list = databaseHelper.getIndividualData(myid);
        adapterHome = new MyAdapterHome(list);
        recyclerviewHome.setAdapter(adapterHome);







        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               boolean delete =  databaseHelper.deleteData(myid);
               if(delete)
               {
                   Toast.makeText(getApplicationContext(),"Account is Deleted",Toast.LENGTH_LONG).show();
                   
                   Intent i = new Intent(HomeActivity.this,LoginActivity.class);
                   startActivity(i);
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
               }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,UpdateActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}