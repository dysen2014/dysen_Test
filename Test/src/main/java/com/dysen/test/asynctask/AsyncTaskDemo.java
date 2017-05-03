package com.dysen.test.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.views.textView.MarqueeText;
import com.dysen.test.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskDemo extends BaseActivity{

    MyAsyncTask myAsyncTask;
    ListView listView;
    MarqueeText txtHint;
    ProgressBar progressBar;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task);

        init();
        logic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAsyncTask != null && myAsyncTask.getStatus() == AsyncTask.Status.RUNNING){
            //cancel() 方法是将对应的AsyncTask 标记为cancel状态，并不是真正的消除线程的执行
            myAsyncTask.cancel(true);
        }
    }

    @Override
    protected void logic() {
        super.logic();

        myAsyncTask.execute(url);
    }

    @Override
    protected void init() {
        super.init();

        listView = bindView(R.id.lv_asynctask);
        progressBar = bindView(R.id.pro_asynctask);
        txtHint = bindView(R.id.txt_hint_asynctask);

        myAsyncTask = new MyAsyncTask();
        url = "http://192.168.0.12:8080/tImageloader.json";
//                "http://www.imooc.com/api/teacher?type=4&num=30";
    }

    class MyAsyncTask extends AsyncTask<String, Void, List<Bean.DataBean>>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            txtHint.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Bean.DataBean> doInBackground(String... params) {
            if (isCancelled()){
                return null;
            }
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<Bean.DataBean> been) {
            super.onPostExecute(been);

            progressBar.setVisibility(View.GONE);
            txtHint.setVisibility(View.GONE);

            if (isCancelled() || been == null){
                return;
            }
             MyAdapter myAdapter = new MyAdapter(atiy, been, listView);
            listView.setAdapter(myAdapter);

        }
    }

    private List<Bean.DataBean> getJsonData(String url) {

        List<Bean.DataBean> list = new ArrayList<>();

        try {
            String jsonStr = readStream(new URL(url).openStream());
//            LogUtils.i("jsonStr="+jsonStr);
            Bean bean = new Bean();
            bean = JSON.parseObject(jsonStr, Bean.class);
            list = bean.getData();

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过访问网页返回的数据
     * @param is
     * @return
     */
    private String readStream(InputStream is){

        InputStreamReader inputStreamReader = null;
        String result = "";
        String line = "";

        try {
            inputStreamReader = new InputStreamReader(is, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null){
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
