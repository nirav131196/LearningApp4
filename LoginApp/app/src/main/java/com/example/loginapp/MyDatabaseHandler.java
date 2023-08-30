package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHandler extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "DATA";
    public static final String TABLE_NAME = "DETAILS";

    public static final String Column_id = "ID";
    public static final String Column_name = "NAME";
    public static final String Column_surname = "SURNAME";


    public MyDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE  = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME+ "(" + Column_id + " INTEGER PRIMARY KEY," + Column_name + " TEXT, " + Column_surname + " TEXT)";
        db.execSQL(TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    public  boolean insertData(String name,String surname)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(Column_name,name);
            cv.put(Column_surname,surname);

            long result = db.insert(TABLE_NAME,null,cv);
             if(result == -1){
                 return false;
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
    public ArrayList<MyDataModel> getData(){

        ArrayList<MyDataModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(SQL,null);
        if(cursor.moveToFirst())
        {

            do{
                MyDataModel data = new MyDataModel();
                data.setId(cursor.getString(0));
                data.setName(cursor.getString(1));
                data.setSurname(cursor.getString(2));

                list.add(data);

            }while(cursor.moveToNext());
        }
        return  list;
    }
    public boolean deleteRecord(String id){

        try{
            SQLiteDatabase db =this.getWritableDatabase();
            db.delete(TABLE_NAME,"ID=?",new String[]{id});
            db.close();
            return  true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean updateRecord(String id,String name,String surname){
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(Column_name,name);
            cv.put(Column_surname,surname);
            db.update(TABLE_NAME,cv,"ID=?",new String[]{id});
            db.close();
            return  true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
