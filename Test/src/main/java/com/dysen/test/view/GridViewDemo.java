package com.dysen.test.view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridViewDemo extends BaseActivity implements AdapterView.OnItemClickListener {

    GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] icon= {R.drawable.img_pink, R.drawable.img_blue, R.drawable.img_green, R.drawable.img_darkcyan};
    private String[] iconName = {"user", "system", "view", "about"};
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_grid_view_demo);

        init();
        control();
    }

    private void control() {

        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.gridview_item,new String[]{"img", "txt"}
                , new int[]{R.id.img_icon, R.id.txt_icon_name});
        gridView.setAdapter(simpleAdapter);

        //设置监听
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {

        for (int i=0; i<icon.length; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("txt", iconName[i]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    protected void init() {
        super.init();

        gridView = bindView(R.id.grid_view);
        dataList = new ArrayList<Map<String, Object>>();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        showLong(iconName[position]+"\tposition="+position, 3);
    }
}
