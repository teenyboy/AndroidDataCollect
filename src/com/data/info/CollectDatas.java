package com.data.info;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by wang on 15-1-1.
 */
public class CollectDatas {

    Context context;
    private final static String TAG = "CollectDatas";
    private final static String device_os_version = "os_version";
    private final static String device_platform = "platform";
    private final static String device_language = "language";
    private final static String device_Id = "deviceId";
    private final static String device_size = "size";
    private final static String device_mobileornot = "mobileornot";
    // private final static String device_phone_type = "phone_type";
    private final static String device_IMEI = "IMEI";
    private final static String device_WIFIor2G3G = "WIFIor2G3G";
    private final static String device_time = "time";
    //  private final static String device_version = "version";
    private final static String device_mccmnc = "mccmnc";
    private final static String device_cellId = "cellId";
    private final static String device_lac = "lac";
    private final static String device_moduleName = "moduleName";
    private final static String device_DeviceName = "DeviceName";
    private final static String device_wifimac = "wifimac";
    private final static String device_havebluetooth = "havebluetooth";
  //  private final static String device_havewifi = "havewifi";
    private final static String device_havegps = "havegps";
    private final static String device_havegravity = "havegravity";
    private final static String device_latitude = "latitude";
    private final static String device_Longitude = "Longitude";
    private final static String device_MNC = "MNC";

    private String MNC;
    private String mccmnc;

    public CollectDatas(Context context) {
        this.context = context;
    }

    public HashMap<String, String> nochange_infoMap = new HashMap<String, String>();
    public HashMap<String, String> change_infoMap = new HashMap<String, String>();


    public boolean getNoChangeCollectDatasMap() {

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().
                getMetrics(displayMetrics);
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        LocationCell locationCell = new LocationCell();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if(CollectUtil.getCell(context)){
            mccmnc = String.valueOf(locationCell.getMCCMNC());
            MNC = CollectUtil.getMNC(mccmnc);
        }else {
            mccmnc = "null";
        }

        //�ɼ��������ݷ���HashMap��

        nochange_infoMap.put(device_os_version, CollectUtil.getOSVersion(context));
        nochange_infoMap.put(device_platform, "Android");
        nochange_infoMap.put(device_language, Locale.getDefault().getLanguage());
        nochange_infoMap.put(device_Id, CollectUtil.getPhoneId(context));
        nochange_infoMap.put(device_size, displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        nochange_infoMap.put(device_mobileornot, String.valueOf(true));
        //  nochange_infoMap.put(device_phone_type, String.valueOf(tm.getPhoneType()));
        nochange_infoMap.put(device_IMEI, tm.getSubscriberId());
        //   nochange_infoMap.put(device_version, CollectUtil.getVersion(context));
        nochange_infoMap.put(device_mccmnc, mccmnc);
        nochange_infoMap.put(device_moduleName, Build.PRODUCT);
        nochange_infoMap.put(device_DeviceName, CollectUtil.getDeviceName());
        nochange_infoMap.put(device_havebluetooth, String.valueOf(bluetoothAdapter == null ? false : true));
   //     nochange_infoMap.put(device_havewifi, String.valueOf(CollectUtil.currentNetworkIsWifi(context)));
        nochange_infoMap.put(device_havegps, String.valueOf(locationManager == null ? false : true));
        nochange_infoMap.put(device_havegravity, String.valueOf(CollectUtil.isHaveGravity(context)));
        nochange_infoMap.put(device_MNC,MNC);

        return true;
    }
}