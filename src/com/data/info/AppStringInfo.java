package com.data.info;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by wang on 15-1-12.
 */
public class AppStringInfo {

    private final static String TAG = "AppStringInfo";

    public static String getAppStringInfo(Context context, String packageName, int action) {

        StringBuffer sb = new StringBuffer();
        Double device_latitude;
        Double device_Longitude;
        LatitudeAndLongitude lal = new LatitudeAndLongitude();
        //CollectUtil.CollectLatitudeAndLongitude cc = new CollectUtil().new CollectLatitudeAndLongitude();
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        long Time = System.currentTimeMillis();
        //    if (cc.getGPSLocation(context)) {
        //得到位置
        // cc.getNetworkLocation(context);
        //  cc.getGPSLocation(context);
        device_latitude = lal.getLatitude();
        device_Longitude = lal.getLongitude();

        //      System.out.println("device_latitude" + device_latitude);
        //    System.out.println("device_Longitude" + device_Longitude);
        //}
        String IMSI = tm.getSubscriberId();

        sb.append(Time + "-|-|" + device_latitude + "-|-|" + device_Longitude + "-|-|" + IMSI + "-|-|" + packageName + "-|-|" + action + "\n");
        //  Log.d(TAG, "applog is " + String.valueOf(sb));

        return String.valueOf(sb);
    }
}
