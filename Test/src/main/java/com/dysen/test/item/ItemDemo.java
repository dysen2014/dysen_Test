package com.dysen.test.item;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.item.BaseItemLayout;
import com.dysen.lib.item.ItemUtils;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;
import com.dysen.test.asynctask.AsyncTaskDemo;
import com.dysen.test.broadcast.BroadcastDemo;
import com.dysen.test.contentprovider.ContentProviderDemo;
import com.dysen.test.emoji.EmojiRainDemo;
import com.dysen.test.gestureoverlayview.GestureOverlayViewDemo;
import com.dysen.test.picker.PickerActivity;
import com.dysen.test.service.SystemServiceDemo;
import com.dysen.test.view.GridViewDemo;
import com.dysen.test.view.ProgressBarDemo;
import com.dysen.test.view.SpinnerDemo;
import com.dysen.test.view.WebViewDemo;
import com.dysen.test.view.animator.AnimatorDemo;
import com.dysen.test.view.expand.ExpandIconViewDemo;
import com.dysen.test.view.fragment.FragmentDemo;
import com.dysen.test.view.fragmentTab.FragmentTab;
import com.dysen.test.view.gallery.GalleryDemo;
import com.dysen.test.view.panoramaImageView.PanoramaImageViewDemo;
import com.dysen.test.view.recyclerView.RecyclerViewDemo;
import com.dysen.test.view.scrollview.ScrollViewDemo;
import com.dysen.test.view.seekbar.SeekBarDemo;
import com.dysen.test.view.snowfall.SnowfallDemo;
import com.dysen.test.view.viewPagerTab.ViewPagerTab;
import com.dysen.test.view.viewflipper.ViewFlipperDemo;
import com.dysen.test.view.viewpager.ViewPagerDemo;
import com.dysen.test.view.viewpageranim.ViewPagerAnim;

/**
 * item 样式
 */
public class ItemDemo extends BaseActivity {

    Context context;
    BaseItemLayout baseLayout;
    ScrollView scrollView;
    String[] values = {"picker", "gridView", "spinner", "progress", "webView", "fragment", "viewpager", "viewFlipper"
            , "scrollView", "gallery", "seekbar", "asynctask", "contentprovider", "broadcast", "Service", "GestureDetector",
            "PanoramaImageViewDemo", "EmojiRain", "ExpandIcon", "Animator", "ViewPagerAnim", "ViewPagerTab", "SnowfallView", "RecyclerView"};
    String[] infos = {"pickerdemo", "gridView demo", "spinner demo", "进度条", "webView", "fragment", "页卡", "轮播页卡"
            , "滚动条", "gallery", "滑块", "异步任务", "", "广播", "服务", "手势监听", "", "掉表情包", "", "属性动画", "viewPager动画", "",
            "下雪", "RecyclerView"};
    int[] resIds = {R.drawable.img_pink, R.drawable.img_blue, R.drawable.img_green, R.drawable.img_darkcyan
            , R.drawable.img_orange, R.drawable.ic_set, R.drawable.ic_ghost, R.drawable.ic_explore, R.drawable.ic_gps
            , R.drawable.ic_check, 0, 0, 0, 0, R.drawable.img_purple, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scrollView = new ScrollView(this);
        baseLayout = new BaseItemLayout(this);
        scrollView.addView(baseLayout);
        scrollView.setVerticalScrollBarEnabled(false);
        setContentView(scrollView);
//        setContentView(R.layout.activity_item_demo);
        baseLayout.setBackgroundColor(Color.parseColor("#d1d4d9"));
        context = this;
        init();
        for (int i = 0; i < 9; i++) {
            LogUtils.i("i="+i);
            if (i == 9) {
                showShort("hello", 2);
            }
        }
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        baseLayout.setValueList(ItemUtils.getValueList(values)) // 文字 list
                .setResIdList(ItemUtils.getResIdList(resIds)) // icon list
                .setItemMarginTop(20)  //设置 全部item的间距
                .setItemMarginTop(1, 0) // 设置 position 下的item 的 间距
                .setIconHeight(24)    // icon 的高度
                .setIconWidth(24)      // icon 的宽度
//                .setItemMode(BaseItemLayout.Mode.IMAGE) // 设置显示模式
//                .setItemMode(ItemUtils.getValueList(values).size() -1, BaseItemLayout.Mode.BUTTON) // 设置显示模式
                .setTrunResId(R.drawable.ic_switch_off)  //设置未选中图片
                .setUpResId(R.drawable.ic_switch_on) //设置选中图片
                .setArrowResId(R.drawable.img_find_arrow) // 右边的箭头
                .setItemMode(BaseItemLayout.Mode.TXT) // 设置显示模式
                .setItemRightText(ItemUtils.getInfoList(infos))  // 只有在Mode.TXT 才需要设置改值
                .create();

        baseLayout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        showActivity(atiy, PickerActivity.class);
                        break;
                    case 1:
                        showActivity(atiy, GridViewDemo.class);
                        break;
                    case 2:
                        showActivity(atiy, SpinnerDemo.class);
                        break;
                    case 3:
                        showActivity(atiy, ProgressBarDemo.class);
                        break;
                    case 4:
                        showActivity(atiy, WebViewDemo.class);
                        break;
                    case 5:
                        showActivity(atiy, FragmentDemo.class);
                        break;
                    case 6:
                        showActivity(atiy, ViewPagerDemo.class);
                        break;
                    case 7:
                        showActivity(atiy, ViewFlipperDemo.class);
                        break;
                    case 8:
                        showActivity(atiy, ScrollViewDemo.class);
                        break;
                    case 9:
                        showActivity(atiy, GalleryDemo.class);
                        break;
                    case 10:
                        showActivity(atiy, SeekBarDemo.class);
                        break;
                    case 11:
                        showActivity(atiy, AsyncTaskDemo.class);
                        break;
                    case 12:
                        showActivity(atiy, ContentProviderDemo.class);
                        break;
                    case 13:
                        showActivity(atiy, BroadcastDemo.class);
                        break;
                    case 14:
                        showActivity(atiy, SystemServiceDemo.class);
                        break;
                    case 15:
                        showActivity(atiy, GestureOverlayViewDemo.class);
                        break;
                    case 16:
                        showActivity(atiy, PanoramaImageViewDemo.class);
                        break;
                    case 17:
                        showActivity(atiy, EmojiRainDemo.class);
                        break;
                    case 18:
                        showActivity(atiy, ExpandIconViewDemo.class);
                        break;
                    case 19:
                        showActivity(atiy, AnimatorDemo.class);
                        break;
                    case 20:
                        showActivity(atiy, ViewPagerAnim.class);
                        break;
                    case 21:
                        showActivity(atiy, FragmentTab.class);
                        break;
                    case 22:
                        showActivity(atiy, SnowfallDemo.class);
                        break;
                    case 23:
                        showActivity(atiy, RecyclerViewDemo.class);
                        break;
//                    case :
//                    showActivity(atiy, .class);
//                    break;
                }
            }
        });


        baseLayout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
            @Override
            public void onClick(int position, boolean isCheck) {
                Log.e(TAG, "-----> position = " + position + " isCheck = " + isCheck);
            }
        });
    }

    @Override
    protected void init() {
        super.init();

//        atiy = this;
//        baseLayout = bindView(R.id.layout);
    }

}
