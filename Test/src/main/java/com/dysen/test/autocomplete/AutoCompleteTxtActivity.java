package com.dysen.test.autocomplete;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class AutoCompleteTxtActivity extends BaseActivity {

    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    private String[] res = {"a", "ab", "abc","abcd", "b", "bcd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_txt);

        init();
//        autoCompleteTextView.setAdapter(getAdapter(res));
        control(autoCompleteTextView);
        control(multiAutoCompleteTextView);
    }

    private void control(View view) {
        switch (view.getId()){
            case R.id.autoCompleteTextView:
                autoCompleteTextView.setAdapter(getAdapter(res));
                break;
            case R.id.multiAutoCompleteTextView:
                multiAutoCompleteTextView.setAdapter(getAdapter(res));
                //设置以逗号为分隔符作为结束符号
                multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                break;
        }
    }
    /**
     *  初始化 adapter 及 数据源
     */
    private ArrayAdapter<String> getAdapter(String[] res) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);

        return adapter;
    }

    @Override
    protected void init() {
        super.init();

        autoCompleteTextView = bindView(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = bindView(R.id.multiAutoCompleteTextView);
    }
}
