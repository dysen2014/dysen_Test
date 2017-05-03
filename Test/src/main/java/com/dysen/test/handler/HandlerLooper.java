package com.dysen.test.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;


public class HandlerLooper extends BaseActivity {

    TextView text;
    Handler handler;
    HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        text = new TextView(this);
        text.setText("handler thread");

        setContentView(text);

        handlerThread = new HandlerThread("hanler thread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            //这里是在  子线程中操作   适合 耗时操作
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                LogUtils.i("current thread" + Thread.currentThread());

            }
        };
        handler.sendEmptyMessage(1);
    }
}
