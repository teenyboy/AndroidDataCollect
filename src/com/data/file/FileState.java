package com.data.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.data.info.CollectUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by wang on 15-1-9.
 */
public class FileState {

    private final static String TAG = "FileState";
    public static File appfile;
    public static File configFile;
    public static File usrStateFile;
    public static File browserBookmarkFile;
    public static File browserHistoryFile;
    public static File phoneFile;
    public static File logFile;

    public void makeFileDir(Context context) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && CollectUtil.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE"))
            ;
        File fileDir = new File(FilePath.filePath);
        if (fileDir.exists()) {
            Log.d(TAG, "fileDir has existed");
        } else {
            fileDir.mkdir();
            Log.d(TAG, "fileDir is created");
        }

        makeAppFile();
        makeConfigFile();
        makeUsrStateFile();
        makeBrowserBookmarkFile();
        makeBrowserHistoryFile();
        makePhoneFile();

        Log.d(TAG, "every files has existed!");

    }

    //create appFile
    public void makeAppFile() {
        appfile = new File(FilePath.appFile);
        if (appfile.exists()) {
            Log.d(TAG, "appFile has existed");
        } else {
            try {
                appfile.createNewFile();
                Log.d(TAG, "appFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "appFile create fail");
            }
        }
    }

    //delete appFile
    public void deleteAppFile() {
        if (appfile.exists()) {
            appfile.delete();
            Log.d(TAG, "appfile has deleted");
        } else {
            Log.d(TAG, "no appfile");
        }
    }

    //create configFile
    public void makeConfigFile() {
        configFile = new File(FilePath.configFile);
        if (configFile.exists()) {
            Log.d(TAG, "configFile has existed");
        } else {
            try {
                configFile.createNewFile();
                Log.d(TAG, "configFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "configFile create fail");
            }
        }
    }

    //delete configFile
    public void deleteConfigFile() {
        if (configFile.exists()) {
            configFile.delete();
            Log.d(TAG, "configFile has deleted");
        } else {
            Log.d(TAG, "no configFile");
        }
    }

    //create usrState_File
    public void makeUsrStateFile() {
        usrStateFile = new File(FilePath.usrStateFile);
        if (usrStateFile.exists()) {
            Log.d(TAG, "usrStateFile has existed");
        } else {
            try {
                usrStateFile.createNewFile();
                Log.d(TAG, "usrStateFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "usrStateFile create fail");
            }
        }
    }

    //delete usrStateFile
    public void deleteUsrStateFile() {
        if (usrStateFile.exists()) {
            usrStateFile.delete();
            Log.d(TAG, "usrStateFile has deleted");
        } else {
            Log.d(TAG, "no usrStateFile");
        }
    }

    //create browserBookmarkFile
    public void makeBrowserBookmarkFile() {
        browserBookmarkFile = new File(FilePath.browserBookmarkFile);
        if (browserBookmarkFile.exists()) {
            Log.d(TAG, "browserBookmarkFile has existed");
        } else {
            try {
                browserBookmarkFile.createNewFile();
                Log.d(TAG, "browserBookmarkFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "browserBookmarkFile create fail");
            }
        }

    }

    //delete browserBookmarkFile
    public void deleteBrowserBookmarkFile() {
        if (browserBookmarkFile.exists()) {
            browserBookmarkFile.delete();
            Log.d(TAG, "browserBookmarkFile has deleted");
        } else {
            Log.d(TAG, "no browserBookmarkFile");
        }
    }

    //create browserHistoryFile
    public void makeBrowserHistoryFile() {

        browserHistoryFile = new File(FilePath.browserHistoryFile);
        if (browserHistoryFile.exists()) {
            Log.d(TAG, "browserHistoryFile has existed");
        } else {
            try {
                browserHistoryFile.createNewFile();
                Log.d(TAG, "browserHistoryFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "browserHistoryFile create fail");
            }
        }

    }

    //delete browserHistoryFile
    public void deleteBrowserHistorykFile() {
        if (browserHistoryFile.exists()) {
            browserHistoryFile.delete();
            Log.d(TAG, "browserHistoryFile has deleted");
        } else {
            Log.d(TAG, "no browserHistoryFile");
        }
    }

    //create phoneFile
    public void makePhoneFile() {

        phoneFile = new File(FilePath.phoneFile);
        if (phoneFile.exists()) {
            Log.d(TAG, "phoneFile has existed");
        } else {
            try {
                phoneFile.createNewFile();
                Log.d(TAG, "phoneFile is created");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "phoneFile create fail");
            }
        }
    }

    //delete phoneFile
    public void deletePhoneFile() {
        if (phoneFile.exists()) {
            phoneFile.delete();
            Log.d(TAG, "phoneFile has deleted");
        } else {
            Log.d(TAG, "no phoneFile");
        }
    }

}
