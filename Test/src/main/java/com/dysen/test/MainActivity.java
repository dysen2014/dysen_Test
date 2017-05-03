package com.dysen.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.lib.util.ToastUtils;
import com.dysen.lib.volley.VolleyInterface;
import com.dysen.lib.volley.VolleyRequest;
import com.dysen.lib.zbarscan.ScanCodeUtils;
import com.dysen.test.handler.HandlerDemo;
import com.dysen.test.item.ItemDemo;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    String url = "http://wx.qjzls.com/admin/appLogin/login?logName=tq&pw=123";
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = bindView(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("测试 App");

        init();

        FloatingActionButton fab = bindView(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    protected void init() {
        txt = bindView(R.id.txt);
        bindView(R.id.volley).setOnClickListener(this);
        bindView(R.id.scan).setOnClickListener(this);
        bindView(R.id.handler).setOnClickListener(this);
        bindView(R.id.btn).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String result = data.getStringExtra("result");
        if (result != null){
            ToastUtils.showLong(this, ""+result, 1);
            ((TextView)bindView(R.id.txt)).setText(""+result);
        }else {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        ToastUtils.showLong(this, "关闭当前 activity", 2);
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.volley :
                volleyTest();
                break;
            case R.id.scan :
                scanCode();
                break;
            case R.id.handler :
                showActivity(MainActivity.this, HandlerDemo.class);
                break;
            case R.id.btn :
                showActivity(MainActivity.this, ItemDemo.class);
                break;
//            case R.id :
//                break;
        }
    }

    private void volleyTest() {

        String url = "http://wx.qjzls.com/admin/appLogin/login?logName=tq&pw=123";

        VolleyRequest.RequestGet(MainActivity.this, url, "doGet", new VolleyInterface(MainActivity.this, VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onMySuccess(String s) {
                LogUtils.i(s);
            }

            @Override
            public void onMyError(VolleyError error) {

            }
        });
    }

    private void scanCode() {

        /**android 6.0 权限申请**/
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, ScanCodeUtils.code);
            //判断是否需要 向用户解释，为什么要申请该权限
            ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS);
        } else {
//                    startActivityForResult(
//                            new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
//                                    MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)), 1);
            startActivityForResult(new Intent(MainActivity.this, ScanCodeUtils.class), ScanCodeUtils.code);
        }
    }
}
