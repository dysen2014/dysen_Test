package com.dysen.test.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.dysen.lib.util.LogUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dy on 2016/12/22.
 */

public class MyImageLoader {

    private ImageView mImageView;
    private String mUrl;

    private ListView mListView;
    private Set<NewAysincTak2> mTask;

    private LruCache<String, Bitmap> lruCache;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(mImageView.getTag()==null){//初始化时并未设置Tag

                mImageView.setTag(mUrl);

            }
            if (mImageView.getTag().equals(mUrl)) {//避免时序上的混乱
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
            LogUtils.i(mUrl+"\n"+mImageView.getTag());
        }
    };

    public MyImageLoader(ListView listView) {

        mListView = listView;
        mTask = new HashSet<>();

        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;

        //初始化
        lruCache = new LruCache<String, Bitmap>(cacheSize){

            @Override
            protected int sizeOf(String key, Bitmap value) {

                //在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }

    /**
     * 增加到缓存
     * @param url
     * @param bitmap
     */
    private void addBitmap2Cache(String url, Bitmap bitmap){

        if (getBitmap4Cache(url) == null){
            lruCache.put(url, bitmap);
        }
    }

    /**
     * 从缓存中获取 bitmap 对象
     * @param url
     * @return
     */
    private Bitmap getBitmap4Cache(String url){

        return lruCache.get(url);
    }

    public void showImageByThread(ImageView imageView, final String url){

       this.mImageView = imageView;
       this.mUrl = url;

        //从缓存中获取图片
        Bitmap bitmap = getBitmap4Cache(url);
        if (bitmap == null){//缓存中没有 就需要下载
            new Thread(){

                @Override
                public void run() {
                    super.run();

                    Bitmap bitmap = getBitmap4URL(url);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }
            }.start();
        }else {//设置图片
            imageView.setImageBitmap(bitmap);
        }
   }

    /**
     * 获取网路图片 并返回bitmap对象
     * @param urlStr
     * @return
     */
    public Bitmap getBitmap4URL(String urlStr){
        Bitmap bitmap;
        InputStream inputStream = null;

        try {
            URL url = new URL(urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);

            httpURLConnection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void showImageByAsyncTask(ImageView imageView, final String url){

        //从缓存中获取图片
        Bitmap bitmap = getBitmap4Cache(url);
        if (bitmap == null){//缓存中没有 就需要下载
            new NewAysincTak(imageView, url).execute(url);
//            imageView.setImageResource(R.mipmap.ic_launcher);
        }else {//设置图片
            imageView.setImageBitmap(bitmap);
        }
    }

    class NewAysincTak extends AsyncTask<String, Void, Bitmap> {

        private ImageView mImageView;
        private String mUrl;

        public NewAysincTak(ImageView imageView, String url) {
            mImageView = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

           String url = params[0];
            Bitmap bitmap = getBitmap4URL(params[0]);

            if (bitmap != null){
                //把下载好的 图片bitmap 添加到缓存中去
                addBitmap2Cache(url, bitmap);
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            if(mImageView.getTag()==null){//初始化时并未设置Tag
                mImageView.setTag(mUrl);
            }
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(bitmap);
            }
            LogUtils.i(mUrl+"\n"+mImageView.getTag());
        }
    }

    /**
     * 用来加载从start到end的所有图片
      * @param start
     * @param end
     */
    public void loadImages(int start, int end) {

        for (int i = start; i < end; i++) {

            String url = MyAdapter.urls[i];
            //从缓存中获取图片
            Bitmap bitmap = getBitmap4Cache(url);

            if (bitmap == null){//缓存中没有 就需要下载

                NewAysincTak2 task = new NewAysincTak2(url);
                task.execute(url);
                mTask.add(task);
            }else {//设置图片

                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                if (imageView != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public void cancelAllTask() {
        if (mTask != null){
            for (NewAysincTak2 task : mTask){
                task.cancel(false);
            }
        }
    }

    class NewAysincTak2 extends AsyncTask<String, Void, Bitmap> {

        private String mUrl;

        public NewAysincTak2(String url) {

            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            String url = params[0];
            Bitmap bitmap = getBitmap4URL(url);

            if (bitmap != null){
                //把下载好的 图片bitmap 添加到缓存中去
                addBitmap2Cache(url, bitmap);
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {


            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            System.out.println("imageView="+imageView);
            if(imageView !=null && bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
            super.onPostExecute(bitmap);
        }
    }
}
