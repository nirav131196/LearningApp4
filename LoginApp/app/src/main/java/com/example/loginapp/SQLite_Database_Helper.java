package com.example.loginapp;

import static android.content.Context.MODE_PRIVATE;
import static android.icu.text.ListFormatter.Type.AND;
import static android.icu.text.MessagePattern.ArgType.SELECT;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.google.firebase.crashlytics.buildtools.reloc.com.google.common.net.HttpHeaders.FROM;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLite_Database_Helper extends SQLiteOpenHelper {

    public static final String databasename = "employee8";
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


    //  FIELDS FOR FOREIGN KEY
    public static final String employee = "employee";
    public static final String columnId2 = "emp_id";
    public static final String columnMobileNo = "emp_mobileno";
    public static final String groupid1 = "empid1";

    public static final String employeegroup = "employee2";
    public static final String groupid2 = "empid2";
    public static final String columnMobileNo2 = "emp_mobileno2";

    public SQLite_Database_Helper(Context context)
    {
        super(context, databasename, null, 37);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +"(" + columnId + " INTEGER PRIMARY KEY, " + columnName + " TEXT, " + columnSurname + " TEXT, " + columnDesignation + " TEXT, " + columnDOB + " TEXT, " + columnJoiningDate + " TEXT, " + columnSalary + " TEXT, " + columnAddress + " TEXT, " + columnCity + " TEXT)";
        db.execSQL(CREATE_TABLE);

        // TABLE 1
        db.execSQL("create table " + employeegroup +"(" + groupid2 + " integer primary key autoincrement, "
                + columnMobileNo2 + " text"+")");


        // TABLE 2
        db.execSQL("create table " + employee +"(" + columnId2 + " integer primary key autoincrement, "
                + columnMobileNo + " text, "
                + groupid1 + " integer references " + employeegroup + "(" + groupid2 + ")"+")");


       /* String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE +"(" + columnId2 + " INTEGER PRIMARY KEY, "
                + columnMobileNo + " TEXT, "
                + group_id + " INTEGER UNIQUE references " + EMPLOYEE_GROUP + "(" + group_id2 + "))";
        db.execSQL(CREATE_TABLE2);

        String CREATE_TABLE3 = "CREATE TABLE IF NOT EXISTS " + EMPLOYEE_GROUP +"(" + group_id2 + " INTEGER PRIMARY KEY, "
                + columnMobileNo2 + " TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE3);*/



       /* String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS EMPLOYEE_THREE " +
                "(emp_id INTEGER PRIMARY KEY,emp_mobileno TEXT,emp_id2 INTEGER UNIQUE, FOREIGN KEY(emp_id2) REFERENCES EMPLOYEE_FOUR(emp_id2))";
        db.execSQL(CREATE_TABLE2);

        String CREATE_TABLE3 = "CREATE TABLE IF NOT EXISTS EMPLOYEE_FOUR (emp_id2 INTEGER PRIMARY KEY,emp_mobileno2 TEXT)";
        db.execSQL(CREATE_TABLE3);*/
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+employee);
        db.execSQL("DROP TABLE IF EXISTS "+employeegroup);
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
    public boolean insertEmp(String mobileno,String groupid)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv =new ContentValues();

            cv.put(columnMobileNo,mobileno);
            cv.put(groupid1,groupid);

            long result2 = db.insert(employee,null,cv);
            if(result2 ==  -1)
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
    public boolean insertEmpGroup(String mobileno)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv =new ContentValues();
            cv.put(columnMobileNo2,mobileno);

            long result2 = db.insert(employeegroup,null,cv);
            if(result2 ==  -1)
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
    public ArrayList<SQLiteEmployeeData> getEmployeeData(String salary)
    {
        ArrayList<SQLiteEmployeeData> list = new ArrayList<>();
        String SQL = "SELECT * FROM " + TABLE_NAME + " WHERE emp_salary > "+ salary;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmployeeData data = new SQLiteEmployeeData();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setDesignation(cursor.getString(3));
                data.setSalary(cursor.getString(6));
                list.add(data);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<SQLiteEmployeeData> getEmpDataByPost(String post)
    {
        ArrayList<SQLiteEmployeeData> list = new ArrayList<>();
        String SQL = "SELECT * FROM EMPLOYEE_DETAILS WHERE emp_designation='"+post+"'";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmployeeData data = new SQLiteEmployeeData();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setDesignation(cursor.getString(3));
                data.setSalary(cursor.getString(6));
                list.add(data);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<SQLiteEmployeeData> getEmpDataPostAndSalary(String salary,String post)
    {
        ArrayList<SQLiteEmployeeData> list = new ArrayList<>();
        String SQL = "SELECT * FROM EMPLOYEE_DETAILS WHERE emp_salary>'"+salary+"' AND emp_designation='"+post+"'";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmployeeData data = new SQLiteEmployeeData();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setDesignation(cursor.getString(3));
                data.setSalary(cursor.getString(6));
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
    public ArrayList<SQLiteEmpData> getData1()
    {
        ArrayList<SQLiteEmpData> list = new ArrayList<>();
        String SQL = "SELECT * FROM " + employee;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmpData data = new SQLiteEmpData();
                data.setId(cursor.getString(0));
                data.setMobileno(cursor.getString(1));
                data.setGroupid(cursor.getString(2));

                list.add(data);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<SQLiteEmpData2> getData2()
    {
        ArrayList<SQLiteEmpData2> list = new ArrayList<>();
        String SQL = "SELECT * FROM " + employeegroup;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor =db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {
                SQLiteEmpData2 data = new SQLiteEmpData2();

                data.setGroupid2(cursor.getString(0));
                data.setMobileno2(cursor.getString(1));
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
    public void UpdateRecord(String id,String name,String surname,String post,String dob,String joindate,String salary,String address,String city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(columnName,name);
        cv.put(columnSurname,surname);
        cv.put(columnDesignation,post);
        cv.put(columnDOB,dob);
        cv.put(columnJoiningDate,joindate);
        cv.put(columnSalary,salary);
        cv.put(columnAddress,address);
        cv.put(columnCity,city);
        db.update(TABLE_NAME,cv,"emp_id=?",new String[]{id});
        db.close();
    }
}