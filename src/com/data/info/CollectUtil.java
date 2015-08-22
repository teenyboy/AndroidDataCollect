package com.data.info;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import com.data.collect.PhoneCollect;
import com.data.configure.Constants;
import com.data.configure.PhoneConfig;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectUtil {

    static Context collectUtilContext;

    /**
     * @return String
     * @Title: now
     * @Description: 当前时间
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * @return String
     * @Description:文件名:当天时间
     */
    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static String getIMEI(Context context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }

    /**
     * @return String
     * @throws
     * @Title: getTime
     * @Description: TODO
     */
    public static String now() {
        Time nowTime = new Time("Asia/chongqing");
        nowTime.setToNow();
        return nowTime.format("%Y%m%d");
    }

    /**
     * @param context
     * @return String
     */
    public static String getVersion(Context context) {
        if (context == null) {
            return "";
        }

        String versionName = "";

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * @param
     * @return String
     * @throws
     * @Title: getSDK
     * @Description: 得到当前收集的SDK
     */
    public static String getSDK() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * @param context
     * @return String
     * @throws
     * @Title: getActivityName
     * @Description: TODO
     */
    public static String getActivityName(Context context) {
        collectUtilContext = context;
        if (collectUtilContext == null) {
            return "";
        }
        ActivityManager am = (ActivityManager) collectUtilContext
                .getSystemService(collectUtilContext.ACTIVITY_SERVICE);
        if (checkPermission(collectUtilContext, "android.permission.GET_TASKS")) {
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            return cn.getShortClassName();
        }
        return "";
    }

    /**
     * @param context
     * @param permission
     * @return boolean
     * @Title: checkPermission
     * @Description: TODO
     */
    public static boolean checkPermission(Context context, String permission) {

        collectUtilContext = context;
        PackageManager pm = context.getPackageManager();
        return pm.checkPermission(permission,
                collectUtilContext.getPackageName()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @param context
     * @return List<PackageInfo>
     * @throws
     * @Title: getPackageInfo
     * @Description: TODO
     */
    public static List<PackageInfo> getPackageInfo(Context context) {

        List<PackageInfo> appList = new ArrayList<PackageInfo>();

        PackageManager mPackageManager = context.getPackageManager();

        List<PackageInfo> packageList = mPackageManager.getInstalledPackages(0);
        for (int i = 0; i < packageList.size(); i++) {
            PackageInfo pi = (PackageInfo) packageList.get(i);
            appList.add(pi);
        }
        return appList;
    }

    /**
     * @param context
     * @return String
     * @throws
     * @Title: getPhoneId
     * @Description: TODO
     */
    public static String getPhoneId(Context context) {
        if (context == null) {
            return "";
        }
        if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            String phoneId = "";
            if (checkPhoneState(context)) {
                TelephonyManager tm = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);
                phoneId = tm.getDeviceId();
                if (phoneId != null) {
                    return phoneId;
                }
            }
        }
        return "";
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer))
            return (capitial(model));
        else
            return capitial(manufacturer) + "" + model;
    }

    public static String getMNC(String IMSI) {

        String IMSIsub = IMSI.substring(3,5);
        if (IMSIsub.equals("01")) {
            return "CHINA_UNICOM";
        } else if (IMSIsub.equals("00")) {
            return "CHINA_MOBILE";
        } else if (IMSIsub.equals("03")) {
            return "CHINA_TELECOM";
        } else {
            return null;
        }
    }

    public static String capitial(String str) {
        if (str == "" || str.length() == 0)
            return "";
        char first = str.charAt(0);
        if (Character.isUpperCase(first))
            return str;
        else
            return Character.toUpperCase(first) + str.substring(1);
    }

    /**
     * @param context
     * @return Boolean
     * @throws
     * @Title: checkPhoneState
     * @Description: TODO
     */
    private static Boolean checkPhoneState(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm.checkPermission("android.permission.READ_PHONE_STATE",
                context.getPackageName()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * @param context
     * @return String
     * @throws
     * @Title: getOSVersion
     * @Description: TODO
     */
    public static String getOSVersion(Context context) {
        String OSVersion = "";
        if (checkPhoneState(context)) {
            OSVersion = android.os.Build.VERSION.RELEASE;
            return OSVersion;
        }
        return null;
    }

//    /**
//     * @param context
//     * @return String
//     */
//    public static String getAPPKey(Context context) {
//
//        if (context == null) {
//            return "";
//        }
//
//        String key_str = "";
//        try {
//            PackageManager pm = context.getPackageManager();
//            ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(), 128);
//            if (ai != null) {
//                String str = ai.metaData.getString("UMS_APPKEY");
//                if (str != null) {
//                    key_str = str;
//                    return key_str;
//                }
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /**
     * @param context
     * @return boolean
     * @throws
     * @Title: currentNetworkIsWifi
     * @Description: TODO
     */
    public static boolean currentNetworkIsWifi(Context context) {
        if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) ;
        Context contextApplication = context.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI") && info[i].isConnected())
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * @param context
     * @return boolean
     * @throws
     * @Title: isNetworkAvailable
     * @Description: TODO
     */
    public static boolean isNetworkAvailable(Context context) {
        if (checkPermission(context, "android.permission.INTERNET")) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isAvailable()) {
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * @param context
     * @return boolean
     * @throws
     * @Title: isHaveGravity
     * @Description: TODO
     */
    public static boolean isHaveGravity(Context context) {
        SensorManager sm = (SensorManager) context
                .getSystemService(context.SENSOR_SERVICE);
        if (sm == null)
            return false;
        return true;
    }

    /**
     * @param context
     * @return String
     * @throws
     * @Title: networkType
     * @Description: TODO
     */
    public static String networkType(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(context.TELEPHONY_SERVICE);
        int type = tm.getNetworkType();
        String typeString = "UNKNOWN";
        if (type == TelephonyManager.NETWORK_TYPE_CDMA) {
            typeString = "CDMA";
        }
        if (type == TelephonyManager.NETWORK_TYPE_EDGE) {
            typeString = "EDGE";
        }
        if (type == TelephonyManager.NETWORK_TYPE_EVDO_0) {
            typeString = "EVDO_0";
        }
        if (type == TelephonyManager.NETWORK_TYPE_EVDO_A) {
            typeString = "EVDO_A";
        }
        if (type == TelephonyManager.NETWORK_TYPE_GPRS) {
            typeString = "GPRS";
        }
        if (type == TelephonyManager.NETWORK_TYPE_HSDPA) {
            typeString = "HSDPA";
        }
        if (type == TelephonyManager.NETWORK_TYPE_HSPA) {
            typeString = "HSPA";
        }
        if (type == TelephonyManager.NETWORK_TYPE_HSUPA) {
            typeString = "HSUPA";
        }
        if (type == TelephonyManager.NETWORK_TYPE_UMTS) {
            typeString = "UMTS";
        }
        if (type == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
            typeString = "UNKNOWN";
        }
        if (type == TelephonyManager.NETWORK_TYPE_1xRTT) {
            typeString = "1xRTT";
        }
        if (type == 11) {
            typeString = "iDen";
        }
        if (type == 12) {
            typeString = "EVDO_B";
        }
        if (type == 13) {
            typeString = "LTE";
        }
        if (type == 14) {
            typeString = "eHRPD";
        }
        if (type == 15) {
            typeString = "HSPA+";
        }

        return typeString;
    }

    /**
     * @param context
     * @return boolean
     * @throws
     * @Title: getCell
     * @Description: TODO
     */
    public static boolean getCell(Context context) {
        LocationCell mLocationCell = new LocationCell();
        TelephonyManager mTelephonyManager = (TelephonyManager) context
                .getSystemService(context.TELEPHONY_SERVICE);
        CellLocation cellLocation = mTelephonyManager.getCellLocation();
        String operator = mTelephonyManager.getNetworkOperator();

        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation mGsmCellLocation = (GsmCellLocation) mTelephonyManager
                    .getCellLocation();
            mLocationCell.setMCC(Integer.parseInt(operator.substring(0, 3)));
            mLocationCell.setMNC(Integer.parseInt(operator.substring(3)));
            mLocationCell.setCID(mGsmCellLocation.getCid());
            mLocationCell.setLAC(mGsmCellLocation.getLac());
            mLocationCell.setMCCMNC(Integer.parseInt(operator));
        } else {
            mLocationCell.setMCC(Integer.parseInt(operator.substring(0, 3)));
            mLocationCell.setMNC(Integer.parseInt(operator.substring(3)));
            mLocationCell.setCID(0);
            mLocationCell.setLAC(0);
            mLocationCell.setMCCMNC(Integer.parseInt(operator));
        }

        if (cellLocation == null)
            return false;

        return true;
    }

    /**
     * @param context
     * @return String
     * @throws
     * @Title: getNetworkTypeWIFI2G3G
     * @Description: TODO
     */
    public static String getNetworkTypeWIFI2G3G(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        String mNetWorkType = null;
        if (ni != null && ni.isConnected()) {
            String type = ni.getTypeName();

            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = GetNetWorkType.NETWORKTYPE_WIFI;

            } else if (type.equalsIgnoreCase("MOBILE")) {
                String proxyHost = android.net.Proxy.getDefaultHost();

                mNetWorkType = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? GetNetWorkType.NETWORKTYPE_3G
                        : GetNetWorkType.NETWORKTYPE_2G)
                        : GetNetWorkType.NETWORKTYPE_WAP;
            }
        } else {
            mNetWorkType = GetNetWorkType.NETWORKTYPE_INVALID;
        }
        return mNetWorkType;
    }

    /**
     * @param context
     * @return boolean
     * @throws
     * @Title: isFastMobileNetwork
     * @Description: TODO
     */
    public static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }


    //得到电话状态
    public void getPhoneState(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new MyPhoneListener(context), PhoneStateListener.LISTEN_CALL_STATE);
    }

    public class CollectLatitudeAndLongitude {

        private final static String TAG = "CollectLatitudeAndLongitude";
        LatitudeAndLongitude mLatitudeAndLongitude = new LatitudeAndLongitude();
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//                if (location != null) {
//                    mLatitudeAndLongitude.setLatitude(location.getLatitude());
//                    mLatitudeAndLongitude.setLongitude(location.getLongitude());
//                }
                Double latitude = location.getLatitude();
                Double longtitude = location.getLongitude();
                //       Log.d(TAG, "latitude:" + latitude + ",longitude:" + longtitude);
                mLatitudeAndLongitude.setLatitude(latitude);
                mLatitudeAndLongitude.setLongitude(longtitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //   Criteria criteria = new Criteria();
        //LocationListener listener;
        public void getNetworkLocation(Context context) {
            LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 100, locationListener);
        }

        public void getGPSLocation(final Context context) {

//            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//            criteria.setAccuracy(Criteria.ACCURACY_FINE);
//            criteria.setAltitudeRequired(false);
//            criteria.setBearingRequired(false);
//            criteria.setCostAllowed(false);
//            criteria.setPowerRequirement(Criteria.POWER_LOW);
            LocationManager mLocationManager = (LocationManager) context
                    .getSystemService(context.LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, locationListener);
//            mLocationManager.setTestProviderEnabled("gps", true);
//            final String provider = mLocationManager.getBestProvider(criteria, true);
//            listener = new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//
//                    if (location != null) {
//                        mLatitudeAndLongitude.setLatitude(location.getLatitude());
//                        mLatitudeAndLongitude.setLongitude(location.getLongitude());
//                    }
//                    mLocationManager.removeUpdates(this);
//                    mLocationManager.setTestProviderEnabled(provider, true);
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            };
//
//            mLocationManager.requestLocationUpdates(provider, 1000, (float) 1000.0, listener);

//            List<String> matchingProviders = mLocationManager.getAllProviders();
//            for (String prociderString : matchingProviders) {
//                Location location = mLocationManager
//                        .getLastKnownLocation(prociderString);
//                if (location != null) {
//                    mLatitudeAndLongitude.setLatitude(location.getLatitude());
//                    mLatitudeAndLongitude.setLongitude(location.getLongitude());
//                    return true;
//                }
//                return false;
//            }
//            return true;
        }
