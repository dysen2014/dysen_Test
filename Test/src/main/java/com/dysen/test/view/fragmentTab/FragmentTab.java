package com.dysen.test.view.fragmentTab;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;
import com.dysen.test.view.viewpager.*;

import java.util.ArrayList;
import java.util.List;

public class FragmentTab extends BaseActivity {

    FrameLayout frameLayout;
    ViewPager viewPager;
    LinearLayout ll1, ll2, ll3, ll4;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

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
        MyFragmentPagerAdapter2 myFragmentPagerAdapter2 = new MyFragmentPagerAdapter2(getSupportFragmentManager(), fragmentList);

                viewPager.setAdapter(myFragmentPagerAdapter2);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentItem = viewPager.getCurrentItem();
                reSetImg();
                switch (currentItem){
                    case 0:
                        ll1.setAlpha(1f);
                        break;
                    case 1:
                        ll2.setAlpha(1f);
                        break;
                    case 2:
                        ll3.setAlpha(1f);
                        break;
                    case 3:
                        ll4.setAlpha(1f);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void init() {
        super.init();

        viewPager = bindView(R.id.vp_fragment_tab);
        frameLayout = bindView(R.id.fg_frameLayout_tab);

        ll1 = bindView(R.id.ll_bottom_1);
        ll2 = bindView(R.id.ll_bottom_2);
        ll3 = bindView(R.id.ll_bottom_3);
        ll4 = bindView(R.id.ll_bottom_4);

        /**
         * 通过 Fragment 作为 View Pager 的数据源
         */
        fragmentList.add(new MyFragment1());
        fragmentList.add(new MyFragment2());
        fragmentList.add(new MyFragment3());
        fragmentList.add(new MyFragment4());
    }

    @Override
    public void doClick(View view) {
        super.doClick(view);

        //
        reSetImg();

        switch (view.getId()){
            case R.id.ll_bottom_1:
                viewPager.setCurrentItem(0);
                ll1.setAlpha(1f);
                break;
            case R.id.ll_bottom_2:
                viewPager.setCurrentItem(1);
                ll2.setAlpha(1f);
                break;
            case R.id.ll_bottom_3:
                viewPager.setCurrentItem(2);
                ll3.setAlpha(1f);
                break;
            case R.id.ll_bottom_4:
                viewPager.setCurrentItem(3);
                ll4.setAlpha(1f);
                break;
        }
    }

    private void reSetImg() {

        ll1.setAlpha(.5f);
        ll2.setAlpha(.5f);
        ll3.setAlpha(.5f);
        ll4.setAlpha(.5f);
    }
}
