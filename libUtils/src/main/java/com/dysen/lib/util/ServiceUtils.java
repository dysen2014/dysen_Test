package com.dysen.lib.util;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;

/**
 * Created by dy on 2016/12/26.
 */

public class ServiceUtils {

    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context)
    {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity)
        {

            //获取网络信息
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * wifi 控制
      * @param context
     */
    public static void controlWifi(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);

        if (wifiManager == null) {
            return ;
        }else {
            if (wifiManager.isWifiEnabled()){
                wifiManager.setWifiEnabled(false);
            }else {
                wifiManager.setWifiEnabled(true);
            }
        }
    }

    /**
     * 获取系统当前音量
     * @param context
     * @return
     */
    public static int getSysVolume(Context context){

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
        int current = audioManager.getStreamVolume(AudioManager.STREAM_RING);

        return current;
    }

    /**
     * 获取系统当前电量
     * @param context
     * @return
     */
    public int getEectricity(Context context){

        BatteryManager batteryManager = (BatteryManager)context.getSystemService(Context.BATTERY_SERVICE);
        int max ;
        int current ;

        return 0;
    }
}
