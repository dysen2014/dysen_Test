package com.dysen.test.view.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDemo extends BaseActivity implements ViewPager.OnPageChangeListener{

    List<View> viewList;
    List<String> titleList;
    List<Fragment> fragmentList;

    ViewPager viewPager;

    PagerTabStrip pagerTabStrip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        /**
         * 方式一
         */
//        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(viewList, titleList);
//        viewPager.setAdapter(myPagerAdapter);
        pagerTabStrip.setBackgroundResource(R.color.colorPrimary);
        pagerTabStrip.setTextColor(Color.WHITE);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColorResource(R.color.greenyellow);

        /**
         * 方式二
         * 页卡 不销毁
         */
//        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
//        viewPager.setAdapter(myFragmentPagerAdapter);

        /**
         * 方式三
         * 页卡 会销毁
         */
        MyFragmentPagerAdapter2 myFragmentPagerAdapter2 = new MyFragmentPagerAdapter2(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(myFragmentPagerAdapter2);
    }

    @Override
    protected void init() {
        super.init();

        viewPager = bindView(R.id.vp_viewpager);
        pagerTabStrip = bindView(R.id.pts_viewpager);

        viewPager.
        (this);

        viewList = new ArrayList<View>();
        titleList = new ArrayList<String>();
        fragmentList = new ArrayList<Fragment>();
        /**
         * 通过 View 作为 View Pager 的数据源
         */
        View view1 = View.inflate(atiy, R.layout.activity_viewpager_view1, null);
        View view2 = View.inflate(atiy, R.layout.activity_viewpager_view2, null);
        View view3 = View.inflate(atiy, R.layout.activity_viewpager_view3, null);
        View view4 = View.inflate(atiy, R.layout.activity_viewpager_view4, null);

        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        /**
         * 通过 Fragment 作为 View Pager 的数据源
         */
        fragmentList.add(new MyFragment1());
        fragmentList.add(new MyFragment2());
        fragmentList.add(new MyFragment3());
        fragmentList.add(new MyFragment4());

        titleList.add("第一页");
        titleList.add("第二页");
        titleList.add("第三页");
        titleList.add("第四页");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

//        LogUtils.i("position="+position+"\tpositionOffset="+positionOffset+"\ttpositionOffsetPixels="+ positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        showLong("当前是第"+(position+1)+"个页卡", 3);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        LogUtils.i("state="+state);
    }
}
