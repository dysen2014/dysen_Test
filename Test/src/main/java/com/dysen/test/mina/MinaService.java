package com.dysen.test.mina;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;

/**
 * Created by dy on 2016-12-05.
 */

public class MinaService extends Service {

    ConnectionThread connectionThread;
    @Override
    public void onCreate() {
        super.onCreate();

        connectionThread = new ConnectionThread("mina", getApplicationContext());
        connectionThread.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        connectionThread.disConnection();
        connectionThread = null;
    }

    /**
     * 负责调用 connection manager 类来完成与服务器的链接
     */
    class ConnectionThread extends HandlerThread{

        private Context context;
        boolean isConnection;
        ConnectManager connectManager;

        public ConnectionThread(String name, Context context){
            super(name);
            this.context = context;
            ConnectionConfig connectionConfig = new ConnectionConfig.Builder(context)
                    .setIp("192.168.0.12")
                    .setPort(9001)
                    .setReadBufferSize(10240)
                    .setConnetionTime(10000).Builder();
        }

        /**
         * 开始链接我们的服务器
         */
        @Override
        protected void onLooperPrepared() {
            for (; ;){
                //完成服务器的连接
                isConnection = connectManager.connect();
                if (isConnection){
                    break;
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void disConnection(){
            //完成服务器的断开
            connectManager.disConnect();
        }
    }
}
