package com.data.file;

import android.content.Context;

import com.data.collect.UsrStateCollect;
import com.data.main.CollectService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wang on 15-1-12.
 */
public class TimeThreadFile implements Runnable {

    private final static String TAG = "TimeThreadFile";
    File file;
    Context context;
    UsrStateCollect usc;
    String data;
    private final static int TIME = 5000;

    public TimeThreadFile(Context context, File file) {

        this.file = file;
        this.context = context;
        usc = new UsrStateCollect(context);
    }


    @Override
    public void run() {

        data = usc.getUsrStateCollect();



        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true), 1024);
            bw.append(data);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CollectService.handler.postDelayed(this, TIME);
    }
}
