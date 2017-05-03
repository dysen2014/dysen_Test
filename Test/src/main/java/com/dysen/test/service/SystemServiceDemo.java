package com.dysen.test.service;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.NetUtils;
import com.dysen.lib.util.ServiceUtils;
import com.dysen.test.R;

public class SystemServiceDemo extends BaseActivity {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_service);

        init();
//        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        if (NetUtils.isConnected(this)){
            showShort("网络可用", 1);
            if (NetUtils.isWifi(this)){
                showShort("wifi 已连接", 1);
            }else {
                showShort("wifi已断开", 1);
            }
        }else {
            showShort("网络不可用", 1);
        }
    }

    @Override
    protected void init() {
        super.init();

        txt = bindView(R.id.txt_sys_service);
    }

    public void doClick(View view){
        switch (view.getId()){

            case R.id.btn_check:
                if (NetUtils.isConnected(this)){
                    showShort("网络可用", 1);
                }else {
                    showShort("网络不可用", 1);
                }
                break;
            case R.id.tbtn:
                ServiceUtils.controlWifi(this);
                break;
            case R.id.sk_sys_service:
                txt.setText("系统当前音量："+ServiceUtils.getSysVolume(this))
                ;
                break;
        }
    }
}
