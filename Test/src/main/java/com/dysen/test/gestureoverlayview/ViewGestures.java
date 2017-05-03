package com.dysen.test.gestureoverlayview;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.dialog.CommonDialog;
import com.dysen.lib.util.kjframe.KJDB;
import com.dysen.test.R;
import com.dysen.test.gesturebuilder.Gestures;
import com.dysen.test.gesturebuilder.MyListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2016/12/28.
 */

public class ViewGestures extends BaseActivity {

	ListView lv;
	TextView txt;

	private KJDB dbGestures;
	List<Gestures> gesturesList;
	private List<String> gesNames = new ArrayList<String>();//保存手写的名称集合
	private List<Bitmap> gesPics = new ArrayList<Bitmap>();//保存转换为手写的图片的集合
	private MyListAdapter adapter;
	private String gesPath;

	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);

		setContentView(R.layout.activity_common_listview);

		init();
		logic();
	}

	@Override
	protected void logic() {
		super.logic();
		showShort("logic", 5);
		gesPath = new File(Environment.getExternalStorageDirectory(),
				"gestures").getAbsolutePath();
		GestureLibrary gestureLibrary = GestureLibraries.fromFile(gesPath);
//				GestureLibraries.fromRawResource(atiy, R.raw.gestures);

		adapter = new MyListAdapter(atiy, gesNames, gesPics);
		getExitGesture(gestureLibrary, gesPath);

		lv.setAdapter(adapter);
	}

	@Override
	protected void init() {
		super.init();

		lv = bindView(R.id.lv_common_listview);
		txt = bindView(R.id.txt_common_listview);

		showShort("init", 5);
	}

	//读取SD卡中的/sdcard/gestures里建立的手写，并显示在ListView中
	public void getExitGesture(GestureLibrary lib, String gesPath) {
		Log.i(TAG, "getExitGesture()");
		gesNames.clear();
		gesPics.clear();
		File f = new File(gesPath);
//		lib = GestureLibraries.fromFile(f);
		if (f.exists()) {
			if (!lib.load()) {
				showShort("加载失败！！", 2);
				lv.setVisibility(View.GONE);
				txt.setVisibility(View.VISIBLE);
				txt.setText("");
			} else {
				Object[] obj = lib.getGestureEntries().toArray();
				for (int i = 0; i < obj.length; i++) {
					ArrayList<Gesture> al = lib.getGestures(obj[i].toString());

					for (int j = 0; j < al.size(); j++) {

						// 手势名称
						gesNames.add(obj[i].toString());
						Gesture gs = (Gesture) al.get(j);
						//将手写转成Bitmap图片
						gesPics.add(gs.toBitmap(50, 50, 12, Color.MAGENTA));
					}
				}
				lv.setVisibility(View.GONE);
				txt.setVisibility(View.VISIBLE);
			}
		} else {
			showShort("文件不存在！", 2);
			CommonDialog commonDialog = new CommonDialog(atiy);
			commonDialog.setTitle("无手势记录");
			commonDialog.setSingle(true);
			commonDialog.show();
			showLong("无手势记录", 1);
			lv.setVisibility(View.GONE);
			txt.setVisibility(View.VISIBLE);
		}
		adapter.notifyDataSetChanged();
	}
}
