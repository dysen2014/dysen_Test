package com.dysen.test.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.ToastUtils;
import com.dysen.test.R;

/**
 * Created by dy on 2016-12-03.
 */

public class webViewUtils extends BaseActivity {

    private MyWebView mWebView;
    private Button btn_icon;
    private long exitTime = 0;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);

        (this.findViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        init();
        control();
    }

    private void control() {

        initSettings(url);
    }

    @Override
    protected void init() {
        super.init();
    }

    private void initSettings(String url) {

        mWebView = (MyWebView) findViewById(R.id.usew_wb);
        mWebView.loadUrl(url);
//        mWebView.loadUrl("http://m.jd.com/?usid=FVYT3IM5AHOXM3FSBLGHUM557I5DT4XXHOJH4L2HVBUSVA2OE37FYFTAP57U3WQBFJFT6GEJ5AELD37AK543IUAZO4&utm_source=www.jd.com&utm_medium=tuiguang&utm_campaign=t_pcmtiaozhuan_pcmtiaozhuan");

        btn_icon = (Button) findViewById(R.id.btn_icon);

        mWebView.setWebChromeClient(mChromeClient);
        mWebView.setWebViewClient(mWebViewClient);

        //比如这里做一个简单的判断，当页面发生滚动，显示那个Button
        mWebView.setOnScrollChangedCallback(new MyWebView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy > 0) {
                    btn_icon.setVisibility(View.VISIBLE);
                } else {
                    btn_icon.setVisibility(View.GONE);
                }
            }
        });

        btn_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.setScrollY(0);
                btn_icon.setVisibility(View.GONE);
            }
        });

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);//设定支持viewport
        webSettings.setLoadWithOverviewMode(true);   //自适应屏幕
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);//设定支持缩放

        CookieManager cm = CookieManager.getInstance();
        // 开启Javascript脚本
        webSettings.setJavaScriptEnabled(true);

        // 启用localStorage 和 essionStorage
        webSettings.setDomStorageEnabled(true);

        // 开启应用程序缓存
        webSettings.setAppCacheEnabled(true);
        String appCacheDir = this.getApplicationContext()
                .getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCacheDir);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 10);// 设置缓冲大小，我设的是10M
        webSettings.setAllowFileAccess(true);

        // 启用Webdatabase数据库
        webSettings.setDatabaseEnabled(true);
        String databaseDir = this.getApplicationContext()
                .getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(databaseDir);// 设置数据库路径

        // 启用地理定位
        webSettings.setGeolocationEnabled(true);
        // 设置定位的数据库路径
        webSettings.setGeolocationDatabasePath(databaseDir);

        // 开启插件（对flash的支持）
//        webSettings.setPluginsEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

    }

    private WebChromeClient mChromeClient = new WebChromeClient() {

        private View myView = null;
        private CustomViewCallback myCallback = null;

        // 配置权限 （在WebChromeClinet中实现）
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

        // 扩充数据库的容量（在WebChromeClinet中实现）
        @Override
        public void onExceededDatabaseQuota(String url,
                                            String databaseIdentifier, long currentQuota,
                                            long estimatedSize, long totalUsedQuota,
                                            WebStorage.QuotaUpdater quotaUpdater) {

            quotaUpdater.updateQuota(estimatedSize * 2);
        }

        // 扩充缓存的容量
        @Override
        public void onReachedMaxAppCacheSize(long spaceNeeded,
                                             long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {

            quotaUpdater.updateQuota(spaceNeeded * 2);
        }

        // Android 使WebView支持HTML5 Video（全屏）播放的方法
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            if (myCallback != null) {
                myCallback.onCustomViewHidden();
                myCallback = null;
                return;
            }

            ViewGroup parent = (ViewGroup) mWebView.getParent();
            parent.removeView(mWebView);
            parent.addView(view);
            myView = view;
            myCallback = callback;
            mChromeClient = this;
        }

        @Override
        public void onHideCustomView() {
            if (myView != null) {
                if (myCallback != null) {
                    myCallback.onCustomViewHidden();
                    myCallback = null;
                }

                ViewGroup parent = (ViewGroup) myView.getParent();
                parent.removeView(myView);
                parent.addView(mWebView);
                myView = null;
            }
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        // 处理页面导航
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            // 记得消耗掉这个事件。给不知道的朋友再解释一下，
            // Android中返回True的意思就是到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //获取屏幕高度，另外因为网页可能进行缩放了，所以需要乘以缩放比例得出的才是实际的尺寸
            Log.e("HEHE", mWebView.getContentHeight() * mWebView.getScale() + "");
            CookieManager cookieManager = CookieManager.getInstance();
            String CookieStr = cookieManager.getCookie(url);
            Log.e("HEHE", "Cookies = " + CookieStr);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    };

    // 浏览网页历史记录
    // goBack()和goForward()
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showShort(webViewUtils.this, "再按一次退出", 2);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }
}
