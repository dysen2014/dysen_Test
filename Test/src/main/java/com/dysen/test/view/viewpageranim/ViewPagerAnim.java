package com.dysen.test.view.viewpageranim;

import android.animation.ObjectAnimator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ZoomButtonsController;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;
import com.dysen.test.view.animator.AnimatorUtils;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAnim extends BaseActivity {

    ViewPager viewPager;
    int[] icons = new int[]{R.mipmap.guide_image1, R.mipmap.guide_image2, R.mipmap.guide_image3};
    List<ImageView> imgList = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_anim);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();
        //添加动画效果
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {

//                showShort("position="+position, 1);
                //添加动画效果
                ObjectAnimator objectAnimator = AnimatorUtils.getAnimator(page, "alpha", 2000, 1-position);
//                        AnimatorUtils.getAnimator(viewPager, "alpha", 2000, .4f, 1f);
                objectAnimator.setInterpolator(new BounceInterpolator());
//                objectAnimator.start();
            }
        });
        viewPager.setAdapter(new MyPagerAdapter(atiy, imgList, icons));

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                //添加动画效果
                ObjectAnimator objectAnimator = AnimatorUtils.getAnimator(viewPager, "rotation", 2000, 0f, 360f);
//                        AnimatorUtils.getAnimator(viewPager, "alpha", 2000, .4f, 1f);
                objectAnimator.setInterpolator(new BounceInterpolator());
                objectAnimator.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void init() {
        super.init();

        viewPager = bindView(R.id.vpg_view_pager_anim);
    }
}
