package com.dysen.test.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dysen.test.R;

/**
 * Created by dy on 2016/12/15.
 */

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /**
         *  resource    Fragment 需要加载的布局文件
         *  root    加载layout的父ViewGroup
         *  attactToRoot    false， 不返回ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment,  container, false);
        TextView textView = (TextView) view.findViewById(R.id.txt_fragment);
        Button btn = (Button) view.findViewById(R.id.btn_fragment);
        textView.setText("静态加载 FRagment");
        btn.setText("change");

        return view;
    }
}
