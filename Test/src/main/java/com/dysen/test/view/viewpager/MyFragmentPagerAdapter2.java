package com.dysen.test.view.viewpager;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dy on 2016/12/16.
 */

public class MyFragmentPagerAdapter2 extends FragmentStatePagerAdapter {
    /**
     * 适合 页卡较多的时候
     */
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public MyFragmentPagerAdapter2(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> titleList){
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
