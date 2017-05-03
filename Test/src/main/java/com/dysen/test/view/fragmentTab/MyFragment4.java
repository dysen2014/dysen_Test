package com.dysen.test.view.fragmentTab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dysen.test.R;

/**
 * Created by dy on 2016/12/15.
 */

public class MyFragment4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /**
         *  resource    Fragment 需要加载的布局文件
         *  root    加载layout的父ViewGroup
         *  attactToRoot    false， 不返回ViewGroup
         */
        View view = inflater.inflate(R.layout.activity_viewpager_view4,  container, false);
        TextView textView = (TextView) view.findViewById(R.id.txt_fragment);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("我们销毁啦");
    }
}
