package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Linear_Layout_Activity extends AppCompatActivity {

    /*TextView txtName;
    SwipeRefreshLayout swipeRefreshLayout;*/

    // ListView list;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        gridView = findViewById(R.id.GridView);

        /*txtName = findViewById(R.id.txtName);
        swipeRefreshLayout =findViewById(R.id.RefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                txtName.setText("Refreshed");
                swipeRefreshLayout.setRefreshing(false);
                // by true it will continue refreshing.
            }
        });*/

        // for web view
       /* WebView webView = findViewById(R.id.web);
        webView.loadUrl("https://www.google.com/");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());*/

        // for grid view and list view
        ArrayList<String> group = new ArrayList<>();
        group.add("TATA");
        group.add("BIRLA");
        group.add("AMBANI");
        group.add("ADANI");
        group.add("GIRNARI");

        // for list view
       /* String items[] = {"TATA","BIRLA","AMBANI","ADANI","GIRNARI"};
        list=findViewById(R.id.list);
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(arr);*/

        // for grid view
        // learn adapter code with grid view  of GeeksForGeeks
        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,group);
        gridView.setAdapter(arr);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(Linear_Layout_Activity.this,DialogBox.class);
        startActivity(i);
    }
}