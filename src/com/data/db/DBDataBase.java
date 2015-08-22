package com.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 15-1-25.
 */
public class DBDataBase {
    private static SQLiteOpenHelper sqLiteOpenHelper;

    public static SQLiteDatabase getDBDataBase(Context context) {

        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new DataDBHelper(context, "FileData.db", null, 1);
        }

        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        return sqLiteDatabase;
    }

    public static Cursor getDBCursor(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM FileData", null);
        return cursor;
    }

}
