package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLite_Database_Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EMPLOYEE.db";
    public static final String TABLE_NAME = "EMPLOYEE_DETAILS";
    public static final String columnId = "emp_id";
    public static final String columnName = "emp_name";
    public static final String columnSurname = "emp_surname";
    public static final String columnDesignation = "emp_designation";
    public static final String columnDOB  = "emp_dob";
    public static final String columnJoiningDate = "emp_JD";
    public static final String columnSalary = "emp_salary";
    public static final String columnAddress = "emp_address";
    public static final String columnCity = "emp_city";

    public SQLite_Database_Helper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"(" + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + columnName + " TEXT, " + columnSurname + " TEXT, " + columnDesignation + " TEXT, " + columnDOB + " TEXT, " + columnJoiningDate + " TEXT, " + columnSalary + " TEXT, " + columnAddress + " TEXT, " + columnCity + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String surname,String designation,String DOB,String JoinDate,String Salary,String Address,String City)
    {
            try
            {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues cv =new ContentValues();
                cv.put(columnName,name);
                cv.put(columnSurname,surname);
                cv.put(columnDesignation,designation);
                cv.put(columnDOB,DOB);
                cv.put(columnJoiningDate,JoinDate);
                cv.put(columnSalary,Salary);
                cv.put(columnAddress,Address);
                cv.put(columnCity,City);

                long result = db.insert(TABLE_NAME,null,cv);
                if(result ==  -1)
                {
                    return  false;
                }
                else
                {
                    return true;
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
    }
    public ArrayList<SQLiteEmployeeData> getEmployeeData()
    {
        ArrayList<SQLiteEmployeeData> list = new ArrayList<>();
        String SQL = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmployeeData data = new SQLiteEmployeeData();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setDesignation(cursor.getString(3));
                list.add(data);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<SQLiteEmployeeData_All> getEmployeeALlData(String id)
    {
        ArrayList<SQLiteEmployeeData_All> list = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE emp_id="+id;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmployeeData_All data = new SQLiteEmployeeData_All();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setSurname(cursor.getString(2));
                data.setDesignation(cursor.getString(3));
                data.setDob(cursor.getString(4));
                data.setJoindate(cursor.getString(5));
                data.setSalary(cursor.getString(6));
                data.setAddress(cursor.getString(7));
                data.setCity(cursor.getString(8));
                list.add(data);
            }
            while (cursor.moveToNext());
        }
        return list;
    }

    public void DeleteRecord(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"emp_id=?",new String[]{id});
        db.close();
    }
}