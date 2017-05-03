package com.dysen.test.view.gallery;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.dialog.CommonDialog;
import com.dysen.test.R;

public class GalleryDemo extends BaseActivity implements AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {

    // 数据源
    private int[] imgs = {R.mipmap.item1, R.mipmap.item2, R.mipmap.item3, R.mipmap.item4, R.mipmap.item5, R.mipmap.item6
            , R.mipmap.item7, R.mipmap.item8, R.mipmap.item9, R.mipmap.item10, R.mipmap.item11, R.mipmap.item12};
    private Gallery gallery;
    ImageSwitcher imageSwitcher;
    private MyImgAdapter myImgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_demo);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        //加载 gallery 适配器
        myImgAdapter = new MyImgAdapter(this, imgs);

        gallery.setAdapter(myImgAdapter);

        gallery.setOnItemSelectedListener(this);

        imageSwitcher.setFactory(this);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(atiy, android.R.anim.fade_in));//淡入
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(atiy, android.R.anim.fade_out));//淡出

    }

    @Override
    protected void init() {
        super.init();

        gallery = bindView(R.id.gl_gallery);
        imageSwitcher = bindView(R.id.imgsw_gallery);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        imageSwitcher.setBackgroundResource(imgs[position % imgs.length]);
        String msg = "对于一个在北平住惯的人，像我，冬天要是不刮风，便觉得是奇迹;济南的冬天是没有风声的。";
//        initDialog("", msg);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View makeView() {

        ImageView imageView = new ImageView(atiy);
        //等比例缩放
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return imageView;
    }

    private void initDialog(String title, String msg) {
//        final CommonDialog dialog = new CommonDialog(atiy);
        commonDialog.setMessage(msg)
//                .setImageResId(R.mipmap.ic_launcher)
                .setTitle("系统提示")//setSingle(true)显示单个按钮 false 显示两个按钮
                .setSingle(false).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
            @Override
            public void onPositiveClick() {
                commonDialog.dismiss();
                showShort("xxx", 1);
            }

            @Override
            public void onNegtiveClick() {
                commonDialog.dismiss();
                showShort("sss", 1);
            }
        }).show();
    }
}
