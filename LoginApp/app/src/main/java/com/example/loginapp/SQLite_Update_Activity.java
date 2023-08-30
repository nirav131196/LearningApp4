package com.example.loginapp;

import static com.example.loginapp.SQLite_Database_Helper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class SQLite_Update_Activity extends BaseActivity {

    String updateid;
    EditText edtId,edtName,edtSurname,edtDob,edtJoinDate,edtSalary,edtAddress,edtCity;
    Spinner edtPost;
    Button btnUpdate;
    SQLite_Database_Helper databaseHelper;
    SQLiteDatabase db;

    // For calender
    private Calendar calander;
    private int year,month,day;
    // For Spinner
    String[] items2 = {"Select Designation","IOS","Angular","REACT NATIVE","ANDROID","FLUTTER","PYTHON","C#","JAVA","UI DESIGNER","PHP","LARAVEL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_update);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        databaseHelper = new SQLite_Database_Helper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        calander =Calendar.getInstance();
        year = calander.get(Calendar.YEAR);
        month = calander.get(Calendar.MONTH);
        day = calander.get(Calendar.DAY_OF_MONTH);

        initView();
        ClickUpdateButton();
        ClickDobEdtText();
        ClickJoinEdtText();
        Bundle extra =getIntent().getExtras();
        if(extra != null)
        {
            updateid = extra.getString("myid2");
        }
        edtId.setText(updateid);
        edtId.setEnabled(false);

        // For Spinner
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, items2);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edtPost.setAdapter(ad);

      /*  SharedPreferences sh = getSharedPreferences("TABLENAME", Context.MODE_PRIVATE);
        String MYTABLE = sh.getString("table","");*/

        String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE emp_id="+updateid;
        db = databaseHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);

        if(cursor.moveToFirst())
        {
            do {
                String id =cursor.getString(0);
                String name = cursor.getString(1);
                String surname = cursor.getString(2);
                String post = cursor.getString(3);
                String DOB= cursor.getString(4);
                String Joindate =cursor.getString(5);
                String salary =cursor.getString(6);
                String address = cursor.getString(7);
                String city =cursor.getString(8);

                edtId.setText(id);
                edtName.setText(name);
                edtSurname.setText(surname);
                edtPost.getSelectedItem();
                edtDob.setText(DOB);
                edtJoinDate.setText(Joindate);
                edtSalary.setText(salary);
                edtAddress.setText(address);
                edtCity.setText(city);

            } while(cursor.moveToNext());
        }
    }
    private void ClickJoinEdtText() {

        edtJoinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(990);
            }
        });

    }
    private void ClickDobEdtText() {

        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
    }
    protected Dialog onCreateDialog(int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this,myDatelistener,year,month,day);
        }
        else if(id==990)
        {
            return new DatePickerDialog(this,myDatelistener2,year,month,day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDatelistener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year,month+1,dayOfMonth);
        }
    };
    private DatePickerDialog.OnDateSetListener myDatelistener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            showDate2(year,month+1,dayOfMonth);
        }
    };
    private void showDate(int year, int month,int day)
    {
        edtDob.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
    private void showDate2(int year, int month,int day)
    {
        edtJoinDate.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
    private void ClickUpdateButton() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String surname =edtSurname.getText().toString().trim();
                String post =edtPost.getSelectedItem().toString().trim();
                String DOB =edtDob.getText().toString().trim();
                String JoinDate =edtJoinDate.getText().toString().trim();
                String Salary =edtSalary.getText().toString().trim();
                String Address =edtAddress.getText().toString().trim();
                String City =edtCity.getText().toString().trim();

                if(name.length() > 0 && surname.length() > 0 && !post.equals("Select Designation") && DOB.length() > 0 && JoinDate.length() > 0 && Salary.length() > 0 && Address.length() > 0 && City.length() > 0)
                {
                    try {
                            Log.e("DATA : ","DATA "+name+surname+post+DOB+JoinDate+Salary+Address+City);
                            databaseHelper.UpdateRecord(id,name,surname,post,DOB,JoinDate,Salary,Address,City);
                            Intent i = new Intent(SQLite_Update_Activity.this,SQLite_Select_Record.class);
                            startActivity(i);
                            showToast("Employee Details Updated");
                            finish();
                   /*     boolean isUpdated = databaseHelper.UpdateRecord(id,name,surname,post,DOB,JoinDate,Salary,Address,City);*/
                       /* if(isUpdated == true)
                        {
                        }
                        else
                        {
                            showToast("Employee Details Not Added");
                        }*/
                    }
                    catch (Exception ex)
                    {
                        showToast("Exception is : "+ex);
                    }
                }
                else
                {
                    showToast("Enter All Details");
                }
            }
        });
    }
    private void initView() {
        edtId =(EditText) findViewById(R.id.edtUpdateID);
        edtName =(EditText) findViewById(R.id.edtEmpName);
        edtSurname =(EditText) findViewById(R.id.editEmpSurname);
        edtPost =(Spinner) findViewById(R.id.editEmpPost);
        edtDob =(EditText) findViewById(R.id.editEmpDOB);
        edtJoinDate =(EditText) findViewById(R.id.editEmpJoiningDate);
        edtSalary =(EditText) findViewById(R.id.editEmpSalary);
        edtAddress =(EditText) findViewById(R.id.editEmpAddress);
        edtCity =(EditText) findViewById(R.id.editEmpCity);

        btnUpdate=(Button) findViewById(R.id.UpdateButton);

    }

    public boolean onSupportNavigateUp() {

        Intent i =new Intent(SQLite_Update_Activity.this, SQLite_Select_Record.class);
        startActivity(i);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Intent i =new Intent(SQLite_Update_Activity.this, SQLite_Select_Record.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}