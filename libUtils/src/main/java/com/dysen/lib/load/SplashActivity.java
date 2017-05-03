package com.dysen.lib.load;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.dysen.lib.R;
import com.dysen.lib.base.BaseActivity;


public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = View.inflate(this, R.layout.activity_start, null);
        setContentView(view);
        //透明状态栏
//        StatusBarUtil.setTransparent(this);

        //实现淡入淡出的效果
//		overridePendingTransition(android.R.anim.slide_in_left,android.
//				R.anim.slide_out_right);

        //类似 iphone 的进入和退出时的效果
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.2f,0.8f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}

        });
    }

    /**
     * 跳转到...
     */
    private void redirectTo(){

        skipActivity(SplashActivity.this, LoginActivity.class);
    }
//    private void redirectTo(Intent intent){
//
//        skipActivity(SplashActivity.this, intent);
//    }
}
