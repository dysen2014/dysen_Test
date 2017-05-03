package com.dysen.test.view.scrollview;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.lib.util.SharedPreUtils;
import com.dysen.test.R;

public class ScrollViewDemo extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private TextView txtView, txtHeight;
    private ScrollView scrollView;
    private Button btnUp, btnDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        txtView.setText(getResources().getText(R.string.context));
        String strHeight = SharedPreUtils.myGetValue(sharedPreferences, "scroll_y");
        if (!strHeight.equals("")) {
            showLong(Integer.parseInt(strHeight) + "上流浏览到：" + SharedPreUtils.myGetValue(sharedPreferences, "scroll_y"), 1);
            scrollView.post(new Runnable() {//定位到上次浏览的位置
                @Override
                public void run() {
//                scrollView.fullScroll(scrollView.FOCUS_DOWN);//定位到底部
                    scrollView.scrollTo(0, Integer.parseInt(SharedPreUtils.myGetValue(sharedPreferences, "scroll_y")));
                    txtHeight.setText("此时的高度：" + scrollView.getScrollY());
                }
            });
        }
        /**
         * scrollView 监听器
         */
        scrollView.setOnTouchListener(this);

        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
    }

    @Override
    protected void init() {
        super.init();

        txtView = bindView(R.id.txt_scrollview);
        txtHeight = bindView(R.id.txt_height_scrollView);
        scrollView = bindView(R.id.scl_scrollview);

        btnUp = bindView(R.id.btn_up_scrollview);
        btnDown = bindView(R.id.btn_down_scrollview);

        sharedPreferences = atiy.getSharedPreferences("share_data", MODE_PRIVATE);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN://手指落下
                break;
            case MotionEvent.ACTION_MOVE://手指滑动
                break;
            case MotionEvent.ACTION_UP://手指离开
                SharedPreUtils.mySetValue(sharedPreferences, "scroll_y", scrollView.getScrollY()+"");
                txtHeight.setText("此时的高度："+scrollView.getScrollY());
                //顶部
                if (scrollView.getScrollY() <= 0){
                    LogUtils.i("顶部");
                }
                //底部
                if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY()){
                    LogUtils.i("底部");
                    LogUtils.i("内容高度："+scrollView.getChildAt(0).getMeasuredHeight()+"\n屏幕高度："+scrollView.getHeight()+"\n滑动的高度："+scrollView.getScrollY());
                    txtView.append(getResources().getText(R.string.context));
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            /**
             * scrollTo();  以滚动视图起始位置开始计算
             * scrollBy();  相对于前一次的位置，去滚动对应的距离
             */
            case R.id.btn_up_scrollview:
                scrollView.scrollBy(0, 0-scrollView.getHeight());
                break;
            case R.id.btn_down_scrollview:
                scrollView.scrollBy(0, scrollView.getHeight());
                break;
        }
    }
}
