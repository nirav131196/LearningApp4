package com.example.loginapp;

import static com.example.loginapp.SQLite_Database_Helper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_Update_Activity extends BaseActivity {

    String updateid;
    EditText edtId,edtName,edtSurname,edtPost,edtDob,edtJoinDate,edtSalary,edtAddress,edtCity;
    Button btnUpdate;
    SQLite_Database_Helper databaseHelper;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_update);

        databaseHelper = new SQLite_Database_Helper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        initView();
        ClickUpdateButton();
        Bundle extra =getIntent().getExtras();
        if(extra != null)
        {
            updateid = extra.getString("myid2");
        }
        edtId.setText(updateid);
        edtId.setEnabled(false);
      /*  SharedPreferences sh = getSharedPreferences("TABLENAME", Context.MODE_PRIVATE);
        String MYTABLE = sh.getString("table","");*/

       /* String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE emp_id="+updateid;
        db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
*/
    }
    private void ClickUpdateButton() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edtId.getText().toString().trim();
                String name = edtName.getText().toString().trim();
                String surname =edtSurname.getText().toString().trim();
                String post =edtPost.getText().toString().trim();
                String DOB =edtDob.getText().toString().trim();
                String JoinDate =edtJoinDate.getText().toString().trim();
                String Salary =edtSalary.getText().toString().trim();
                String Address =edtAddress.getText().toString().trim();
                String City =edtCity.getText().toString().trim();

                if(name.length() > 0 && surname.length() > 0 && post.length() > 0 && DOB.length() > 0 && JoinDate.length() > 0 && Salary.length() > 0 && Address.length() > 0 && City.length() > 0)
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
        edtPost =(EditText) findViewById(R.id.editEmpPost);
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