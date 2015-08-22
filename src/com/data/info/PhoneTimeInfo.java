package com.data.info;

import android.content.Context;
import android.os.Looper;

import java.util.TimerTask;

/**
 * Created by wang on 15-1-17.
 */
public class PhoneTimeInfo extends TimerTask {

    Context context;
    CollectUtil collectUtil;

    public PhoneTimeInfo(Context context) {
        this.context = context;
        collectUtil = new CollectUtil();
    }

    @Override
    public void run() {
        collectUtil.getPhoneState(context);
    }
}
