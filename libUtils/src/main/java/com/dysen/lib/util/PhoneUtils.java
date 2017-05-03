package com.dysen.lib.util;

import android.content.Context;
import android.location.LocationManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy on 2016-09-01.
 */
public class PhoneUtils {

    /**
     * Gps是否可用
     * @param context
     * @return
     */
    public static final boolean isGpsEnable(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * dysen 2015-6-10 上午9:19:56 info:
     */
    public static void main(String[] args) {
        isMobileNo("13394162501");

    }


    /**
     * dysen 2015-6-10 上午10:14:08 info: 判断手机号码是否合格
     * 13、15、18三个号段共30个号段，154、181、183、184暂时没有，加上147共27个。
     */
    public static boolean isMobileNo(String mobiles) {

        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9])|(14[7]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches() + "---");

        return m.matches();

    }

    /**
     * dysen 2015-6-10 上午10:14:08 info: 判断手机号码是否合格
     * 13、15、18三个号段共30个号段，154、181、183、184暂时没有，加上147共27个。
     */
    public static boolean isMobileNo2(String mobiles) {
        String value = mobiles;// 手机号

        String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";

        Pattern p = Pattern.compile(regExp);

        Matcher m = p.matcher(value);
        System.out.println(m.matches() + "---");
        return m.find();// boolean

    }
}
