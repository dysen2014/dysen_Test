package com.dysen.test.view.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dy on 2016/12/16.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<View> viewList;
    private List<String> titleList;

    public MyPagerAdapter(List<View> viewList, List<String> titleList){
        this.viewList = viewList;
        this.titleList = titleList;
    }

    /**
     * 页卡的数量
     * @return
     */
    @Override
    public int getCount() {
        return viewList.size();
    }

    /**
     * view 是否来自于对象
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 实例化一个页卡
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    /**
     * 销毁一个页卡
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(viewList.get(position));
    }

    /**
     * 设置页卡的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
