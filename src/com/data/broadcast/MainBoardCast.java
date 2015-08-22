package com.data.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.data.collect.MainCollect;

/**
 * Created by wang on 15-1-9.
 */
public class MainBoardCast extends BroadcastReceiver {
    private final static String TAG = "MainBoardCast";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
            System.out.println("unlock");
        }

        //监听日期变换
        if(Intent.ACTION_DATE_CHANGED.equals(intent.getAction())){

            MainCollect mainCollect = new MainCollect(context);
            mainCollect.getFileCollect();
        }

    }
}
