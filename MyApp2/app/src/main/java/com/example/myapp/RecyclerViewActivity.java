package com.example.myapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycler_view);

        listData[] list = new listData[]{

                new listData("1","NIRAV","GIRNARI","9191","A","ABC"),
                new listData("2","PALASH","SAHARE","7878","B","BCD"),
                new listData("3","SAGAR","JUNGI","7878454","C","DED"),
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.dateRecyclerView);
        MyListAdapter adapter = new MyListAdapter(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }



}