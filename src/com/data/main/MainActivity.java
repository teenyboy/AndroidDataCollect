package com.data.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {


    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "--MainActivity onCreate--");

        Intent intent = new Intent(this, CollectService.class);
        startService(intent);

       finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "--MainActivity onResume--");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "--MainActivity onPause--");

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.v(TAG, "--MainActivity onStart--");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        Log.d(TAG, "--MainActivity onDestory--");
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        Log.d(TAG, "--MainActivity onstop--");
        super.onStop();
    }

}
