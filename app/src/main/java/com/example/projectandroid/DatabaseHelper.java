package com.example.projectandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME="AppService.db";
    private static final int DATABASE_VERSION = 9; //Version 4 add column for customer table

    //service table
    public static final String TABLE_NAME_SERVICE="service_provider_table";
    public static final String col1_SERVICE="ID";
    public static final String col2_SERVICE="BUSINESS_NAME";
    public static final String col3_SERVICE="SERVICE_TYPE";
    public static final String col4_SERVICE="CONTACT_NUM";
    public static final String col5_SERVICE="EMAIL";
    public static final String col6_SERVICE="PASSWORD";
    public static final String col7_SERVICE="STATE";
    public static final String col8_SERVICE="AREA";
    public static final String col9_SERVICE="LOGO";

    //customer table
    public static final String TABLE_NAME_CUSTOMER="customer_table";
    public static final String col1_CUSTOMER="ID";
    public static final String col2_CUSTOMER="NAME";
    public static final String col3_CUSTOMER="CONTACT_NUM";
    public static final String col4_CUSTOMER="EMAIL";
    public static final String col5_CUSTOMER="PASSWORD";
    public static final String col6_CUSTOMER="STATE";
    public static final String col7_CUSTOMER="AREA";
    public static final String col8_CUSTOMER="ADDRESS";

    //service list table
    public static final String TABLE_NAME_SERLIST="serlist_table";
    public static final String col1_SERLIST="ID";
    public static final String col2_SERLIST="SERVICE_NAME";
    public static final String col3_SERLIST="Description";
    public static final String col4_SERLIST="Price_Min";
    public static final String col5_SERLIST="Price_Max";
    public static final String col6_SERLIST="Company_ID";

    //booking list table
    public static final String TABLE_NAME_BOOKING="booking_table";
    public static final String col1_BOOKING="ID";
    public static final String col2_BOOKING="DATE";
    public static final String col3_BOOKING="TIME";
    public static final String col4_BOOKING="SERLIST_ID";
    public static final String col5_BOOKING="CUSTOMER_ID";
    public static final String col6_BOOKING="STATUS";

    //rating table
    public static final String TABLE_NAME_RATING="rating_table";
    public static final String col1_RATING="ID";
    public static final String col2_RATING="RATING";
    public static final String col3_RATING="CUSTOMER_ID";
    public static final String col4_RATING="COMPANY_ID";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);//create database

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_SERVICE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,BUSINESS_NAME TEXT,SERVICE_TYPE TEXT,CONTACT_NUM TEXT,EMAIL TEXT,PASSWORD TEXT,STATE TEXT,AREA TEXT, LOGO BLOB)");
        db.execSQL("CREATE TABLE "+TABLE_NAME_CUSTOMER+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CONTACT_NUM TEXT,EMAIL TEXT UNIQUE,PASSWORD TEXT,STATE TEXT,AREA TEXT,ADDRESS TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_NAME_SERLIST+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,SERVICE_NAME TEXT,Description TEXT,Price_Min DOUBLE,Price_Max DOUBLE,Company_ID INTEGER)");
        db.execSQL("CREATE TABLE "+TABLE_NAME_BOOKING+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TIME TEXT, SERLIST_ID INTEGER, CUSTOMER_ID INTEGER, STATUS TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_NAME_RATING+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, RATING FLOAT, CUSTOMER_ID TEXT, COMPANY_ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SERVICE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_SERLIST);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_BOOKING);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_RATING);
        onCreate(db);

    }


    //function service table

    public boolean insertDataService(String BUSINESS_NAME, String SERVICE_TYPE, String CONTACT_NUM, String EMAIL, String PASSWORD, String STATE, String AREA, byte[] LOGO)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col2_SERVICE,BUSINESS_NAME);
        contentValues.put(DatabaseHelper.col3_SERVICE,SERVICE_TYPE);
        contentValues.put(DatabaseHelper.col4_SERVICE,CONTACT_NUM);
        contentValues.put(DatabaseHelper.col5_SERVICE,EMAIL);
        contentValues.put(DatabaseHelper.col6_SERVICE,PASSWORD);
        contentValues.put(DatabaseHelper.col7_SERVICE,STATE);
        contentValues.put(DatabaseHelper.col8_SERVICE,AREA);
        contentValues.put(DatabaseHelper.col9_SERVICE,LOGO);
        long result=db.insert(DatabaseHelper.TABLE_NAME_SERVICE,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataService()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME_SERVICE,null);
        return res;

    }

    //select specific data based on area
    public Cursor getSpecificDataService(String sqlstatement)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery(sqlstatement,null);
        return res;

    }




    public boolean updateDataService(String id, String BUSINESS_NAME, String SERVICE_TYPE, String CONTACT_NUM, String EMAIL, String PASSWORD,String STATE, String AREA, byte[] LOGO)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col1_SERVICE,id);
        contentValues.put(DatabaseHelper.col2_SERVICE,BUSINESS_NAME);
        contentValues.put(DatabaseHelper.col3_SERVICE,SERVICE_TYPE);
        contentValues.put(DatabaseHelper.col4_SERVICE,CONTACT_NUM);
        contentValues.put(DatabaseHelper.col5_SERVICE,EMAIL);
        contentValues.put(DatabaseHelper.col6_SERVICE,PASSWORD);
        contentValues.put(DatabaseHelper.col7_SERVICE,STATE);
        contentValues.put(DatabaseHelper.col8_SERVICE,AREA);
        contentValues.put(DatabaseHelper.col9_SERVICE,LOGO);
        db.update(TABLE_NAME_SERVICE,contentValues,"ID=?",new String[] {id});
        return true;
    }

    public Integer deleteDataService(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_SERVICE,"ID=?",new String[] {id});
    }



    //function customer table


    public boolean insertDataCustomer(String NAME, String CONTACT_NUM, String EMAIL, String PASSWORD,String STATE,String AREA,String ADDRESS)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col2_CUSTOMER,NAME);
        contentValues.put(DatabaseHelper.col3_CUSTOMER,CONTACT_NUM);
        contentValues.put(DatabaseHelper.col4_CUSTOMER,EMAIL);
        contentValues.put(DatabaseHelper.col5_CUSTOMER,PASSWORD);
        contentValues.put(DatabaseHelper.col6_CUSTOMER,STATE);
        contentValues.put(DatabaseHelper.col7_CUSTOMER,AREA);
        contentValues.put(DatabaseHelper.col8_CUSTOMER,ADDRESS);
        long result=db.insert(DatabaseHelper.TABLE_NAME_CUSTOMER,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllDataCustomer()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME_CUSTOMER,null);
        return res;

    }

    public boolean updateDataCustomer(String id, String NAME, String CONTACT_NUM, String EMAIL, String PASSWORD,String STATE,String AREA,String ADDRESS)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col1_CUSTOMER,id);
        contentValues.put(DatabaseHelper.col2_CUSTOMER,NAME);
        contentValues.put(DatabaseHelper.col3_CUSTOMER,CONTACT_NUM);
        contentValues.put(DatabaseHelper.col4_CUSTOMER,EMAIL);
        contentValues.put(DatabaseHelper.col5_CUSTOMER,PASSWORD);
        contentValues.put(DatabaseHelper.col6_CUSTOMER,STATE);
        contentValues.put(DatabaseHelper.col7_CUSTOMER,AREA);
        contentValues.put(DatabaseHelper.col8_CUSTOMER,ADDRESS);
        db.update(TABLE_NAME_CUSTOMER,contentValues,"ID=?",new String[] {id});
        return true;
    }

    public Integer deleteDataCustomer(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_CUSTOMER,"ID=?",new String[] {id});
    }

    //service list table
    public boolean insertDataSerlist(String SERVICE_NAME, String Description, double Price_Min, double Price_Max, int Company_ID)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col2_SERLIST,SERVICE_NAME);
        contentValues.put(DatabaseHelper.col3_SERLIST,Description);
        contentValues.put(DatabaseHelper.col4_SERLIST,Price_Min);
        contentValues.put(DatabaseHelper.col5_SERLIST,Price_Max);
        contentValues.put(DatabaseHelper.col6_SERLIST,Company_ID);
        long result=db.insert(DatabaseHelper.TABLE_NAME_SERLIST,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    //booking list table
    public boolean insertDataBooking(String date, String time, int serlist_id, int customer_id, String status)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col2_BOOKING,date);
        contentValues.put(DatabaseHelper.col3_BOOKING,time);
        contentValues.put(DatabaseHelper.col4_BOOKING,serlist_id);
        contentValues.put(DatabaseHelper.col5_BOOKING,customer_id);
        contentValues.put(DatabaseHelper.col6_BOOKING,status);

        long result=db.insert(DatabaseHelper.TABLE_NAME_BOOKING,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean updateDataBookingStatus(String id, String status)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col1_BOOKING,Integer.parseInt(id));
        contentValues.put(DatabaseHelper.col6_BOOKING,status);
        db.update(TABLE_NAME_BOOKING,contentValues,"ID=?",new String[]{id});
        return true;
    }

    //rating table
    public boolean insertDataRating(float rating, int customer_id, int company_id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.col2_RATING,rating);
        contentValues.put(DatabaseHelper.col3_RATING,customer_id);
        contentValues.put(DatabaseHelper.col4_RATING,company_id);

        long result=db.insert(DatabaseHelper.TABLE_NAME_RATING,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
}
