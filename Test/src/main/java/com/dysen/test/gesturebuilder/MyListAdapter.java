package com.dysen.test.gesturebuilder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dysen.lib.util.kjframe.KJDB;
import com.dysen.test.R;

import java.util.List;

/**
 * Created by dy on 2016/12/28.
 */

public class MyListAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> gesNames ;
	private List<Bitmap> gesPics ;

	LayoutInflater layoutInflater;
	KJDB dbGestures;
	Gestures gestures;

	public MyListAdapter(Context mContext,List<String> gesNames,List<Bitmap> gesPics )
	{
		this.mContext=mContext;
		this.gesNames=gesNames;
		this.gesPics=gesPics;

		layoutInflater = LayoutInflater.from(mContext);
		dbGestures = KJDB.create(mContext, "gestures");
		gestures = new Gestures();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return gesNames.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return gesNames.get(arg0);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (viewHolder == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.from(mContext).inflate(R.layout.list, null);
			viewHolder.iv=(ImageView)convertView.findViewById(R.id.img_id);
			viewHolder.text=(TextView)convertView.findViewById(R.id.text_id);
			convertView.setTag(viewHolder);
		}else{

			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.iv.setImageBitmap(gesPics.get(position));
		viewHolder.text.setText(gesNames.get(position));
        String name = gesNames.get(position);
		Bitmap pic = gesPics.get(position);

		gestures.setGesName(name);
		gestures.setGesPic(pic);
		dbGestures.save(gestures);
		return convertView;
	}

	class ViewHolder{
		ImageView iv;
		TextView text;
	}
}
