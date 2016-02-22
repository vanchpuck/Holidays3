package com.jonnygold.holidays.calendar.data;

import android.provider.BaseColumns;

public class HolidaysContract {

    private HolidaysContract(){}

    public interface Images extends BaseColumns {
        public static final String TAB_NAME = "t_images";

        public static final String COL_IMAGE = "image";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_VK_PICTURE = "vk_picture";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s BLOB(51200) NOT NULL, " +
                        "%s VARCHAR(32), " +
                        "%s TEXT)",
                TAB_NAME,
                _ID,
                COL_IMAGE,
                COL_DESCRIPTION,
                COL_VK_PICTURE);
    }

    public interface Priority extends BaseColumns {
        public static final String TAB_NAME = "t_priority";

        public static final String COL_TITLE = "title";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s VARCHAR(32))",
                TAB_NAME,
                _ID,
                COL_TITLE);
    }

    public interface Countries extends BaseColumns {
        public static final String TAB_NAME = "t_countries";

        public static final String COL_FLAG = "flag";
        public static final String COL_NAME = "name";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s TEXT, " +
                        "%s INTEGER PRIMARY KEY, " +
                        "%s VARCHAR(32))",
                TAB_NAME,
                COL_FLAG,
                _ID,
                COL_NAME);
    }

    public interface Holidays extends BaseColumns {
        public static final String TAB_NAME = "t_holidays";

        public static final String COL_TITLE = "title";
        public static final String COL_DESCRIPTION = "description";
        public static final String COL_MONTH = "month";
        public static final String COL_DAY = "day";
        public static final String COL_ACTUAL_DATE_STR = "actualDateStr";
        public static final String COL_ID_PRIORITY = "id_priority";
        public static final String COL_ID_IMAGE = "id_image";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "%s VARCHAR(128) NOT NULL, " +
                        "%s VARCHAR(1024), " +
                        "%s INTEGER(1), " +
                        "%s INTEGER(1), " +
                        "%s TEXT, " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s), " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s))",
                TAB_NAME,
                _ID,
                COL_TITLE,
                COL_DESCRIPTION,
                COL_MONTH,
                COL_DAY,
                COL_ACTUAL_DATE_STR,
                COL_ID_PRIORITY, Priority.TAB_NAME, Priority._ID,
                COL_ID_IMAGE, Images.TAB_NAME, Images._ID);
    }

    public interface CountryHolidays extends BaseColumns {
        public static final String TAB_NAME = "t_countryholidays";

        public static final String COL_ID_COUNTRY = "id_country";
        public static final String COL_ID_HOLIDAY = "id_holiday";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s)), " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s))",
                TAB_NAME,
                _ID,
                COL_ID_COUNTRY, Countries.TAB_NAME, Countries._ID,
                COL_ID_HOLIDAY, Holidays.TAB_NAME, Holidays._ID);
    }

    public interface EasterHolidays extends BaseColumns {
        public static final String TAB_NAME = "t_easterHolidays";

        public static final String COL_ID_HOLIDAY = "id_holiday";
        public static final String COL_DAY_OFFSET = "dayOffset";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s)), " +
                        "%s INTEGER(1))",
                TAB_NAME,
                _ID,
                COL_ID_HOLIDAY, Holidays.TAB_NAME, Holidays._ID,
                COL_DAY_OFFSET);
    }

    public interface MonthFloatHolidays extends BaseColumns {
        public static final String TAB_NAME = "t_MonthFloatHolidays";

        public static final String COL_ID_HOLIDAY = "id_holiday";
        public static final String COL_MONTH = "month";
        public static final String COL_WEEK_DAY = "weekDay";
        public static final String COL_DAY_OFFSET = "dayOffset";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s)), " +
                        "%s INTEGER, " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL)",
                TAB_NAME,
                _ID,
                COL_ID_HOLIDAY, Holidays.TAB_NAME, Holidays._ID,
                COL_MONTH,
                COL_WEEK_DAY,
                COL_DAY_OFFSET);
    }

    public interface YearFloatHolidays extends BaseColumns {
        public static final String TAB_NAME = "t_MonthFloatHolidays";

        public static final String COL_ID_HOLIDAY = "id_holiday";
        public static final String COL_DAY = "day";

        public static final String STMT_CREATE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s INTEGER NOT NULL REFERENCES %s(%s)), " +
                        "%s INTEGER NOT NULL, " +
                        "%s INTEGER NOT NULL)",
                TAB_NAME,
                _ID,
                COL_ID_HOLIDAY, Holidays.TAB_NAME, Holidays._ID,
                COL_DAY);
    }

}
