package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity4 extends AppCompatActivity {

    AppCompatButton obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_design);


        sum();

        MainActivity4 c1 = new MainActivity4();

        obj = findViewById(R.id.name);

        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(i);

                Toast.makeText(getApplicationContext(),"Welcome Palash",Toast.LENGTH_SHORT).show();
            }
        });

        try{

        }
        catch(Exception ex){

        }

    }


    public  void sum(){

        int b = 20 ;
    }
}