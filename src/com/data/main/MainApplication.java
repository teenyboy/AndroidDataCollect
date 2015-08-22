package com.data.main;

import android.app.Application;
import android.content.Context;
import com.data.log.CrashHandler;

/**
 * Created by hao on 2015/4/3.
 */
public class MainApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        CrashHandler crashHandler = CrashHandler.getINSTANCE();
        crashHandler.init(mContext);
    }

    public static Context getmContext() {
        return mContext;
    }
}
