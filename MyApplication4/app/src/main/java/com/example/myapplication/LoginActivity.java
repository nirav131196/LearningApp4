package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtemail,edtpassword;
    Button btnLogin;
    TextView txtLink;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseHelper = new DatabaseHelper(getApplicationContext());

        edtemail = findViewById(R.id.edtEmail2);
        edtpassword = findViewById(R.id.edtPassword2);

        btnLogin = findViewById(R.id.btnSubmit);
        txtLink = findViewById(R.id.txtRegisterLink);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"One of field is empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean MyDetails = databaseHelper.checkdata(email,password);
                    if(MyDetails)
                    {
                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(i);
                        databaseHelper.getId(email);
                        String id = databaseHelper.myid();

                        SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("MYID",id);
                        myEdit.apply();

                        Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();

                        edtemail.setText("");
                        edtpassword.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Credentials wrong.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,Registration_Activity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}