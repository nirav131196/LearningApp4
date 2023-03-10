package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DrinkOrderMenu_Activity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_order_menu);

        List<Product_Data> list = new ArrayList<>();
        list =getData();
        recyclerView =(RecyclerView) findViewById(R.id.product_data);
        adapter  =new RecyclerAdapter(list,getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DrinkOrderMenu_Activity.this));
    }
    private List<Product_Data> getData()
    {
        List<Product_Data> list = new ArrayList<>();
        list.add(new Product_Data("9191","7262","838383","CLOTHES"));

        return list;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(DrinkOrderMenu_Activity.this, Dashboard_Activity_p9.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}