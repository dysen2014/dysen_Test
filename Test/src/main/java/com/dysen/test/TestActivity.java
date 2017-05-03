package com.dysen.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.volley.VolleyError;
import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.item.BaseItemLayout;
import com.dysen.lib.item.ItemUtils;
import com.dysen.lib.util.LogUtils;
import com.dysen.lib.util.ToastUtils;
import com.dysen.lib.volley.VolleyInterface;
import com.dysen.lib.volley.VolleyRequest;
import com.dysen.lib.zbarscan.ScanCodeUtils;
import com.dysen.test.gps.GpsDemo;
import com.dysen.test.handler.HandlerDemo;
import com.dysen.test.item.ItemDemo;
import com.dysen.test.person.PersonActivity;
import com.dysen.test.view.ViewActivity;

public class TestActivity extends BaseActivity {

    BaseItemLayout baseLayout;

    String[] values = {"volley", "scan", "handler", "item", "update", "persons", "gps"};
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
                Log.e(TAG,"----- position = " + position);
                Class<?> cls;
                switch (position){
                    case 0:
                        volleyTest();
                        break;
                    case 1:
                        scanCode();
                    break;
                    case 2:
                        cls = HandlerDemo.class;
                        showActivity(TestActivity.this, HandlerDemo.class);
                    break;
                    case 3:
                        cls = ItemDemo.class;
                        showActivity(TestActivity.this, ItemDemo.class);
                    break;
                    case 4:
                        showActivity(TestActivity.this, ViewActivity.class);
                    break;
                    case 5:
                        showActivity(TestActivity.this, PersonActivity.class);
//                            showWeb("http://wap.baidu.com");
                        break;
                    case 6:
                        showActivity(TestActivity.this, GpsDemo.class);
                        break;
//                    case :
//                    break;
                }

//                showActivity(this, cls);
            }
        });


        baseLayout.setOnSwitchClickListener(new BaseItemLayout.OnSwitchClickListener() {
            @Override
            public void onClick(int position, boolean isCheck) {
                Log.e(TAG,"-----> position = " + position +" isCheck = " + isCheck);
            }
        });
    }

    private void volleyTest() {

        String url = "http://wx.qjzls.com/admin/appLogin/login?logName=tq&pw=123";

        VolleyRequest.RequestGet(this, url, "doGet", new VolleyInterface(this, VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String s) {
                LogUtils.i(s);
            }

            @Override
            public void onMyError(VolleyError error) {
                LogUtils.i(error.toString());
            }
        });
    }

    private void scanCode() {

        /**android 6.0 权限申请**/
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ScanCodeUtils.code);
            //判断是否需要 向用户解释，为什么要申请该权限
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS);
        } else {
//                    startActivityForResult(
//                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
//                                    MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)), 1);
            startActivityForResult(new Intent(this, ScanCodeUtils.class), ScanCodeUtils.code);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        LogUtils.i("requestCode="+requestCode+"\tresultCode="+resultCode+"\ndata="+data);
        String result = data.getExtras().getString("result");
        if (result != null){
            ToastUtils.showLong(this, ""+result, 1);
        }else {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
