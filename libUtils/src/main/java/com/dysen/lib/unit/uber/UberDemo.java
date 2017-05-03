package com.dysen.lib.unit.uber;

import android.os.Bundle;

import com.dysen.lib.R;
import com.dysen.lib.base.BaseActivity;

public class UberDemo extends BaseActivity {

    UberProgressView uber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_demo);

        init();
    }

    protected void init() {

        uber = bindView(R.id.uber);

    }
}
