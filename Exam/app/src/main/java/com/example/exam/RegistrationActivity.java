package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.exam.databinding.ActivityRegistrationBinding;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity  {

    private ActivityRegistrationBinding binding;
    DatabaseHelper sqLiteHelper;

    private Calendar calander;
    private int year, month, day;

    TextView d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#1B871F'>Registration</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_registration);

        sqLiteHelper = new DatabaseHelper(this);

        d1 = (TextView)findViewById(R.id.dateofbirth);
        calander = Calendar.getInstance();
        year = calander.get(Calendar.YEAR);
        month = calander.get(Calendar.MONTH);
        day = calander.get(Calendar.DAY_OF_MONTH);


        TextView t2 = (TextView) findViewById(R.id.link_message_of_login);

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        Button b2 = (Button)findViewById(R.id.register);
        EditText name = (EditText)findViewById(R.id.name);

        EditText email = (EditText)findViewById(R.id.email);
        EditText password = (EditText)findViewById(R.id.password);
        EditText confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        EditText mobileno = (EditText)findViewById(R.id.mobileno);
        EditText degree = (EditText)findViewById(R.id.degree);
        EditText college = (EditText)findViewById(R.id.college);
        EditText city = (EditText)findViewById(R.id.city);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (name.getText().toString().trim().length() > 0 && d1.getText().toString().trim().length() > 0 && email.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0 && confirmPassword.getText().toString().trim().length() > 0 && mobileno.getText().toString().trim().length() > 0 && college.getText().toString().trim().length() > 0 &&  degree.getText().toString().trim().length() > 0 && city.getText().toString().trim().length() > 0) {

                        if(password.getText().toString().equals(confirmPassword.getText().toString())){
                            try {
                                boolean isInserted = sqLiteHelper.insertData(name.getText().toString(),d1.getText().toString(),email.getText().toString(), password.getText().toString(), mobileno.getText().toString(), college.getText().toString(),degree.getText().toString() , city.getText().toString());

                                if (isInserted == true) {
                                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    name.setText("");
                                    d1.setText("Enter a Date of Birth");
                                    email.setText("");
                                    password.setText("");
                                    confirmPassword.setText("");
                                    mobileno.setText("");
                                    college.setText("");
                                    degree.setText("");
                                    city.setText("");
                                    Toast.makeText(getApplicationContext(), "Registration Successfully Done.", Toast.LENGTH_LONG).show();
                                    finish();
                                } else
                                {
                                    Toast.makeText(getApplicationContext(), "Not Registered", Toast.LENGTH_LONG).show();
                                    //  Toast.makeText(getApplicationContext(), "Android OS Latest version is not supported, Android OS 8 is supported.", Toast.LENGTH_LONG).show();
                                }


                            } catch (Exception ex) {
                                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Passwords do not matched. Please Enter same passwords", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Please Enter All Details", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        });


        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  showDate(year,month+1,day);
                showDialog(999);
                //  Toast.makeText(getApplicationContext(),"ca",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    protected Dialog onCreateDialog(int id){
        if(id == 999)
        {
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            showDate(year,month+1,dayOfMonth);

        }
    };
    private void showDate(int year,int month,int day)
    {
        d1.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
}