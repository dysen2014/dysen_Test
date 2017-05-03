package com.dysen.test.view.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class FragmentDemo extends BaseActivity implements View.OnClickListener{

    Button btnStatic, btnDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();


    }

    @Override
    protected void init() {
        super.init();

        btnDynamic = bindView(R.id.btn_fragment_dynamic);
        btnStatic = bindView(R.id.btn_fragment_static);

        btnStatic.setOnClickListener(this);
        btnDynamic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_fragment_static:
                showActivity(atiy, FragmentStatic.class);
                break;
            case R.id.btn_fragment_dynamic:
                fragmentDynamic();
                break;
        }
    }

    /**
     *  通过 commit() 方法 提交给Activity
     *  如果允许用户通过Back键返回到前一个Fragment状态，在commit()之前调用addToBackStack();
     */
    private void fragmentDynamic() {
        MyFragment2 myFragment2 = new MyFragment2();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fm_fragment, myFragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
