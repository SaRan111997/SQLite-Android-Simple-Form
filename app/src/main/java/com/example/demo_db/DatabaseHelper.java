package com.example.demo_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
//SaRan 27-10-21
//https://www.youtube.com/channel/UC13QfHb2Dyncn1fwuavXq9A

public class DatabaseHelper extends SQLiteOpenHelper {

    //Initialize all the fields needed for database
    public static final String DATABASE_NAME = "Employee.db";
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

    //Just pass context of the app to make it simpler
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, 2 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table

        db.execSQL( "create table " + TABLE_NAME + LBR + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT" + COM +
                COL_2 + " TEXT" + COM + COL_3 + " TEXT" + COM + COL_4 + " TEXT" + COM + COL_5 +" TEXT"+ COM + COL_6 + " TEXT"  +RBR );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Dropping old table
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate( db );

    }

    //Insert data in database
    public boolean instertData(String name, String empid, String gender, String salary, String address){

        //Get the instance of SQL Database which we have created
        SQLiteDatabase db = getWritableDatabase();

        //To pass all the values in database
        ContentValues contentValues = new ContentValues();
        contentValues.put( COL_2, name );
        contentValues.put( COL_3, empid );
        contentValues.put( COL_4, gender );
        contentValues.put( COL_5, salary);
        contentValues.put( COL_6, address);

        long result = db.insert( TABLE_NAME, null, contentValues );

        if(result == -1)
            return false;
        else
            return true;
    }

    //Cursor class is used to move around in the database
    public Cursor getData(){

        //Get the data from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery( "select * from " + TABLE_NAME, null );
        return res;
    }


}















