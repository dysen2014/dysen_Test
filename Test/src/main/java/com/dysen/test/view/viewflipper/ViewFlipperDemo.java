package com.dysen.test.view.viewflipper;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class ViewFlipperDemo extends BaseActivity {

    ViewFlipper viewFlipper;
    private int[] resTd = {R.drawable.ic_check, R.drawable.ic_scan, R.drawable.ic_location, R.drawable.ic_clean};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        for (int i : resTd){
            viewFlipper.addView(getChild(i));
        }
        /**
         * 为 Flipper 添加 动画效果
         */
//        viewFlipper.setInAnimation(this, R.anim.zoomin);
//        viewFlipper.setOutAnimation(this, R.anim.zoomout);
//        viewFlipper.setFlipInterval(3000);//3秒 切换一次
//        viewFlipper.startFlipping();
    }

    private ImageView getChild(int resId) {

        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(resId);//适应图片的宽高
        imageView.setBackgroundResource(resId);
        return imageView;
    }

    @Override
    protected void init() {
        super.init();

        viewFlipper = bindView(R.id.vfp_viewflipper);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int startX = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://手指落下
                startX = (int)event.getX();
                break;
            case MotionEvent.ACTION_MOVE://手指滑动

                break;
            case MotionEvent.ACTION_UP://手指离开
                //向右滑动
                if (event.getX() - startX > 100){
                    viewFlipper.setInAnimation(this, R.anim.zoomin);
                    viewFlipper.setInAnimation(this, R.anim.zoomout);
                    viewFlipper.showPrevious();
                }
                //向左滑动
                if (startX - event.getX() > 100){
                    viewFlipper.setInAnimation(this, R.anim.zoomin);
                    viewFlipper.setInAnimation(this, R.anim.zoomout);
                    viewFlipper.showNext();
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
