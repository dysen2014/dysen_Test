package com.dysen.test.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dysen.test.R;

import java.util.List;

/**
 * Created by dy on 2016/12/21.
 */

public class MyAdapter extends BaseAdapter implements AbsListView.OnScrollListener{

    List<Bean.DataBean> list;
    Context context;
    LayoutInflater layoutInflater;
    MyImageLoader myImageLoader;
    ListView mListView;

    private int mStart, mEnd;
    public static String[] urls;
    private boolean flagFirst;

    public MyAdapter(Context context, List<Bean.DataBean> list, ListView listView) {
        this.list = list;
        this.context = context;
        mListView = listView;
        flagFirst = true;
        layoutInflater = LayoutInflater.from(context);
        myImageLoader = new MyImageLoader(listView);
        urls = new String[list.size()];//数组初始化
        for (int i=0; i<list.size(); i++){

            urls[i] = list.get(i).getPicSmall();
        }

        //设置监听
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.common_item, null);

            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_common_item);
            viewHolder.title = (TextView) convertView.findViewById(R.id.txt_title_common_item);
            viewHolder.content = (TextView) convertView.findViewById(R.id.txt_content_common_item);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(R.mipmap.ic_launcher);
//        myImageLoader.showImageByThread(viewHolder.iv, list.get(position).getPicSmall());

        myImageLoader.showImageByAsyncTask(viewHolder.iv, list.get(position).getPicSmall());
        viewHolder.title.setText(list.get(position).getName());
        viewHolder.content.setText(list.get(position).getDescription());

        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView title, content;
    }

    //滚动状态
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE){//停止
            //加载可见
            myImageLoader.loadImages(mStart, mEnd);
        }else {
            //停止任务
            myImageLoader.cancelAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;

        //第一次显示时调用
        if (flagFirst && visibleItemCount > 0){
            myImageLoader.loadImages(mStart, mEnd);
            flagFirst = false;
        }
    }
}
