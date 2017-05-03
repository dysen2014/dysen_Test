package com.dysen.test.mina;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class MinaActivity extends BaseActivity implements View.OnClickListener{

    //自定义一个广播接收器
    private MessageBroadcastReceicver receiver = new MessageBroadcastReceicver();
    TextView txtStart, txtSend, txtReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mina);

        init();
        registerBroadcast();
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter("com.dysen.test.broadcast");
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver, filter);
    }

    private void unregisterBroadcast(){

        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(receiver);
    }

    @Override
    protected void init() {
        super.init();

        txtStart = bindView(R.id.txt_start);
        txtSend = bindView(R.id.txt_send);
        txtReceive = bindView(R.id.txt_receive);

        txtStart.setOnClickListener(this);
        txtReceive.setOnClickListener(this);
        txtSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.txt_start:
                Intent intent = new Intent(MinaActivity.this, MinaService.class);
                startService(intent);
            case R.id.txt_send:
                SessionManager.getInstance().write2Server("123");
                break;
            case R.id.txt_receive:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MinaService.class));
        unregisterBroadcast();
    }

    /**
     * 接收 mina 发过来的消息,并更新UI
     */
    private class MessageBroadcastReceicver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            setTitle(intent.getStringExtra("message"));
        }
    }
}
