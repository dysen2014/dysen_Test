package com.dysen.test.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dysen.test.R;

import java.util.List;

/**
 * Created by dy on 2016/12/21.
 */

public class CommonAdapter extends BaseAdapter {

    List<Bean> list;
    Context context;
    LayoutInflater layoutInflater;

    public CommonAdapter(Context context, List<Bean> list) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
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
//        viewHolder.title.setText(list.get(posi ).getContactAddr());

        return convertView;
    }

    class ViewHolder{
        ImageView iv;
        TextView title, content;
    }
}
