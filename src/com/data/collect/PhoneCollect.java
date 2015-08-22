package com.data.collect;

import android.content.Context;
import com.data.file.FileState;
import com.data.file.ThreadFile;
import com.data.info.PhoneTimeInfo;
import com.data.main.CollectService;

/**
 * Created by root on 15-1-26.
 */
public class PhoneCollect {

    Context context;

    public PhoneCollect(Context context) {
        this.context = context;
    }

    public void getPhoneCollect() {
        PhoneTimeInfo pti = new PhoneTimeInfo(context);
        CollectService.handler.post(pti);
    }

    public static void getPhoneDatas(String data) {
        ThreadFile tf = new ThreadFile(data, FileState.phoneFile);
        CollectService.handler.post(tf);
    }
}
