package com.example.myapplication;

import static com.example.myapplication.DatabaseHelper.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtId,edtName,edtEmail,edtGender,edtDOB;
    Button btnUpdate;

    RecyclerView recycleviewvUpdate;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        databaseHelper  =new DatabaseHelper(getApplicationContext());
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtGender = findViewById(R.id.edtGender);
        edtDOB = findViewById(R.id.edtDOB);

        SharedPreferences sh = getSharedPreferences("MyData", MODE_PRIVATE);
        String myid = sh.getString("MYID", "");

        String SQL = "SELECT ID , NAME , EMAIL , GENDER , DOB FROM " + TABLE_NAME + " WHERE ID = "+myid;
        db =databaseHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);

        if(cursor.moveToFirst())
        {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String gender = cursor.getString(3);
            String dob = cursor.getString(4);

            edtId.setText(id);
            edtName.setText(name);
            edtEmail.setText(email);
            edtGender.setText(gender);
            edtDOB.setText(dob);
        }




        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edtId.getText().toString();
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String gender = edtGender.getText().toString();
                String dob = edtDOB.getText().toString();

                if(id.isEmpty() || name.isEmpty() || email.isEmpty() || gender.isEmpty() || dob.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"One of field is empty",Toast.LENGTH_LONG).show();
                }
                else
                {
                    databaseHelper.updateData(id,name,email,gender,dob);
                    edtId.setText("");
                    edtName.setText("");
                    edtEmail.setText("");
                    edtGender.setText("");
                    edtDOB.setText("");
                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}