package com.dysen.test.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerDemo extends BaseActivity implements AdapterView.OnItemSelectedListener {

    TextView txt;
    Spinner spinner;
    private String[] ss = {"user", "system", "view", "about"};
    private int[] icon= {R.drawable.img_pink, R.drawable.img_blue, R.drawable.img_green, R.drawable.img_darkcyan};
    List<String> list;
    private List<Map<String, Object>> dataList;
    ArrayAdapter<CharSequence> arrayAdapter;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();
        //适配器
//        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        simpleAdapter = new SimpleAdapter(this, getData2(), R.layout.sp_item, new String[]{"img", "txt"}
                , new int[]{R.id.img_sp, R.id.txt_sp});
        simpleAdapter.setDropDownViewResource(R.layout.sp_item);
        spinner.setAdapter(simpleAdapter);

        //适配器
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getData());
        //设置样式 为适配器设置下拉列表下拉时的菜单样式
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //加载适配器 将适配器添加到下拉列表上
//        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    private List<String> getData() {

        for (String s : ss){
            list.add(s);
        }

        return list;
    }

    private List<Map<String, Object>> getData2() {

        for (int i=0; i<icon.length; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("img", icon[i]);
            map.put("txt", ss[i]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    protected void init() {
        super.init();

        txt = bindView(R.id.txt_spinner);
        spinner = bindView(R.id.sp_spinner);

        list = new ArrayList<String>();
        dataList = new ArrayList<Map<String, Object>>();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        String item = (String) arrayAdapter.getItem(i);
        String item = ""+ simpleAdapter.getItem(i);
        //dataList.get(i);
        txt.setText(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
