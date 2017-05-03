package com.dysen.test.reveal;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.dysen.lib.util.LogUtils;

public class RevealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        RevealLayout revealLayout = new RevealLayout(this);

        super.onCreate(savedInstanceState);

        setContentView(revealLayout);

        Button btn = new Button(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        LogUtils.i(getWindowManager().getDefaultDisplay().getWidth()+"==="+displayMetrics.widthPixels);
        btn.setWidth(getWindowManager().getDefaultDisplay().getWidth());
            btn.setBackgroundColor(Color.parseColor("#009cbb"));
        btn.setTop(30);
        revealLayout.addView(btn);
//        setContentView(R.layout.activity_reveal);
    }
}
