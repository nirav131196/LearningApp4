package com.example.exam;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

  //  public static final String FILE_DIR = "DATABASE";
    public static final String DATABASE_NAME  = "Master2.db";
    public static final String TABLE_NAME= "DATA";
    public static final String ColumnID = "user_id";
    public static final String Column_2 = "name";
    public static final String Column_3 = "dateofbirth";
    public static final String Column_4 = "email";
    public static final String Column_5 = "password";
    public static final String Column_6 = "phoneno";
    public static final String Column_7 = "collegename";
    public static final String Column_8 = "degree";
    public static final String Column_9 = "city";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + ColumnID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_2 + " TEXT,"+ Column_3 +" TEXT," + Column_4 +" TEXT,"+ Column_5 +" TEXT,"+ Column_6 +" TEXT,"+ Column_7 +" TEXT,"+ Column_8 +" TEXT,"+ Column_9 +" TEXT)";
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String dob,String email,String pass,String phone,String college,String degree, String city)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv= new ContentValues();
            cv.put(Column_2,name);
            cv.put(Column_3,dob);
            cv.put(Column_4,email);
            cv.put(Column_5,pass);
            cv.put(Column_6,phone);
            cv.put(Column_7,college);
            cv.put(Column_8,degree);
            cv.put(Column_9,city);

            long result = db.insert(TABLE_NAME,null,cv);

            if (result == -1)
                return  false;
            else
                return  true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }
    }
    public boolean checkdata(String email,String pass)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
      //      Cursor cursor = db.rawQuery(" SELECT ColumnID FROM " + TABLE_NAME + " WHERE " + Column_4 + " = ' " + email + " ' " + "  AND " + Column_5 + " = ' " + pass + " ' ",null);
            String[] columns = {ColumnID};
            String selection = Column_4 + " = ?"+ " AND " + Column_5 + " = ?";
            String[]  selectionargs = {email,pass};
            Cursor cursor =db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);
            int cursorcount = cursor.getCount();
            cursor.close();
            db.close();

            if(cursorcount > 0 )
                return  true;
            else
                return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return  false;
        }
    }
    @SuppressLint("Range")
    public String getName(String id){

        Cursor cursor;
        String name = "";

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT "+ Column_2 + " FROM " + TABLE_NAME + " WHERE user_id=?",new String[]{id + ""});

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                name = cursor.getString(cursor.getColumnIndex(Column_2));
            }
            return name;
        }
        catch(Exception ex){
            return name;
        }

    }
    @SuppressLint("Range")
    public String getDateOfBirth(String id){

        Cursor cursor;
        String dateOfBirth = "";

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT "+ Column_3 + " FROM " + TABLE_NAME + " WHERE user_id=?",new String[]{id + ""});

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                dateOfBirth = cursor.getString(cursor.getColumnIndex(Column_3));
            }
            return dateOfBirth;
        }
        catch(Exception ex){
            return dateOfBirth;
        }

    }
    @SuppressLint("Range")
    public String getEmail(String id){

        Cursor cursor;
        String email = "";

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT "+ Column_4 + " FROM " + TABLE_NAME + " WHERE user_id=?",new String[]{id + ""});

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                email = cursor.getString(cursor.getColumnIndex(Column_4));
            }
            return email;
        }
        catch(Exception ex){
            return email;
        }

    }

    @SuppressLint("Range")
    public String getCity(String id){

        Cursor cursor;
        String city = "";

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT "+ Column_9 + " FROM " + TABLE_NAME + " WHERE user_id=?",new String[]{id + ""});

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                city = cursor.getString(cursor.getColumnIndex(Column_9));
            }
            return city;
        }
        catch(Exception ex){
            return city;
        }

    }
    @SuppressLint("Range")
    public String getMobileNo(String id){

        Cursor cursor;
        String mobileNo = "";

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT "+ Column_6 + " FROM " + TABLE_NAME + " WHERE user_id=?",new String[]{id + ""});

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                mobileNo = cursor.getString(cursor.getColumnIndex(Column_6));
            }
            return mobileNo;
        }
        catch(Exception ex){
            return mobileNo;
        }

    }
    @SuppressLint("Range")
    public int getId(String email, String password2){

        Cursor cursor;
        int studentId = 0;

        try{

            SQLiteDatabase db = this.getWritableDatabase();
            //cursor = db.rawQuery("SELECT "+ ColumnID + " FROM " + TABLE_NAME + " WHERE email=? AND password=?",new String[]{email + "" + password + ""});
           // cursor = db.rawQuery(" SELECT " + ColumnID + " FROM " + TABLE_NAME + " WHERE " + Column_4 + " = ' " + email + " ' " + "  AND " + Column_5 + " = ' " + password2 + " ' ",null);
            String[] columns = {ColumnID};
            String selection = Column_4 + " = ?"+ " AND " + Column_5 + " = ?";
            String[]  selectionargs = {email,password2};
            cursor =db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);

            if(cursor.getCount()  > 0){
                cursor.moveToFirst();
                studentId = cursor.getInt(cursor.getColumnIndex(ColumnID));
                Log.e("EXCEPTION","Exception 1 "+studentId);
            }
            else{
                Log.e("EXCEPTION","Exception 2 "+studentId);
            }

            return studentId;
        }
        catch(Exception ex){
            Log.e("EXCEPTION","Exception 3 "+ex);
            return studentId;

        }

    }
}