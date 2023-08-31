package com.example.myapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TableRow;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASENAME = "MyData";
    public static final String TABLE_NAME = "MyDetails";
    public static final String Column_Id = "ID";
    public static final String Column_Name = "NAME";
    public static final String Column_Email = "EMAIL";
    public static final String Column_Password = "PASSWORD";
    public static final String Column_gender = "GENDER";
    public static final String Column_DOB = "DOB";
    String myid;



    public DatabaseHelper(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE  = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + Column_Id + " INTEGER PRIMARY KEY," + Column_Name + " TEXT," + Column_Email + " TEXT," + Column_Password + " TEXT," + Column_gender + " TEXT," + Column_DOB + " TEXT)");
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



    public boolean insertData(String name,String email,String password,String gender,String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_Name,name);
        cv.put(Column_Email,email);
        cv.put(Column_Password,password);
        cv.put(Column_gender,gender);
        cv.put(Column_DOB,dob);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public boolean deleteData(String id)
    {

        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_NAME,"ID=?",new String[]{id});
            db.close();
            return  true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }
    }

    public void updateData(String id,String name,String email,String gender,String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Column_Name,name);
        cv.put(Column_Email,email);
        cv.put(Column_gender,gender);
        cv.put(Column_DOB,dob);
        db.update(TABLE_NAME,cv,"ID=?",new String[]{id});
        db.close();
    }
    public ArrayList<MyModelFile>  getAllData()
    {
        ArrayList<MyModelFile> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT ID , NAME , EMAIL , GENDER , DOB FROM  "+TABLE_NAME;
        Cursor  cursor = db.rawQuery(SQL,null);

         if(cursor.moveToFirst())
         {
             do {
                    MyModelFile data = new MyModelFile();

                    data.setId(cursor.getString(0));
                    data.setName(cursor.getString(1));
                    data.setEmail(cursor.getString(2));
                    data.setGender(cursor.getString(3));
                    data.setDob(cursor.getString(4));

                    list.add(data);

             }while(cursor.moveToNext());
         }
         return  list;
    }


    public ArrayList<MyModelFile> getMyData(String id)
    {
        ArrayList<MyModelFile> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SQL = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {
            do {

                MyModelFile data = new MyModelFile();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setDob(cursor.getString(2));

                list.add(data);
            }while(cursor.moveToNext());
        }
        return  list;
    }

    public boolean getId(String email)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Column_Id};
        String selection = Column_Email + " = ?";
        String[]  selectionargs = {email};
      //  String SQL = "SELECT ID , NAME , EMAIL , GENDER , DOB FROM  "+TABLE_NAME+ " WHERE " + Column_Id + " = ?";
        Cursor  cursor = db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);

        int cursorcount = cursor.getCount();


        if(cursorcount > 0)
        {

                cursor.moveToFirst();
                myid = cursor.getString(0);

            return true;
        }
        else
        {
            return false;
        }
    }
    public  boolean getId2(String email)
    {
        SQLiteDatabase db =this.getReadableDatabase();
        String[] columns = {Column_Id};
        String selection = Column_Email + " = ? ";
        String[] selectionarg = {email};
        Cursor c =db.query(TABLE_NAME,columns,selection,selectionarg,null,null,null);
        int count = c.getCount();

        if(count > 0)
        {
            while(count > 0)
            {
                String id = c.getString(0);
                count--;
            }

            return true;
        }
        else
        {
            return  false;
        }
    }

    public ArrayList<MyModelFile> getIndividualData(String id)
    {
        ArrayList<MyModelFile> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {Column_Id,Column_Name,Column_Email,Column_gender,Column_DOB};
        String selection = Column_Id + " = ?";
        String[]  selectionargs = {id};
        //  String SQL = "SELECT ID , NAME , EMAIL , GENDER , DOB FROM  "+TABLE_NAME+ " WHERE " + Column_Id + " = ?";
        Cursor  cursor = db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);

        int cursorcount = cursor.getCount();


        if(cursorcount > 0)
        {
            if(cursor.moveToFirst())
            {
                MyModelFile data = new MyModelFile();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setEmail(cursor.getString(2));
                data.setGender(cursor.getString(3));
                data.setDob(cursor.getString(4));

                list.add(data);
            }
        }
        return  list;
    }
    public String myid(){

        return  myid;
    }
    /*public void  loginOfUser(String email,String password)
    {
        ArrayList<MyModelFile> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String fetch = "SELECT email and password FROM "+ TABLE_NAME+" WHERE "+ Column_Email = email +" AND "+ Column_Password = password "";
        db.execSQL(fetch);

    }*/
    public boolean checkdata(String email,String pass)  // FOR LOGIN
    {
        try
        {
            //      Cursor cursor = db.rawQuery(" SELECT ColumnID FROM " + TABLE_NAME + " WHERE " + Column_4 + " = ' " + email + " ' " + "  AND " + Column_5 + " = ' " + pass + " ' ",null);

            SQLiteDatabase db = this.getWritableDatabase();
            String[] columns = {Column_Id};
            String selection = Column_Email + " = ?"+ " AND " + Column_Password + " = ?";
            String[]  selectionargs = {email,pass};
            Cursor cursor =db.query(TABLE_NAME,columns,selection,selectionargs,null,null,null);
            int cursorcount = cursor.getCount();
            db.close();
            cursor.close();

            if(cursorcount > 0)
            {

                return  true;
            }
            else
            {
                return false;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            return  false;
        }
    }

}
