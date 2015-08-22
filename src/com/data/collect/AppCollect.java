package com.data.collect;

import android.content.Context;

import com.data.file.FileState;
import com.data.file.ThreadFile;
import com.data.info.AppTimeInfo;
import com.data.info.PhoneTimeInfo;
import com.data.main.CollectService;

import java.sql.Time;
import java.util.Timer;

/**
 * Created by wang on 15-1-9.
 */
public class AppCollect {

    private Context context;
    private final static int TIME = 2000;

    public AppCollect(Context context) {
        this.context = context;
    }

    public void getAppCollect() {
        AppTimeInfo api = new AppTimeInfo(context);
        CollectService.handler.postDelayed(api, TIME);
    }

    public static void getAppInstallorunInstallDatas(String data) {

        ThreadFile tf = new ThreadFile(data, FileState.appfile);
        CollectService.handler.post(tf);
    }
}
