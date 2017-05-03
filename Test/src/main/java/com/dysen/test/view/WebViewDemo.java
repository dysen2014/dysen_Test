package com.dysen.test.view;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class WebViewDemo extends BaseActivity {

    WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();
        //加载 本地资源 （html 页面）
//        webView.loadUrl("file:///android_asset/");
        // 加载 外地资源
        webView.loadUrl("http://www.imooc.com");
        webView.setWebViewClient(new MyWebViewClient());

        //启用支持 JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //WebView 加载页面有限使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new MyWebChromeClient());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){//页面能返回
                webView.goBack();
            }else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void init() {
        super.init();

        webView = bindView(R.id.webView);
    }

    class MyWebChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100){
                //页面加载完成
                closeDialog();
            }else {
                //页面正在加载
                openDialog(newProgress);
            }
        }
    }

    private void openDialog(int newProgress) {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(atiy);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setTitle("加载中");
            progressDialog.setIcon(R.drawable.user);
            progressDialog.setMax(100);
            progressDialog.setProgress(newProgress);
            progressDialog.show();
        }else {
            progressDialog.setProgress(newProgress);
        }
    }

    private void closeDialog() {

        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //返回值是 true 时控制页面在 WebView 中打开，如果为 false 调用系统浏览器或第三方浏览器
            view.loadUrl(url);
            return true;
        }
        //WebViewClient 帮助 WebView 去处理一些页面控制和请求通知

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }
}
