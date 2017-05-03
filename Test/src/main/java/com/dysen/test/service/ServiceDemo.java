package com.dysen.test.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import com.dysen.test.service.MyBindService.MyBinder;
import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class ServiceDemo extends BaseActivity implements View.OnClickListener{

    MyBindService myBindService;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override//当启动源成功连接后会调用
        public void onServiceConnected(ComponentName name, IBinder binder) {

            myBindService = ((MyBinder) binder).getService();
        }

        @Override//当启动源意外丢失的时候会调用
        //比如service崩溃或被强行杀死
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    Button btnStart, btnStop, btnBind, btnUnBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        init();
    }

    @Override
    protected void init() {
        super.init();

        bindView(R.id.btn_start_service).setOnClickListener(this);
        bindView(R.id.btn_stop_service).setOnClickListener(this);
        bindView(R.id.btn_bind_service).setOnClickListener(this);
        bindView(R.id.btn_unbind_service).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Class<?> cls = null;

        switch (v.getId()){
            case R.id.btn_start_service:
//                cls = MyStartService.class;
                intent = new Intent(atiy, MyStartService.class);
                startService(intent);
                break;
            case R.id.btn_stop_service:
                stopService(intent);
                break;
            case R.id.btn_bind_service:
                intent = new Intent(atiy, MyBindService.class);

                bindService(intent, serviceConnection, ServiceDemo.BIND_AUTO_CREATE);
                myBindService.play();
                break;
            case R.id.btn_unbind_service:
                unbindService(serviceConnection);
                break;
        }
//        intent = new Intent(atiy, cls);
//        startService(intent);
    }
}
