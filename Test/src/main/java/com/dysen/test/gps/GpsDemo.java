package com.dysen.test.gps;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.lib.util.MyDateUtils;
import com.dysen.test.R;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GpsDemo extends BaseActivity {

    Location location;
    LocationManager locationManager;
    TextView show;
    Switch sw;
    Handler handler = new Handler();
    MyRunnable myRunnable = new MyRunnable();
    Handler handler2 = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Location location = (Location) msg.obj;
            updateView(location);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_demo);

        show = (TextView) findViewById(R.id.editText2);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        init();
        //判断是否开启 GPS
        if (isOpen(this)) {//已开启

        } else {//未开启
            openGPS(this);//开启
        }

        getGPSConfi();//获取经纬度数据
    }

    @Override
    protected void init() {
        super.init();

        sw = bindView(R.id.sw);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sw.isChecked())
                    handler.postDelayed(myRunnable, 5000);
                else
                    handler.removeCallbacks(myRunnable);
            }
        });
    }

    public void updateView(Location newLocation) {

        if (newLocation != null) {
            double latitude, longitude;
            latitude = newLocation.getLatitude();
            longitude = newLocation.getLongitude();
            StringBuilder sb = new StringBuilder();
            String str = "";

            sb.append("实时位置信息：\n");
            sb.append("经度：");
            sb.append(longitude);
            sb.append("\n纬度：");
            sb.append(latitude);
            sb.append("\n高度：");
            sb.append(newLocation.getAltitude());
            sb.append("\n速度：");
            sb.append(newLocation.getSpeed());
            sb.append("\n方向：");
            sb.append(newLocation.getBearing());
            sb.append("\n时间：");
            sb.append(MyDateUtils.formatDate(new Date(), "HH:mm:ss ms"));
//            ToastUtils.showShort(this, sb.toString(), 1);
            LogUtils.i(sb.toString());

            Geocoder gc = new Geocoder(this, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = gc.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            LogUtils.i("locationList.size()="+locationList.size());
            if (locationList.size()<=0){

            }else {
                Address address = locationList.get(0);//得到Address实例
                LogUtils.i(TAG, "address =" + address);
                String countryName = address.getCountryName();//得到国家名称，比如：中国
                LogUtils.i(TAG, "countryName = " + countryName);
                String locality = address.getLocality();//得到城市名称，比如：北京市
                LogUtils.i(TAG, "locality = " + locality);
                for (int i = 0; address.getAddressLine(i) != null; i++) {
                    String addressLine = address.getAddressLine(i);//得到周边信息，包括街道等，i=0，得到街道名称

                    if (i < 2) {
                        str += addressLine;
                    } else {
//                    return;
                    }
                }
                LogUtils.i(TAG, "addressLine = " + str);
                sb.append("\n地址：" + str);
            }
            show.setText(sb.toString());
        } else {
            show.setText("获取失败");
        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            //当位置发生改变时调用
            updateView(location);
        }


        @Override
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {
            //当状态改变时调用

        }


        @Override
        public void onProviderEnabled(String provider) {
            //当适配器有效时调用
            if (ActivityCompat.checkSelfPermission(GpsDemo.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GpsDemo.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            updateView(locationManager.getLastKnownLocation(provider));
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            //当适配器禁用时调用
            updateView(null);
        }
    };

    /**
     * GPS功能已经打开-->根据GPS去获取经纬度
     */
    public Location getGPSConfi() {

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            /** 第二个参数minTime: 两次定位用户位置的最小间隔时间（毫秒）
             * 第三个参数minDistance: 两次定位用户位置的最小距离（米）
             */
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            /** 第二个参数minTime: 两次定位用户位置的最小间隔时间（毫秒）
              * 第三个参数minDistance: 两次定位用户位置的最小距离（米）
              */
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

//        if (location != null) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            show.setText("经纬度:" + latitude + "--" + longitude+"方向："+location.getBearing());
//        } else {
//            show.setText("未获取到经纬度数据");
//        }
        return location;
    }
    
    /**
     * 开启手机GPS
     */
    public void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvide");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));

        try {
            //使用PendingIntent发送广播告诉手机去开启GPS功能
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断手机GPS是否开启
     * @return
     */
    public boolean isOpen(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //通过GPS卫星定位,定位级别到街
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //通过WLAN或者移动网络确定位置
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {

            show.setText("");
            getGPSConfi();

            Message msg = handler2.obtainMessage();
            msg.obj = location;
            msg.sendToTarget();

            handler.postDelayed(myRunnable, 5000);
        }
    }

}