//        public void onDestroy() {
//            mLocationManager.removeUpdates(listener);
//            mLocationManager.setTestProviderEnabled("gps", false);
//        }

    }

    class MyPhoneListener extends PhoneStateListener {

        private final static String TAG = "MyPhoneListener";
        private Context context;

        public MyPhoneListener(Context context) {
            this.context = context;
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            boolean flag = Constants.phoneFlag;

            switch (state) {
                //来电
                case TelephonyManager.CALL_STATE_RINGING:
                    if (flag) {
                        String RingingPhoneState = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_RING);
                        PhoneCollect.getPhoneDatas(RingingPhoneState);
                        Log.d(TAG, "call_state_ringing");
                        flag = !flag;
                    }
                    break;
                //挂断
                case TelephonyManager.CALL_STATE_IDLE:
                    if (flag) {
                        String IDLEPhoneState = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_DISCONNECT);
                        PhoneCollect.getPhoneDatas(IDLEPhoneState);
                        Log.d(TAG, "call_state_idle");
                        flag = !flag;
                    }
                    break;
                //通话中
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    if (flag) {
                        String OFFHook = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_OFFHOOK);
                        PhoneCollect.getPhoneDatas(OFFHook);
                        Log.d(TAG, "call_state_offhook");
                        flag = !flag;
                    }
                    break;
                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }


}
