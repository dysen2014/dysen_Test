package com.dysen.test.view.viewpager;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dy on 2016/12/16.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    /**
     * 适合 页卡较少的时候
     */
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> titleList){
    super(fragmentManager);

        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
