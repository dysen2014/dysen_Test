package com.dysen.test.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class  HandlerDemo extends BaseActivity implements View.OnClickListener{

    int[] img = {R.drawable.img_pink, R.drawable.img_green, R.drawable.img_orange};
    int index = 0;

    Handler handler = new Handler();
    MyRunnable myRunnable = new MyRunnable();
    TextView txt;
    Switch sw;
    Button btn, btn2;
    ImageView iv;

    Handler handler2 = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String cl = "";
            int index = msg.what;
            switch (index) {
                case 0:
                    cl = "红色";
                    break;
                case 1:
                    cl = "绿色";
                    break;
                case 2:
                    cl = "黄色";
                    break;
            }
            txt.setText("当前颜色:"+cl);
        }

    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();
    }

     protected void init() {

        sw = bindView(R.id.sw);
        sw.setOnClickListener(this);
        btn = bindView(R.id.btn);
        btn.setOnClickListener(this);
        btn2 = bindView(R.id.btn2);
        btn2.setOnClickListener(this);
        iv = bindView(R.id.iv);
        iv.setOnClickListener(this);
        txt = bindView(R.id.txt);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sw:
                testLoop();
                break;
            case R.id.btn:
                test();
                break;
            case R.id.btn2:
                test2();
                break;
        }
    }

    private void test() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                txt.setText("123456");
            }
        });
    }

    private void test2() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Message msg = handler2.obtainMessage();
                msg.what = index;
                msg.sendToTarget();
            }
        }).start();
    }

    private void testLoop() {

        if (sw.isChecked())
            handler.postDelayed(myRunnable, 1500);
        else
            handler.removeCallbacks(myRunnable);
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            index++;
            index = index % 3;
            System.out.println("index="+index);
            iv.setImageResource(img[index]);

            Message msg = handler2.obtainMessage();
            msg.what = index;
            msg.sendToTarget();

            handler.postDelayed(myRunnable, 1500);
        }
    }
}
