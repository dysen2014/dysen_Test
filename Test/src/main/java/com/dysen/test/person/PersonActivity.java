package com.dysen.test.person;

import android.graphics.Color;
import android.os.Bundle;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.item.BaseItemLayout;
import com.dysen.lib.item.ItemUtils;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;

public class PersonActivity extends BaseActivity {

    BaseItemLayout baseLayout;

    String[] values = {"武汉通查询", "查询购票", "查看收益", "item", "update", "jrs直播", "gps"};
    String[] infos = {"1", "2", "3", "4", "5", "个人主页", "定位"};
    int[] resIds = {R.drawable.img_pink, R.drawable.img_blue, R.drawable.img_green, R.drawable.img_darkcyan,
            R.drawable.img_green, R.drawable.ic_explore, R.drawable.ic_location};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseLayout = new BaseItemLayout(this);
        setContentView(baseLayout);

        baseLayout.setBackgroundColor(Color.parseColor("#d1d4d9"));

        control();
    }

    private void control() {

        baseLayout.setValueList(ItemUtils.getValueList(values)) // 文字 list
                .setResIdList(ItemUtils.getResIdList(resIds)) // icon list
                .setItemMarginTop(20)  //设置 全部item的间距
                .setItemMarginTop(1,0) // 设置 position 下的item 的 间距
                .setIconHeight(24)    // icon 的高度
                .setIconWidth(24)      // icon 的宽度
                .setItemMode(BaseItemLayout.Mode.IMAGE) // 设置显示模式
                .setItemMode(ItemUtils.getValueList(values).size() -2, BaseItemLayout.Mode.BUTTON) // 设置显示模式
                .setTrunResId(R.drawable.ic_switch_off)  //设置未选中图片
                .setUpResId(R.drawable.ic_switch_on) //设置选中图片
                .setArrowResId(R.drawable.img_find_arrow) // 右边的箭头
                .setItemMode(ItemUtils.getValueList(values).size()-1, BaseItemLayout.Mode.TXT) // 设置显示模式
                .setItemRightText(ItemUtils.getInfoList(infos))  // 只有在Mode.TXT 才需要设置改值
                .create();

        baseLayout.setOnBaseItemClick(new BaseItemLayout.OnBaseItemClick() {
            @Override
            public void onItemClick(int position) {

                Class<?> cls;
                switch (position) {
                    case 0:
                        showWeb("http://app.tengchu.com/balance/index?openid=ohM8Fj1b0zzgcsYdSrEAQJHvOP7o");
                        break;
                    case 1:
                        showWeb("");
                        break;
                    case 2:
                        showWeb("https://yjb.youku.com/pcdn/main.html?pid=201607060youcoin&freeLogin=true&sourceType=weixin&userAgent=Mozilla/5.0%20(Linux;%20Android%207.1.1;%20Nexus%206P%20Build/NPF26F;%20wv)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Version/4.0%20Chrome/54.0.2840.85%20Mobile%20Safari/537.36%20MicroMessenger/6.3.31.921%20NetType/WIFI%20Language/zh_CN&requestTime=1480727541000&ytid=800917594&sign=e6d52a19a92befb15f2c0d5675fe36ac");
                        break;
//                    case :
//
//                        break;
//                showActivity(this, cls);
                }
            }
        });

        baseLayout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
            @Override
            public void onClick(int position, boolean isCheck) {
                LogUtils.i("-----> position = " + position +" isCheck = " + isCheck);
            }
        });
    }
}
