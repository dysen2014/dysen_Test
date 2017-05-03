package com.dysen.lib.sms;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

/**
 * 作者：沈迪 [dysen] on 2016-04-21 10:43.
 * 邮箱：dysen@outlook.com | dy.sen@qq.com
 * 描述：弹出框操作类
 */
public class MessageTools {
    public static void ShowDialog(Context context, String text){
        Builder builder = new Builder(context);
        builder.setMessage(text);

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("取消", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
