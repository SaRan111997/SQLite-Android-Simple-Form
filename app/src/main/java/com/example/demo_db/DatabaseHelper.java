package com.example.demo_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
//SaRan 27-10-21
//https://www.youtube.com/channel/UC13QfHb2Dyncn1fwuavXq9A

public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize all the fields needed for database
    public static final String DATABASE_NAME = "Employee";
    public static final String BACKUP_DB = "Employee_BC.db";
    public static final String TABLE_NAME = "employe_data";
    public static final String COL_1 = "ID";//int
    public static final String COL_2 = "Name";//varchar
    public static final String COL_3 = "empid";//varchar
    public static final String COL_4 = "gender";//varchar
    public static final String COL_5 = "salary";//int
    public static final String COL_6 = "address";//varchar
    public static final String LBR = "(";
    public static final String RBR = ")";
    public static final String COM = ",";
    public static final int DATABASE_VERSION=1;
    private SQLiteDatabase sqLiteDatabase;
    MyDBAdapter myDBAdapter;
    //private GridViewActivity myDBAdapter;
    public DatabaseHelper open()
    {
        SQLiteDatabase db = getWritableDatabase();
        return this;
    }
//    public void close()
//    {
//        sqLiteDatabase.close();
//    }

    //Just pass context of the app to make it simpler
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table

        db.execSQL("create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " TEXT" + COM + COL_5 + " TEXT" + COM + COL_6 + " TEXT" + RBR);


    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        //Dropping old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    //Insert data in database
    public boolean instertData(String name, String empid, String gender, String salary, String address) {

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, empid);
        contentValues.put(COL_4, gender);
        contentValues.put(COL_5, salary);
        contentValues.put(COL_6, address);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //Cursor class is used to move around in the database
    public Cursor getData() {

        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getCompanies() {
        try {
            String selectQuery = "SELECT * FROM employe_data";
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery(selectQuery, null);
            return cursor;
        } catch (Exception ex) {
            return null;
        }
    }

    public ArrayList<HashMap<String, String>> getProducts() {

        ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("ID", cursor.getString(0));
                map.put("Name", cursor.getString(1));
                map.put("empid", cursor.getString(2));
                map.put("gender", cursor.getString(3));
                map.put("salary", cursor.getString(4));
                map.put("address", cursor.getString(5));
                productList.add(map);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

// return contact list
        return productList;
    }

    public Cursor getAllEntries(String sql) {
        return sqLiteDatabase.rawQuery(sql,null);
    }

    public static class MyDBAdapter extends SQLiteOpenHelper {
        public MyDBAdapter(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    }




}




















