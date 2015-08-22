package com.data.collect;

import android.content.Context;

import com.data.file.FileState;
import com.data.file.TimeThreadFile;
import com.data.info.CollectUtil;
import com.data.info.LatitudeAndLongitude;
import com.data.info.LocationCell;
import com.data.main.CollectService;

/**
 * Created by wang on 15-1-9.
 */
public class UsrStateCollect {

    private final static String TAG = "UsrStateCollect";
    Context context;
    private int TIME = 20000;

    public UsrStateCollect(Context context) {
        this.context = context;
    }

    public String getUsrStateCollect() {

        StringBuffer sb = new StringBuffer();
        Double device_latitude;
        Double device_Longitude;
        LatitudeAndLongitude lal = new LatitudeAndLongitude();
        LocationCell locationCell = new LocationCell();
        //  CollectUtil.CollectLatitudeAndLongitude cc = new CollectUtil().new CollectLatitudeAndLongitude();

        long Time = System.currentTimeMillis();
//        if (cc.getGPSLocation(context)) {

        //得到位置
        // cc.getGPSLocation(context);
        //cc.getNetworkLocation(context);
        device_latitude = lal.getLatitude();
        device_Longitude = lal.getLongitude();

        //      System.out.println("device_latitude" + device_latitude);
        //      System.out.println("device_Longitude" + device_Longitude);
//        }
        String device_netState = CollectUtil.getNetworkTypeWIFI2G3G(context);
        int device_cellId = locationCell.getCID();
        int device_lac = locationCell.getLAC();

        sb.append(Time + "-|-|" + device_latitude + "-|-|" + device_Longitude + "-|-|" + device_netState + "-|-|" + device_cellId + "-|-|" + device_lac + "\n");
        //   Log.d(TAG, "usr state is " + String.valueOf(sb));

        return String.valueOf(sb);
    }

    public void getUsrStateDatas() {

        TimeThreadFile tf = new TimeThreadFile(context, FileState.usrStateFile);

        CollectService.handler.postDelayed(tf, TIME);

    }

}
