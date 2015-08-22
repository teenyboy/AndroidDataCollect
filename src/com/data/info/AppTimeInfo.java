package com.data.info;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.data.collect.AppCollect;
import com.data.configure.AppConfig;
import com.data.main.CollectService;
import com.data.main.MainActivity;
import com.data.main.MainApplication;

import java.util.TimerTask;

/**
 * Created by wang on 15-1-12.
 */
public class AppTimeInfo extends TimerTask {

    private final static String TAG = "AppTimeInfo";
    private final static int TIME = 3000;
    private Context context;
    ActivityManager mActivityManager;
    String lastPackageName = null;
    String nowPackageName;
    String packageName;
    StringBuffer sb = new StringBuffer();
    CollectUtil collectUtil;

    public AppTimeInfo(Context context) {
        this.context = context;
        mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        collectUtil = new CollectUtil();
    }

    @Override
    public void run() {

        //  Looper.prepare();

        CollectUtil.CollectLatitudeAndLongitude cc = new CollectUtil().new CollectLatitudeAndLongitude();
        cc.getGPSLocation(context);
        cc.getNetworkLocation(context);

        //清空缓冲区
        sb.delete(0, sb.length());

//
//        ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;
//        nowPackageName = topActivity.getPackageName();
//
        PackageManager pm = MainApplication.getmContext().getPackageManager();

        ComponentName topActivity = mActivityManager.getRunningTasks(1).get(0).topActivity;
        packageName = topActivity.getPackageName();

        try {
            ApplicationInfo info = pm.getApplicationInfo(packageName,0);
            nowPackageName = (String)pm.getApplicationLabel(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //     Log.d(TAG, "nowPackageName" + nowPackageName);
        //    Log.d(TAG, "lastPackageName" + lastPackageName);

        if (nowPackageName.equals(lastPackageName)) {
            //       Log.d(TAG, "app no change");
        } else {
            if (lastPackageName == null) {
                lastPackageName = nowPackageName;
            } else {
                //        Log.d(TAG, "app change");
                sb.append(AppStringInfo.getAppStringInfo(context, lastPackageName, AppConfig.STOP));
                sb.append(AppStringInfo.getAppStringInfo(context, nowPackageName, AppConfig.START));
                AppCollect.getAppInstallorunInstallDatas(String.valueOf(sb));
                lastPackageName = nowPackageName;
            }
        }
        // Looper.loop();

        CollectService.handler.postDelayed(this, TIME);
    }
}
