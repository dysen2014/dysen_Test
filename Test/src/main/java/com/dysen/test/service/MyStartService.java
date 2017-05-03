package com.dysen.test.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.dysen.lib.util.LogUtils;

public class MyStartService extends Service {
    public MyStartService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.i("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.i("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        LogUtils.i("onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        LogUtils.i("onBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
