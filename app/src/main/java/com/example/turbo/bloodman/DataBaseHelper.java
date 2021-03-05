package com.example.turbo.bloodman;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="BloodMan.db";
    public static final String TABLE_NAME="Donor";
    public static final String DCOL_1="ID";
    public static final String DCOL_2="Name";
    public static final String DCOL_3="email";
    public static final String DCOL_4="Phone";
    public static final String DCOL_5="Pass";
    public static final String DCOL_6="Bloodt";
    public static final String DCOL_7="Location";
    public static final String DCOL_8="Avail";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT,email TEXT,Phone TEXT,Pass TEXT,Bloodt TEXT,Location TEXT,Avail TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);

    }

}
