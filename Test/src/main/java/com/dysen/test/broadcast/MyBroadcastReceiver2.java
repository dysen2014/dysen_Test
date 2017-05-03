package com.dysen.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dysen.lib.util.LogUtils;

/**
 * Created by dy on 2016/12/23.
 */

public class MyBroadcastReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        LogUtils.i("收到消息2："+msg);

//        abortBroadcast();//截断
        Bundle bundle = getResultExtras(true);
        String test = bundle.getString("test");
        LogUtils.i("收到广播处理的数据2："+msg);
    }
}
