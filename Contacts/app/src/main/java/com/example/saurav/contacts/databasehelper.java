package com.example.saurav.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by saurav on 31/3/18.
 */

public class databasehelper extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contact.db";
    private static final String TABLE_USER="user";
    private static final String USER_ID= "user_id";
    private static final String USER_NAME="user_name";
    private static final String USER_PHONE="user_phone";
    private static final String USER_E_MAIL="user_e_mail";
    private String CREATE_USER_TABLE="CREATE TABLE " + TABLE_USER + "(USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT,USER_PHONE TEXT, USER_E_MAIL TEXT)";
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + TABLE_USER;
    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }
    public void adduser(user u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,u.getName());
        values.put(USER_E_MAIL,u.getE_mail());
        values.put(USER_PHONE,u.getPhone());
        db.insert(TABLE_USER,null,values);
        db.close();
    }
    public Cursor getalldata(){
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s", TABLE_USER),null);
        return  res;
    }
    public Cursor getdata(String name, String email)
    {
        SQLiteDatabase db =  this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s WHERE USER_NAME = %s", TABLE_USER,name),null);
        return  res;
    }
}
