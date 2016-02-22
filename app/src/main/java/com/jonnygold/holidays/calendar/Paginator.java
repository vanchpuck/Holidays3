package com.jonnygold.holidays.calendar;

import android.content.Context;
import android.database.Cursor;

import com.jonnygold.holidays.calendar.data.HolidaysContentProvider;

import java.util.ArrayList;
import java.util.List;

public class Paginator {

    private final Context context;
    private final int pageWidth;
    private int currPage;

    public Paginator(Context context, int pageWidth) {
        this.context = context;
        this.pageWidth = pageWidth;
        this.currPage = 0;
    }

    public List<String> getMoreHolidays() {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().
                    query(HolidaysContentProvider.CONTENT_URI, null, "_ID > "+(pageWidth*currPage), null, "_ID ASC LIMIT "+pageWidth);
            List<String> holidays = new ArrayList<>(pageWidth);
            while(cursor.moveToNext()) {
                holidays.add(cursor.getString(1));
            }
            currPage++;
            return holidays;
        } finally {
            if(cursor != null) {
                cursor.close();
            }
        }
    }
}
