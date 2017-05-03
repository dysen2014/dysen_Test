package com.dysen.test.view.viewpageranim;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by dy on 2017/1/11.
 */

public class MyPagerAdapter extends PagerAdapter {

    Context context;
    List<ImageView> imgList;
    int[] imgIds;

    public MyPagerAdapter(Context context, List<ImageView> imgList, int[] imgIds) {
        this.context = context;
        this.imgList = imgList;
        this.imgIds = imgIds;
    }

    @Override
    public int getCount() {
        return imgIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imgIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        imgList.add(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgList.get(position));
    }
}
