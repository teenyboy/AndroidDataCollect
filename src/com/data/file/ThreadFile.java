package com.data.file;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by wang on 15-1-10.
 */
public class ThreadFile implements Runnable {

    private final static String TAG = "ThreadFile";
    File file;
    String data;

    public ThreadFile(String data, File file) {
        this.file = file;
        this.data = data;
    }

    @Override
    public synchronized void run() {

        Log.d(TAG, data);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true), 1024);
            bw.append(data);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
