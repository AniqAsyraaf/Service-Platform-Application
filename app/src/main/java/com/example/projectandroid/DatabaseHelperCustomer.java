package com.example.projectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperCustomer extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="CustomerApp.db";
    public static final String TABLE_NAME_CUSTOMER="customer_table";
    public static final String col1_CUSTOMER="ID";
    public static final String col2_CUSTOMER="NAME";
    public static final String col3_CUSTOMER="CONTACT_NUM";
    public static final String col4_CUSTOMER="EMAIL";
    public static final String col5_CUSTOMER="PASSWORD";



    public DatabaseHelperCustomer(Context context) {
        super(context, DATABASE_NAME, null, 1);//create database

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_CUSTOMER+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CONTACT_NUM TEXT,EMAIL TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_CUSTOMER);
        onCreate(db);
    }

    public boolean insertDataCustomer(String NAME, String CONTACT_NUM, String EMAIL, String PASSWORD)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperCustomer.col2_CUSTOMER,NAME);
        contentValues.put(DatabaseHelperCustomer.col3_CUSTOMER,CONTACT_NUM);
        contentValues.put(DatabaseHelperCustomer.col4_CUSTOMER,EMAIL);
        contentValues.put(DatabaseHelperCustomer.col5_CUSTOMER,PASSWORD);
        long result=db.insert(DatabaseHelperCustomer.TABLE_NAME_CUSTOMER,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataCustomer()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+DatabaseHelperCustomer.TABLE_NAME_CUSTOMER,null);
        return res;

    }

    public boolean updateDataCustomer(String id, String NAME, String CONTACT_NUM, String EMAIL, String PASSWORD)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperCustomer.col1_CUSTOMER,id);
        contentValues.put(DatabaseHelperCustomer.col2_CUSTOMER,NAME);
        contentValues.put(DatabaseHelperCustomer.col3_CUSTOMER,CONTACT_NUM);
        contentValues.put(DatabaseHelperCustomer.col4_CUSTOMER,EMAIL);
        contentValues.put(DatabaseHelperCustomer.col5_CUSTOMER,PASSWORD);
        db.update(TABLE_NAME_CUSTOMER,contentValues,"ID=?",new String[] {id});
        return true;
    }

    public Integer deleteDataCustomer(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_CUSTOMER,"ID=?",new String[] {id});
    }

}
