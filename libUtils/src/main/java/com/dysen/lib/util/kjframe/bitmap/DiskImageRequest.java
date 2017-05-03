/* * Copyright (c) 2015, 张涛. * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *      http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.dysen.lib.util.kjframe.bitmap;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.os.Handler;import android.os.Looper;import com.dysen.lib.util.kjframe.http.KJAsyncTask;import com.dysen.lib.util.kjframe.utils.FileUtils;import java.io.FileInputStream;import java.io.FileNotFoundException;/** * 本地图片加载 *  * @author kymjs (https://www.kymjs.com/) */public class DiskImageRequest {    private final Handler handle = new Handler(Looper.getMainLooper());    private String mPath;    class DiskImageRequestTask extends KJAsyncTask<Void, Void, byte[]> {        private final int mMaxWidth;        private final int mMaxHeight;        private final BitmapCallBack mCallback;        public DiskImageRequestTask(int maxWidth, int maxHeight,                BitmapCallBack callback) {            mMaxHeight = maxHeight;            mMaxWidth = maxWidth;            mCallback = callback;        }        @Override        protected void onPreExecute() {            super.onPreExecute();            if (mCallback != null) {                mCallback.onPreLoad();            }        }        @Override        protected byte[] doInBackground(Void... params) {            return loadFromFile(mPath, mMaxWidth, mMaxHeight, mCallback);        }    }    public void load(String path, int maxWidth, int maxHeight,            BitmapCallBack callback) {        mPath = path;        DiskImageRequestTask task = new DiskImageRequestTask(maxWidth,                maxHeight, callback);        task.execute();    }    /**     * 从本地载入一张图片     *      * @param path     *            图片的地址     * @throws FileNotFoundException     */    private byte[] loadFromFile(String path, int maxWidth, int maxHeight,            BitmapCallBack callback) {        byte[] data = null;        FileInputStream fis = null;        try {            fis = new FileInputStream(path);            if (fis != null) {                data = FileUtils.input2byte(fis);            }            handleBitmap(data, maxWidth, maxHeight, callback);        } catch (Exception e) {            doFailure(callback, e);        } finally {            FileUtils.closeIO(fis);        }        return data;    }    private Bitmap handleBitmap(byte[] data, int maxWidth, int maxHeight,            BitmapCallBack callback) {        BitmapFactory.Options option = new BitmapFactory.Options();        Bitmap bitmap = null;        if (maxWidth <= 0 && maxHeight <= 0) {            bitmap = BitmapFactory                    .decodeByteArray(data, 0, data.length, option);        } else {            option.inJustDecodeBounds = true;            BitmapFactory.decodeByteArray(data, 0, data.length, option);            int actualWidth = option.outWidth;            int actualHeight = option.outHeight;            // 计算出图片应该显示的宽高            int desiredWidth = getResizedDimension(maxWidth, maxHeight,                    actualWidth, actualHeight);            int desiredHeight = getResizedDimension(maxHeight, maxWidth,                    actualHeight, actualWidth);            option.inJustDecodeBounds = false;            option.inSampleSize = findBestSampleSize(actualWidth, actualHeight,                    desiredWidth, desiredHeight);            Bitmap tempBitmap = BitmapFactory.decodeByteArray(data, 0,                    data.length, option);            // 做缩放            if (tempBitmap != null                    && (tempBitmap.getWidth() > desiredWidth || tempBitmap                            .getHeight() > desiredHeight)) {                bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth,                        desiredHeight, true);                tempBitmap.recycle();            } else {                bitmap = tempBitmap;            }        }        if (bitmap == null) {            doFailure(callback, new RuntimeException("bitmap create error"));        } else {            BitmapConfig.mMemoryCache.putBitmap(mPath, bitmap);            doSuccess(callback, bitmap);        }        return bitmap;    }    /**     * 框架会自动将大于设定值的bitmap转换成设定值，所以需要这个方法来判断应该显示默认大小或者是设定值大小。<br>     * 本方法会根据maxPrimary与actualPrimary比较来判断，如果无法判断则会根据辅助值判断，辅助值一般是主要值对应的。     * 比如宽为主值则高为辅值     *      * @param maxPrimary     *            需要判断的值，用作主要判断     * @param maxSecondary     *            需要判断的值，用作辅助判断     * @param actualPrimary     *            真实宽度     * @param actualSecondary     *            真实高度     * @return 获取图片需要显示的大小     */    private int getResizedDimension(int maxPrimary, int maxSecondary,            int actualPrimary, int actualSecondary) {        if (maxPrimary == 0 && maxSecondary == 0) {            return actualPrimary;        }        if (maxPrimary == 0) {            double ratio = (double) maxSecondary / (double) actualSecondary;            return (int) (actualPrimary * ratio);        }        if (maxSecondary == 0) {            return maxPrimary;        }        double ratio = (double) actualSecondary / (double) actualPrimary;        int resized = maxPrimary;        if (resized * ratio > maxSecondary) {            resized = (int) (maxSecondary / ratio);        }        return resized;    }    /**     * 关于本方法的判断，可以查看我的博客：http://blog.kymjs.com/kjframeforandroid/2014/12/05/02/     *      * @param actualWidth     * @param actualHeight     * @param desiredWidth     * @param desiredHeight     * @return     */    static int findBestSampleSize(int actualWidth, int actualHeight,            int desiredWidth, int desiredHeight) {        double wr = (double) actualWidth / desiredWidth;        double hr = (double) actualHeight / desiredHeight;        double ratio = Math.min(wr, hr);        float n = 1.0f;        while ((n * 2) <= ratio) {            n *= 2;        }        return (int) n;    }    private void doSuccess(final BitmapCallBack callback, final Bitmap bitmap) {        if (callback != null) {            handle.post(new Runnable() {                @Override                public void run() {                    callback.onSuccess(bitmap);                    callback.onFinish();                }            });        }    }    private void doFailure(final BitmapCallBack callback, final Exception e) {        if (callback != null) {            handle.post(new Runnable() {                @Override                public void run() {                    callback.onFailure(e);                    callback.onFinish();                }            });        }    }}