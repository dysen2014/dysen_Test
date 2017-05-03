package com.dysen.test.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class BroadcastDemo extends BaseActivity implements View.OnClickListener{

    Button btnDisOrdered, btnOrdered, btnAsycn;
    private MyBroadcastReceiver3 myBroadcastReceiver3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        init();
        logic();
    }


    @Override
    protected void logic() {
        super.logic();

        btnDisOrdered.setOnClickListener(this);
        btnOrdered.setOnClickListener(this);
        btnAsycn.setOnClickListener(this);
    }

    @Override
    protected void init() {
        super.init();

        btnDisOrdered = bindView(R.id.btn_dis_ordered_broadcast);
        btnOrdered = bindView(R.id.btn_ordered_broadcast);
        btnAsycn = bindView(R.id.btn_async_broadcast);

        /**
         * 动态注册 广播 (比静态注册优先接收)
         */
//        IntentFilter intentFilter = new IntentFilter("com.dysen");
//        MyBroadcastReceiver2 myBroadcastReceiver2 = new MyBroadcastReceiver2();
//        registerReceiver(myBroadcastReceiver2, intentFilter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_dis_ordered_broadcast:
                sendDisOrdered();
                break;
            case R.id.btn_ordered_broadcast:
                sendOrdered();
                break;
            case R.id.btn_async_broadcast:
                sendSticky();
                break;
        }
    }

    /**
     * 发送无序广播
     */
    private void sendDisOrdered() {

        Intent intent = new Intent();
        intent.putExtra("msg", "这是一条无序广播");
        intent.setAction("com.dysen");

        sendBroadcast(intent);
    }

    /**
     * 发送有序广播
     */
    private void sendOrdered() {

        Intent intent = new Intent();
        intent.putExtra("msg", "这是一条有序广播");
        intent.setAction("com.dysen");

        sendOrderedBroadcast(intent, null);
    }
    /**
     * 发送广播
     */
    private void sendSticky() {

        Intent intent = new Intent();
        intent.putExtra("msg", "这是一条异步广播");
        intent.setAction("com.dysen.3");

        sendStickyBroadcast(intent);

        /**
         * 动态注册 广播 (比静态注册优先接收)
         */
        IntentFilter intentFilter = new IntentFilter("com.dysen.3");
        myBroadcastReceiver3 = new MyBroadcastReceiver3();
        registerReceiver(myBroadcastReceiver3, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //动态注册 广播 一定记得销毁
        unregisterReceiver(myBroadcastReceiver3);
    }
}
