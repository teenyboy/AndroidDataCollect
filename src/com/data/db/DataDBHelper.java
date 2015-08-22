package com.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.data.info.CollectDatas;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wang on 15-1-6.
 */
public class DataDBHelper extends SQLiteOpenHelper {

    private final static String TAG = "DataDBHelper";
    private static String Create_Data;

    public DataDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        Create_Data = "create table FileData ("+"id integer primary key autoincrement,"+"configFile text,"+"appFile text,"+"usrStateFile text,"+"browserBookmarkFile text,"+"browserHistoryFile text,"+"phoneFile text)";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Data");
        onCreate(db);
    }
}
