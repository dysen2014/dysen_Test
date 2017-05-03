package com.dysen.test.view.snowfall;

import android.os.Bundle;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;
import com.jetradarmobile.snowfall.SnowfallView;

public class SnowfallDemo extends BaseActivity {

    SnowfallView snowfallView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snow_fall_demo);

        init();
        logic();
    }

    @Override
    protected void init() {
        super.init();

        snowfallView = bindView(R.id.sfv_snowfallView);
    }
}
