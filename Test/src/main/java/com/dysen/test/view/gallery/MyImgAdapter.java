package com.dysen.test.view.gallery;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by dy on 2016/12/19.
 */

public class MyImgAdapter extends BaseAdapter {

    private int[] res;
    private Context context;

    public MyImgAdapter(Context context, int[] res){
        this.res = res;
        this.context = context;
    }

    @Override
    public int getCount() {

//        return res.length;
        return Integer.MAX_VALUE;
    }

    @Override
    public Object getItem(int position) {
        return res[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ImageView imageView = new ImageView(context);
        imageView.setBackgroundResource(res[position % res.length]);//循环播放
        //设置图像大小
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return imageView;
    }
}
