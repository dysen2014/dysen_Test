package com.dysen.test.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.test.R;

public class ContentProviderDemo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        logic();
    }

    @Override
    protected void logic() {
        super.logic();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME}, null, null, null);

        if (cursor != null){
            while (cursor.moveToNext()){

                int id = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
                LogUtils.i("id="+id+"\tname="+name);

                Cursor cursor1 = contentResolver.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + "=" + id, null, null);
                if (cursor1 != null){
                    while (cursor1.moveToNext()){
                        int type = cursor1.getInt(cursor1.getColumnIndex(Phone.TYPE));
                        if (type == Phone.TYPE_HOME){
                            LogUtils.i("家庭电话："+cursor1.getString(cursor1.getColumnIndex(Phone.NUMBER)));
                        }else if (type == Phone.TYPE_MOBILE){
                            LogUtils.i("手机号码："+cursor1.getString(cursor1.getColumnIndex(Phone.NUMBER)));
                        }else {
                            LogUtils.i("其它："+cursor1.getString(cursor1.getColumnIndex(Phone.NUMBER)));
                        }
                    }
                    cursor1.close();
                }

                Cursor cursor2 = contentResolver.query(Phone.CONTENT_URI, new String[]{Email.DATA, Email.TYPE}, Email.CONTACT_ID + "=" + id, null, null);
                if (cursor2 != null){
                    while (cursor2.moveToNext()){
                        int type = cursor2.getInt(cursor2.getColumnIndex(Email.TYPE));
                        if (type == Email.TYPE_WORK){
                            LogUtils.i("工作邮箱："+cursor2.getString(cursor2.getColumnIndex(Email.DATA)));
                        }else{
                            LogUtils.i("个人邮箱："+cursor2.getString(cursor2.getColumnIndex(Email.DATA)));
                        }
                    }
                    cursor2.close();

                }
            }
            cursor.close();
        }
    }
}
