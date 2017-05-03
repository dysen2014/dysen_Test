package com.dysen.test.view.fragmentTab;


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
     * 适合 页卡较少的时候
     */
    private List<Fragment> fragmentList;

    public MyFragmentPagerAdapter2(FragmentManager fragmentManager, List<Fragment> fragmentList){
    super(fragmentManager);

        this.fragmentList = fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
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
