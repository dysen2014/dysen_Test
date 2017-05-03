package com.dysen.test.view.viewPagerTab;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerTab extends BaseActivity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    List<View> viewList = new ArrayList<View>();
    LinearLayout ll1, ll2, ll3, ll4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_tab);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

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

        pagerAdapter = new MyPagerAdapter(viewList);
        viewPager.setAdapter(pagerAdapter);

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

    @Override
    protected void init() {
        super.init();

        ll1 = bindView(R.id.ll_bottom_1);
        ll2 = bindView(R.id.ll_bottom_2);
        ll3 = bindView(R.id.ll_bottom_3);
        ll4 = bindView(R.id.ll_bottom_4);
       viewPager = bindView(R.id.vpg_viewPager_tab);
    }
}
