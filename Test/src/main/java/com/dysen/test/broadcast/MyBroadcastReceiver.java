package com.dysen.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dysen.lib.util.LogUtils;

/**
 * Created by dy on 2016/12/23.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String msg = intent.getStringExtra("msg");
        LogUtils.i("收到消息："+msg);

//        abortBroadcast();//截断

        Bundle bundle = new Bundle();
        bundle.putString("test", "广播处理的数据");
        setResultExtras(bundle);
    }
}
