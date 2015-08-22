package com.data.info;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.data.main.MainApplication;

import java.util.HashMap;
import java.util.List;

public class AppInfo {

	private Drawable app_icon;
	private String app_name;
	private String package_name;
    private static HashMap<String,String> installedMap = new HashMap<String, String>();

	public Drawable getIcon() {
		return app_icon;
	}

	public void setIcon(Drawable app_icon) {
		this.app_icon = app_icon;
	}

	public String getAppName() {
		return app_name;
	}

	public void setAppName(String app_name) {
		this.app_name = app_name;
	}

	public String getPackageName() {
		return package_name;
	}

	public void setPackageName(String package_name) {
		this.package_name = package_name;
	}

    public void getInstalledList(){

        PackageManager packageManager= MainApplication.getmContext().getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);


        for(int i = 0;i<packages.size();i++){
            PackageInfo packageInfo = packages.get(i);
            AppInfo appInfo = new AppInfo();
            appInfo.app_name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            appInfo.package_name = packageInfo.packageName;

            installedMap.put(appInfo.package_name,appInfo.app_name);

        }
    }

    public static HashMap<String, String> getInstalledHashMap(){
        return installedMap;
    }

}
