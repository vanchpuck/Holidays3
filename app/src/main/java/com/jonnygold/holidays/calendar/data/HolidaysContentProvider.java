package com.jonnygold.holidays.calendar.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class HolidaysContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.jonnygold.holidays.contentprovider";
    public static final String HOLIDAYS_PATH = "com.jonnygold.holidays.contentprovider";
    public static final String URL = String.format("content://%s/%s", AUTHORITY, HOLIDAYS_PATH);
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static final int CODE_COUNTRIES = 1;
    private static final int CODE_COUNTRIES_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    private HolidaysDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        dbHelper = new HolidaysDbHelper(getContext());
        fillUriMatcher();
        return true;
    }

    private void fillUriMatcher() {
        uriMatcher.addURI(AUTHORITY, HOLIDAYS_PATH, CODE_COUNTRIES);
        uriMatcher.addURI(AUTHORITY, HOLIDAYS_PATH + "/#", CODE_COUNTRIES_ID);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case CODE_COUNTRIES_ID:
                return getPaginatedHolidays(projection, selection, selectionArgs, sortOrder);
            case CODE_COUNTRIES:
                return getPaginatedHolidays(projection, selection, selectionArgs, sortOrder);
            default:
                throw new IllegalArgumentException("Illegal URI for query: " + uri);
        }
    }

    private Cursor getPaginatedHolidays(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = dbHelper.getReadableDatabase();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(HolidaysContract.Holidays.TAB_NAME);

        return db.rawQuery(String.format("SELECT %s FROM %s WHERE %s ORDER BY %s", "*", "holidays", selection, sortOrder), new String[]{});
    }

    private Cursor getHolidays(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db = dbHelper.getReadableDatabase();

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(HolidaysContract.Holidays.TAB_NAME);

        return db.rawQuery(String.format("SELECT %s FROM %s ORDER BY _ID ASC", "*", "holidays"), new String[]{});
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case CODE_COUNTRIES:
                db = dbHelper.getWritableDatabase();
                long rowID = db.insert(HolidaysContract.Countries.TAB_NAME, null, values);
                if (rowID > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return ContentUris.withAppendedId(CONTENT_URI, rowID);
                }
                throw new SQLiteException("Failed to add a record into " + uri);
            default:
                throw new IllegalArgumentException("Illegal URI for insert: " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
