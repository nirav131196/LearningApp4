package com.example.exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView name,dob,emailId,mobileNo,cityName;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#1B871F'>Student Info</font>"));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        setContentView(R.layout.activity_home);


        name = (TextView) findViewById(R.id.studentName);
        dob = (TextView) findViewById(R.id.studentDOB);
        emailId = (TextView) findViewById(R.id.studentEmailId);
        mobileNo = (TextView) findViewById(R.id.studentMobileNo);
        cityName = (TextView) findViewById(R.id.studentCityName);

        databaseHelper = new DatabaseHelper(this);

        int studentId;
        Bundle extras = getIntent().getExtras();
        studentId = extras.getInt("STUDENTID");
        Log.e("STUDENT ID","ID is "+studentId);
        String studentName = databaseHelper.getName(String.valueOf(studentId));
        String dateOfBirth = databaseHelper.getDateOfBirth(String.valueOf(studentId));
        String email = databaseHelper.getEmail(String.valueOf(studentId));
        String mobileNo2 = databaseHelper.getMobileNo(String.valueOf(studentId));
        String city = databaseHelper.getCity(String.valueOf(studentId));


        name.setText(studentName);
        dob.setText(dateOfBirth);
        emailId.setText(email);
        mobileNo.setText(mobileNo2);
        cityName.setText(city);

    }
    @Override
    public boolean onSupportNavigateUp() {

        exitDialog("Exit","Are you sure you want to Exit ?",0);
        return super.onSupportNavigateUp();

    }

    private void exitDialog(String title,String message,int id) {

        new AlertDialog.Builder(this).setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(id == 1){
                         //   e1.clear();
                        }
                        finishAffinity();

                    }
                }).setNegativeButton("No",null).show();

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.logout){
            exitDialog("Logout","Are you sure you want to Log out ?",1);

        }

        return super.onOptionsItemSelected(item);

    }*/
    @Override
    public void onBackPressed() {

        exitDialog("Exit","Are you sure you want to Exit ?",0);


    }
}