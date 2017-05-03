package com.dysen.test.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.progress.FlikerProgressBar;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImgLoadDemo extends BaseActivity{

    TextView txt;
    ImageView imageView;
    ProgressBar progressBar, progressBarH;

    FlikerProgressBar flikerPro;

    String url;
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_load);

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

        url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";

        myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(url);
    }

    @Override
    protected void init() {
        super.init();

        imageView = bindView(R.id.iv_imgload);
        progressBar = bindView(R.id.progressBar);
        progressBarH = bindView(R.id.progressBar_h);
        txt = bindView(R.id.txt_asynctask);

        flikerPro = bindView(R.id.fliker);
    }

    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String url = params[0];

            Bitmap bitmap = null;
            URLConnection urlConnection;
            InputStream inputStream;
            try {
                urlConnection = new URL(url).openConnection();
                SystemClock.sleep(3000);
                inputStream = urlConnection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                //通过 decodeStream 解析输入流
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);

                inputStream.close();
                bufferedInputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i=1;i<=100; i++){
                if (isCancelled()){
                    break;
                }
                publishProgress(i);
                SystemClock.sleep(400);
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if (isCancelled()){
                return;
            }

            progressBarH.setProgress(values[0]);
            LogUtils.i("progress="+values[0]);
            flikerPro.setProgress(values[0]);

            txt.setText(values[0] + "%");
            if (progressBarH.getProgress()==100) {
                progressBarH.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
                flikerPro.finishLoad();
            }else if (values[0] >0)
                progressBar.setVisibility(View.GONE);

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        }
    }
}
