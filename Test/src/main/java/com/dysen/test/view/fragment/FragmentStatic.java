package com.dysen.test.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class FragmentStatic extends BaseActivity {

    Button btnFragment;
    TextView txtFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFragment.setText("1234567890");
            }
        });
    }

    @Override
    protected void init() {
        super.init();

        btnFragment = bindView(R.id.btn_fragment);
        txtFragment = bindView(R.id.txt_fragment);

    }
}
