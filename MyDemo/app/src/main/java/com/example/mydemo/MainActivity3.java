package com.example.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {



    AppCompatButton b1;  // example of button click event

    String s1; // string variable
    int number;
    boolean checked;
    int a = 10;

    final int number2 = 10;
    float f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_constraint_layout_ex);


         MainActivity3 m1 = new MainActivity3();  // class example
         s1 = "NIRAV";
         m1.sum();  // function call


        Log.e("ididid","IDIDI "+m1.s1);

        Toast.makeText(getApplicationContext(),"HELLO",Toast.LENGTH_LONG).show();  // toast example


        b1 = findViewById(R.id.button1);  //  button click event

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity3.this,MainActivity2.class);  // intent example
                startActivity(i);
            }


        });

        a = 20;
        try{

        }
        catch(Exception ex){

        }



    }

    public void sum(){

        a = 20;
    }
}