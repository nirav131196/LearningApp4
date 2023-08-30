package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class State_List_Activity extends AppCompatActivity {

    RecyclerView rvState;
    ArrayList<String> MyStateList = new ArrayList<>();
    int itemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_list);

        itemPosition = getIntent().getIntExtra("POSITION",1);

        if(itemPosition == 0)
        {
            MyStateList.add("GUJARAT");
            MyStateList.add("MAHARASHATRA");
            MyStateList.add("PUNJAB");
        }
        else if(itemPosition == 1)
        {
            MyStateList.add("A 1");
            MyStateList.add("A 2");
            MyStateList.add("A 3");
        }
        else if(itemPosition == 2)
        {
            MyStateList.add("C 1");
            MyStateList.add("C 2");
            MyStateList.add("C 3");
        }
        else if(itemPosition == 3)
        {
            MyStateList.add("USA 1");
            MyStateList.add("USA 2");
            MyStateList.add("USA 3");
        }

        rvState  = findViewById(R.id.rvState);
        rvState.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        State_Adapter_File stateAdapter = new State_Adapter_File(MyStateList);
        rvState.setAdapter(stateAdapter);
        rvState.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), rvState, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent i = new Intent(State_List_Activity.this, StateList_with_buttons_Activity.class);
                i.putExtra("STATEPOSITION",position);
                i.putExtra("COUNTRYPOSITION",itemPosition);
                startActivity(i);
            }
            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
}