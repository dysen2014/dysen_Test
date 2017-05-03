package com.dysen.test.gesturedetector;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class GestureDetectorDemo extends BaseActivity {

    ImageView img;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        init();
        logic();
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() - e2.getX()>50){
                showLong("向左滑动", 1);
            } else if (e2.getX() - e1.getX()>50){
                showLong("向右滑动", 1);
            }

            if (e1.getY() - e2.getY()>50){
                showLong("向上滑动", 1);
            } else if (e2.getY() - e1.getY()>50){
                showLong("向下滑动", 1);
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    protected void logic() {
        super.logic();

        gestureDetector = new GestureDetector(new MyGestureDetector());

        img.setOnTouchListener(new View.OnTouchListener() {
            @Override//捕获触摸的事件
            public boolean onTouch(View v, MotionEvent event) {

                //通过它把监听事件转发给SimpleOnGestureListener
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    @Override
    protected void init() {
        super.init();
     img = bindView(R.id.iv_gesture_detector);
    }
}
