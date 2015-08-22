package com.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.data.file.FilePath;

import java.util.ArrayList;

/**
 * Created by wang on 15-1-7.
 */
public class DataContentValue {

    private final static String TAG = "DataContentValue";
    static Context context;


    public DataContentValue(Context context) {
        this.context = context;
    }

    public static ContentValues getFileContentValues() {

        ContentValues cv = new ContentValues();
        cv.put("configFile",FilePath.configFile);
        cv.put("appFile",FilePath.appFile);
        cv.put("usrStateFile", FilePath.usrStateFile);
        cv.put("browserBookmarkFile", FilePath.browserBookmarkFile);
        cv.put("browserHistoryFile", FilePath.browserHistoryFile);
        cv.put("phoneFile", FilePath.phoneFile);
        Log.d(TAG, FilePath.configFile);
        Log.d(TAG, FilePath.appFile);
        Log.d(TAG, FilePath.usrStateFile);
        Log.d(TAG, FilePath.browserBookmarkFile);
        Log.d(TAG, FilePath.browserHistoryFile);
        Log.d(TAG, FilePath.phoneFile);
        return cv;
    }

    public static ContentValues getFileContentValuesTest() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-04-20_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-04-20_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-04-20_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-04-20_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-04-20_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-04-20_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest1() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-01-25_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-01-25_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-01-25_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-01-25_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-01-25_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-01-25_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest2() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-04-03_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-04-03_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-04-03_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-04-03_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-04-03_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-04-03_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest3() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-01-23_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-01-23_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-01-23_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-01-23_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-01-23_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-01-23_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest4() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-04-02_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-04-02_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-04-02_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-04-02_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-04-02_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-04-02_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest5() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-04-03_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-04-03_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-04-03_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-04-03_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-04-03_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-04-03_phoneFile.txt");
        return cv;
    }

    public static ContentValues getFileContentValuesTest6() {
        ContentValues cv = new ContentValues();
        cv.put("configFile", "/storage/emulated/0/dataCollectCache/2015-01-31_configFile.txt");
        cv.put("appFile", "/storage/emulated/0/dataCollectCache/2015-01-31_appFile.txt");
        cv.put("usrStateFile", "/storage/emulated/0/dataCollectCache/2015-01-31_usrStateFile.txt");
        cv.put("browserBookmarkFile", "/storage/emulated/0/dataCollectCache/2015-01-31_browserBookmarkFile.txt");
        cv.put("browserHistoryFile", "/storage/emulated/0/dataCollectCache/2015-01-31_browserHistoryFile.txt");
        cv.put("phoneFile", "/storage/emulated/0/dataCollectCache/2015-01-31_phoneFile.txt");
        return cv;
    }


    //更新数据库
    public static ArrayList<ContentValues> getCursorContentValues(Cursor cursor) {

        ArrayList<ContentValues> contentValuesArrayList = new ArrayList<ContentValues>();
        int contentValueCount = 0;

        while (cursor.moveToNext()) {
            ContentValues cv = new ContentValues();
            cv.put("configFile", cursor.getString(cursor.getColumnIndex("configFile")));
            cv.put("appFile", cursor.getString(cursor.getColumnIndex("appFile")));
            cv.put("usrStateFile", cursor.getString(cursor.getColumnIndex("usrStateFile")));
            cv.put("browserBookmarkFile", cursor.getString(cursor.getColumnIndex("browserBookmarkFile")));
            cv.put("browserHistoryFile", cursor.getString(cursor.getColumnIndex("browserHistoryFile")));
            cv.put("phoneFile", cursor.getString(cursor.getColumnIndex("phoneFile")));
            contentValuesArrayList.add(contentValueCount, cv);
            contentValueCount++;
        }
        return contentValuesArrayList;
    }


//    public ContentValues getNoChangeContentValues() {
//
//        ContentValues cv = new ContentValues();
//        CollectDatas collectDatas = new CollectDatas(context);
//        collectDatas.getNoChangeCollectDatasMap();
//        Set set = collectDatas.nochange_infoMap.entrySet();
//        Iterator iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry mapEntry = (Map.Entry) iterator.next();
//            cv.put(String.valueOf(mapEntry.getKey()), String.valueOf(mapEntry.getValue()));
//        }
//        //定量数据从HashMap中拿出放入ContentValues
//
//        return cv;
//    }
//
//
//    public ContentValues getChangeContentValues() {
//
//        ContentValues cv = new ContentValues();
//        CollectDatas collectDatas = new CollectDatas(context);
////        collectDatas.getChangeCollectDatasMap();
//        Set set = collectDatas.change_infoMap.entrySet();
//        Iterator iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//            Map.Entry mapEntry = (Map.Entry) iterator.next();
//            cv.put(String.valueOf(mapEntry.getKey()), String.valueOf(mapEntry.getValue()));
////            Log.d(TAG, "content is" + mapEntry.getKey());
////            Log.d(TAG, "value is" + mapEntry.getValue());
////            cv.put("content", String.valueOf(mapEntry.getKey()));
////            cv.put("value", String.valueOf(mapEntry.getValue()));
////            cv.put("time", "1");
//        }
//        //变量数据从HashMap中拿出放入ContentValues
//
//        return cv;
//    }

}
