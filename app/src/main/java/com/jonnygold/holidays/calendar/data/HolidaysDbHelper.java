package com.jonnygold.holidays.calendar.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HolidaysDbHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Holidays.db";

    public HolidaysDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HolidaysContract.Countries.STMT_CREATE);
        db.execSQL(HolidaysContract.Images.STMT_CREATE);
        db.execSQL(HolidaysContract.Priority.STMT_CREATE);
        db.execSQL(HolidaysContract.Holidays.STMT_CREATE);
        db.execSQL(HolidaysContract.CountryHolidays.STMT_CREATE);
        db.execSQL(HolidaysContract.EasterHolidays.STMT_CREATE);
        db.execSQL(HolidaysContract.MonthFloatHolidays.STMT_CREATE);
        db.execSQL(HolidaysContract.YearFloatHolidays.STMT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
