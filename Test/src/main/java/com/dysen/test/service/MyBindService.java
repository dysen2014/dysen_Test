package com.dysen.test.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;

import com.dysen.lib.util.LogUtils;

public class MyBindService extends Service {
    public MyBindService() {
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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        LogUtils.i("onBind");
        return new MyBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        LogUtils.i("unbindService");
    }

    @Override
    public void onDestroy() {
        LogUtils.i("onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder{

        public MyBindService getService(){
            LogUtils.i("");
            return MyBindService.this;
        }
    }

    public void play(){
        LogUtils.i("1234567889");
    }

}
