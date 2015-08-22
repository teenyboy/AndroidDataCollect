package com.data.main;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.data.R;
import com.data.collect.MainCollect;
import com.data.contentobserver.SMSObserver;
import com.data.info.CollectUtil;

/**
 * Created by wang on 15-1-1.
 */

public class CollectService extends Service {

    private final static String TAG = "CollectService";
    private final static int TIME = 1000;

    public static Handler handler;
    private static String TodayDate;


    //test
    int[] count = new int[2];

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "--CollectService onCreate--");

        Notification notification = new Notification(R.drawable.tornado_icon_48, "collecting", System.currentTimeMillis());
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, "DataCollect", "running", pi);
        startForeground(1, notification);
        TodayDate = CollectUtil.getDate();

        handler = new Handler();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "--CollectService onDestory--");

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        getContentResolver().registerContentObserver(Uri.parse("content://sms"), true, new SMSObserver(this,handler));

//        for(int i = 0;i<10;i++){
//            count[i] = i;
//        }
        MainCollect mainCollect = new MainCollect(this);
        mainCollect.getFileCollect();

        return START_STICKY;
    }
}