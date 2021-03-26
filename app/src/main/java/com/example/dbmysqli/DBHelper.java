package com.example.dbmysqli;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname ="Signup";
    public DBHelper(@Nullable Context context) {
        super(context, DBname,  null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table users (id integer primary key autoincrement, name text, unname text, pass text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public boolean insert(String name, String uname, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("uname",uname);
        cv.put("pas",pass);
        long r= db.insert("User","null",cv);
        if(r==-1)
            return false;
        else
            return true;



    }
}
