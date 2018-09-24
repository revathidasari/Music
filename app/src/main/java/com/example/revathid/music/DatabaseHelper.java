package com.example.revathid.music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {public DatabaseHelper(Context context) {
    super(context, "details..db", null, 1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user (name text, mobile text, username text primary key, password text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //inserting the data
    public boolean insert(String name, int mobile, String username, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("mobile", mobile);
        contentValues.put("username", username);
        contentValues.put("password", password);
        long ns= db.insert("user",null, contentValues);
        if (ns==-1) return false;
        else return true;
    }
    //checking if username exists
    public Boolean checkusername(String username){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user where username=?", new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //checkin the details
    public Boolean match(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{username, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }


}

