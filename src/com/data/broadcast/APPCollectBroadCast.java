package com.data.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.data.collect.AppCollect;
import com.data.configure.AppConfig;
import com.data.info.AppInfo;
import com.data.info.AppStringInfo;
import com.data.main.MainApplication;

public class APPCollectBroadCast extends BroadcastReceiver {

    private final String TAG = "CollectReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "enter Receive");

        PackageManager pm = MainApplication.getmContext().getPackageManager();
        ApplicationInfo info;

        //监听软件安装
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String addPackageName = intent.getDataString();

            String packageName[] = addPackageName.split(":");

            addPackageName = packageName[1];

            String addPackageNameInfo = null;
            try {
                info = pm.getApplicationInfo(addPackageName, 0);
                addPackageNameInfo = (String) pm.getApplicationLabel(info);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            AppInfo.getInstalledHashMap().put(addPackageName,addPackageNameInfo);

            String installInfo = AppStringInfo.getAppStringInfo(context, addPackageNameInfo, AppConfig.INSTALL);
            AppCollect.getAppInstallorunInstallDatas(installInfo);
        }
        //监听软件卸载
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            String removePackageName = intent.getDataString();

            String packageName[] = removePackageName.split(":");

            removePackageName = packageName[1];

            String removePackageNameInfo = AppInfo.getInstalledHashMap().get(removePackageName);

//            String removePackageNameInfo = null;
//            try {
//                info = pm.getApplicationInfo(removePackageName, PackageManager.GET_META_DATA);
//                removePackageNameInfo = (String) pm.getApplicationLabel(info);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }


            String uninstallInfo = AppStringInfo.getAppStringInfo(context, removePackageNameInfo, AppConfig.UNINSTALL);
            AppCollect.getAppInstallorunInstallDatas(uninstallInfo);
        }
        //监听软件升级
        if (intent.getAction().equals("android.intent.action.PACKAGE_REPLACED")) {
            String updatePackageName = intent.getDataString();

            String packageName[] = updatePackageName.split(":");

            updatePackageName = packageName[1];

            String updatePackageNameInfo = null;
            try {
                info = pm.getApplicationInfo(updatePackageName, 0);
                updatePackageNameInfo = (String) pm.getApplicationLabel(info);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            String updateInfo = AppStringInfo.getAppStringInfo(context, updatePackageNameInfo, AppConfig.UPDATE);
            AppCollect.getAppInstallorunInstallDatas(updateInfo);
        }
    }

}
