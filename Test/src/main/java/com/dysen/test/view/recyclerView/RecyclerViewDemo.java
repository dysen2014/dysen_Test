package com.dysen.test.view.recyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.Utils;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemo extends BaseActivity {

    RecyclerView recyclerView;
    ArrayList<Utils.SampleModel> sampleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        sampleData = Utils.getSampleData(100);

//        recyclerView.setLayoutManager(new LinearLayoutManager(atiy));//设置布局管理器
//        recyclerView.addItemDecoration(new DividerItemDecoration(atiy, LinearLayoutManager.VERTICAL));//添加分割线

//        recyclerView.setLayoutManager(new GridLayoutManager(atiy, 3));//设置布局管理器
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(atiy));//添加分割线

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL));//设置布局管理器
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL));//设置布局管理器
        //设置adapter
        recyclerView.setAdapter(new HomeAdapter(atiy, sampleData));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void init() {
        super.init();

        recyclerView = bindView(R.id.rv_recycler_view);
    }
}
