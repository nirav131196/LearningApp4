package com.example.exam;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper database;
    private static final int PERMISSION_REQUEST_CODE = 200;

    private ActivityLoginBinding binding;

 /*   SharedPreferences sh;
    SharedPreferences.Editor e3;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#1B871F'>Login</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        setContentView(R.layout.activity_login);

        try
        {

            if(checkPermission())
            {
                //   Toast.makeText(getApplicationContext(),"Permission already granted",Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
        }
        initobjects();
        TextView t1 = (TextView)findViewById(R.id.link_message);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("POINT","POINT 1");
                Intent i =new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        EditText e1 = (EditText)findViewById(R.id.checkemail);
        EditText e2 = (EditText)findViewById(R.id.checkpassword);

        Button b1 = (Button)findViewById(R.id.login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("POINT","POINT 2");
                try
                {
                    if(e1.getText().toString().trim().length() > 0 && e2.getText().toString().trim().length() > 0)
                    {
                        if(database.checkdata(e1.getText().toString(),e2.getText().toString()))
                        {

                         /*   sh = getSharedPreferences("DATA", MODE_PRIVATE);
                            e3 = sh.edit();
                            e3.putString("STUDENT", "LOGIN");
                            e3.apply();
*/
                            int studentId = database.getId(e1.getText().toString(),e2.getText().toString());

                            Log.e("STUDENT ID","ID is "+studentId);
                            Intent i =new Intent(LoginActivity.this,HomeActivity.class);
                            i.putExtra("STUDENTID",studentId);
                            startActivity(i);
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                            e1.setText("");e2.setText("");
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Email Id or Password is wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Both Details", Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Something went wrong !", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void initobjects()
    {
        database=new DatabaseHelper(this);
    }
    private boolean checkPermission()
    {
        try
        {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
            //  int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
            int result3 = ContextCompat.checkSelfPermission(getApplicationContext(),INTERNET);

            return result == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong !!!", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    private void requestPermission()
    {
        try
        {
            ActivityCompat.requestPermissions(this,new String[]{WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
            ActivityCompat.requestPermissions(this,new String[]{INTERNET},PERMISSION_REQUEST_CODE);
        }
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Something went wrong !!!!", Toast.LENGTH_LONG).show();
        }
    }
}