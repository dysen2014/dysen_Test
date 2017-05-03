package com.dysen.test.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by dy on 2016/12/23.
 */

public class MyContentProVider extends ContentProvider {

    //在Contenprovider创建后被调用
    @Override
    public boolean onCreate() {
        return false;
    }

    //根据Uri查询出selection制定田间所匹配的全部记录，并且可以指定查询那些列 以书面方式排序
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    /**
     * 返回当前Uri的MIME类型，
     * 如果该Uri对应的数据可能包括多条记录，那么MIME类型字符串，就是以vnd.android.dir/开头
     * 如果该Uri对应的数据只有一条记录，那么MIME类型字符串，就是以vnd.android.cursor.item/开头
     * */
    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    //根据Uri插入value对应的数据
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    //根据Uri删除selection指定的条件所匹配的全部记录
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    //根据Uri 修改selection制定田间所匹配的全部记录
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
