package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerAndListView_Activity extends AppCompatActivity {

    RecyclerView rvLst;

    ArrayList<String> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_and_list_view);


        list.add("India");
        list.add("Australiya");
        list.add("China");
        list.add("USA");


        rvLst = findViewById(R.id.rvCountry);
        rvLst.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        AdapterForRecyclerView adapter = new AdapterForRecyclerView(list);
        rvLst.setAdapter(adapter);
        rvLst.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvLst, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(RecyclerAndListView_Activity.this,State_List_Activity.class);
                i.putExtra("POSITION",position);
                startActivity(i);

            }
            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}