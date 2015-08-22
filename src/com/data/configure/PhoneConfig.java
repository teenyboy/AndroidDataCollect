package com.data.configure;

/**
 * Created by root on 15-1-26.
 */
public class PhoneConfig {

    //电话操作 1-拨号 2挂断 3来电 4通话中 5短信到来 6发送短信
    public final static int PHONE_CALL = 1;
    public final static int PHONE_DISCONNECT = 2;
    public final static int PHONE_RING = 3;
    public final static int PHONE_OFFHOOK = 4;
    public final static int PHONE_RECEIVED_SMS = 5;
    public final static int PHONE_SEND_SMS = 6;
    public final static String PHONE = "PHONE";

    public static int sms_id;
}
