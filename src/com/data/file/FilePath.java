package com.data.file;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import com.data.info.CollectDatas;
import com.data.info.CollectUtil;
import com.data.main.MainActivity;
import com.data.main.MainApplication;

import java.io.File;

/**
 * Created by wang on 15-1-9.
 */
public class FilePath extends Application{


    public final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/";
    public final static String configFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() +"_configFile"+ ".txt";
    public final static String appFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_appFile" + ".txt";
    public final static String usrStateFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_usrStateFile" + ".txt";
    public final static String browserBookmarkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_browserBookmarkFile" + ".txt";
    public final static String browserHistoryFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_browserHistoryFile" + ".txt";
    public final static String phoneFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_phoneFile" + ".txt";
    public final static String logFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/dataCollectCache/"+CollectUtil.getDate()+"_throw"+".log";

//    private String filePath;
//    private String configFile;
//    private String appFile;
//    private String usrStateFile;
//    private String browserBookmarkFile;
//    private String browserHistoryFile;
//    private String phoneFile;
//    private String logFile;
//
//    public FilePath(Context context) {
//
//        collectDatas = new CollectDatas(context);
//
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        IMEI = tm.getSubscriberId();
//    }
//
//    public String getFilePath() {
//        return filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/";
//    }
//
//    public String getConfigFile() {
//        return configFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_configFile_" + IMEI + ".txt";
//    }
//
//    public String getAppFile() {
//        return appFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_appFile_" + IMEI + ".txt";
//    }
//
//    public String getUsrStateFile() {
//        return usrStateFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_usrStateFile_" + IMEI + ".txt";
//    }
//
//    public String getBrowserBookmarkFile() {
//        return browserBookmarkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_browserBookmarkFile_" + IMEI + ".txt";
//    }
//
//    public String getBrowserHistoryFile() {
//        return browserHistoryFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_browserHistoryFile_" + IMEI + ".txt";
//    }
//
//    public String getPhoneFile() {
//        return phoneFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_phoneFile_" + IMEI + ".txt";
//    }
//
//    public String getLogFile() {
//        return logFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataCollectCache/" + CollectUtil.getDate() + "_" + IMEI + "_throw" + ".log";
//    }
}

