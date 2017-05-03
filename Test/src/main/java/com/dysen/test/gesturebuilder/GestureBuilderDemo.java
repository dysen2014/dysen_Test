package com.dysen.test.gesturebuilder;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GestureBuilderDemo extends BaseActivity {

	private static final  String TAG="GestureBuilderDemo";

	private Button mButton1, mButton2;
	private GestureOverlayView mGestureOverlayView;//手写绘制区
	private EditText mEditText;
	private Gesture ges;
	private GestureLibrary lib;
	private String gesPath;

	// ----------------------------
	private ImageView mImageView;//拉动式抽屉
	private SlidingDrawer mDrawer;//拉动式抽屉的手柄
	private ListView mListView;//拉动式抽屉的内容
	private List<String> gesNames = new ArrayList<String>();//保存手写的名称集合
	private List<Bitmap> gesPics = new ArrayList<Bitmap>();//保存转换为手写的图片的集合

	//------------------- -------
	private LinearLayout layout_bottom;//底部的2个按钮布局
	private LinearLayout layout_top;//顶部的2个按钮布局
	private MyListAdapter adapter;//适配器


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setContentView(R.layout.activity_gesture_builder);
		/* 查看SDCard是否存在 */
		if (!Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			Toast.makeText(this, "SDCard不存在！", Toast.LENGTH_SHORT).show();
			this.finish();
		}

        /* 取得系统默认的GestureLibrary的文件路径 */
		gesPath = new File(Environment.getExternalStorageDirectory(),
				"gestures").getAbsolutePath();

		mButton1 = (Button) this.findViewById(R.id.button1_id);
		mButton2 = (Button) this.findViewById(R.id.button2_id);
		mGestureOverlayView = (GestureOverlayView) this
				.findViewById(R.id.myGestures1);
		mEditText = (EditText) this.findViewById(R.id.edit_id);
		mButton1.setEnabled(false);
		mImageView = (ImageView) this.findViewById(R.id.handler);

		mDrawer = (SlidingDrawer) this.findViewById(R.id.slidingDreaer);
		mListView = (ListView) this.findViewById(R.id.lv_common_listview);
		mEditText.setOnKeyListener(keyListener);

		mGestureOverlayView.addOnGestureListener(onGestureListener);
		mButton1.setOnClickListener(listener1);
		mButton2.setOnClickListener(listener2);
		layout_bottom=(LinearLayout)this.findViewById(R.id.linear_botton_id);
		layout_top=(LinearLayout)this.findViewById(R.id.linear_top_id);

		adapter=new MyListAdapter(this, gesNames, gesPics);

		getExitGesture();//读取SD卡中的/sdcard/gestures里建立的手写，并显示在ListView中
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(list_listener);

		mDrawer.setOnDrawerOpenListener(onDrawerOpenListener_open);
		mDrawer.setOnDrawerCloseListener(onDrawerCloseListener_close);
	}
	AdapterView.OnItemClickListener list_listener=new AdapterView.OnItemClickListener(){

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		                        long arg3) {
			// TODO Auto-generated method stub
			Toast.makeText(GestureBuilderDemo.this, "GestureName:"+gesNames.get(arg2),
					Toast.LENGTH_SHORT).show();
		}

	};
	//抽屉打开
	SlidingDrawer.OnDrawerOpenListener onDrawerOpenListener_open=new SlidingDrawer.OnDrawerOpenListener(){

		public void onDrawerOpened() {
			// TODO Auto-generated method stub
//			mImageView.setImageResource(R.drawable.close);
			layout_bottom.setVisibility(View.GONE);
			layout_top.setVisibility(View.GONE);
			mGestureOverlayView.setVisibility(View.GONE);

			getExitGesture();
		}

	};
	//抽屉关闭
	SlidingDrawer.OnDrawerCloseListener onDrawerCloseListener_close=new SlidingDrawer.OnDrawerCloseListener(){

		public void onDrawerClosed() {
			// TODO Auto-generated method stub
//			mImageView.setImageResource(R.drawable.open);
			layout_bottom.setVisibility(View.VISIBLE);
			layout_top.setVisibility(View.VISIBLE);
			mGestureOverlayView.setVisibility(View.VISIBLE);
		}

	};
	//读取SD卡中的/sdcard/gestures里建立的手写，并显示在ListView中
	public void getExitGesture() {
		Log.i(TAG, "getExitGesture()");
		gesNames.clear();
		gesPics.clear();
		File f = new File(gesPath);
		lib = GestureLibraries.fromFile(f);
		if (f.exists()) {
			if (!lib.load()) {
				Toast.makeText(GestureBuilderDemo.this, "加载失败！！",
						Toast.LENGTH_SHORT).show();
			} else {
				Object[] obj = lib.getGestureEntries().toArray();
				for (int i = 0; i < obj.length; i++) {
					ArrayList<Gesture> al = lib.getGestures(obj[i].toString());
					//  Log.i(TAG, "i="+i);
					for (int j = 0; j < al.size(); j++) {
						//      Log.i(TAG, "j="+j);
						//      Log.i(TAG, "obj[i].toString()==="+obj[i].toString());
						// 手写名称
						gesNames.add(obj[i].toString());
						Gesture gs = (Gesture) al.get(j);
						//将手写转成Bitmap图片
						gesPics.add(gs.toBitmap(50, 50, 12, Color.MAGENTA));
					}
				}
			}

		} else {
			Toast.makeText(GestureBuilderDemo.this, "文件不存在！",
					Toast.LENGTH_SHORT).show();
		}

		adapter.notifyDataSetChanged();

	}

	GestureOverlayView.OnGestureListener onGestureListener = new GestureOverlayView.OnGestureListener() {

		public void onGesture(GestureOverlayView overlay, MotionEvent event) {
			// TODO Auto-generated method stub

		}

		public void onGestureCancelled(GestureOverlayView overlay,
		                               MotionEvent event) {
			// TODO Auto-generated method stub

		}

		public void onGestureEnded(GestureOverlayView overlay, MotionEvent event) {
			// TODO Auto-generated method stub
			ges = overlay.getGesture();
			if (ges != null
					&& mEditText.getText().toString().trim().length() != 0) {
				mButton1.setEnabled(true);
			}
		}

		public void onGestureStarted(GestureOverlayView overlay,
		                             MotionEvent event) {
			// TODO Auto-generated method stub
			ges = null;
			mButton1.setEnabled(false);
		}

	};
	View.OnKeyListener keyListener = new View.OnKeyListener() {

		public boolean onKey(View arg0, int arg1, KeyEvent arg2) {
			// TODO Auto-generated method stub
			if (ges != null
					&& mEditText.getText().toString().trim().length() != 0) {
				mButton1.setEnabled(true);
			} else {
				mButton1.setEnabled(false);
			}
			return false;
		}

	};

	View.OnClickListener listener1 = new View.OnClickListener() {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String gestureName = mEditText.getText().toString().trim();
			lib = GestureLibraries.fromFile(gesPath);
			File f = new File(gesPath);
			if (!f.exists()) {
                /* 文件不存在就直接写入 */
				lib.addGesture(gestureName, ges);
				if (lib.save()) {
					mEditText.setText("");
					mGestureOverlayView.clear(true);
					mButton1.setEnabled(false);
					Toast.makeText(GestureBuilderDemo.this,
							"保存成功，路径为：" + gesPath, Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(GestureBuilderDemo.this, "保存失败！",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				// 文件存在时，先读取已经存在的Gesture
				if (lib.load()) {
                    /* 如果Library中存在相同名称，则先将其移除再写入 */
					Set<String> set = lib.getGestureEntries();
					if (set.contains(gestureName)) {
						ArrayList<Gesture> list = lib.getGestures(gestureName);
						for (int i = 0; i < list.size(); i++) {
							//删除手写数据
							lib.removeGesture(gestureName, list.get(i));
						}
					}
					//新增手写数据
					lib.addGesture(gestureName, ges);
					// 保存写入手写数据
					if (lib.save()) {
						mEditText.setText("");
						mGestureOverlayView.clear(true);
						mButton1.setEnabled(false);
						Toast.makeText(GestureBuilderDemo.this,
								"保存成功，路径为：" + gesPath, Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(GestureBuilderDemo.this, "保存失败！",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(GestureBuilderDemo.this, "加载失败！",
							Toast.LENGTH_SHORT).show();
				}

			}
			mDrawer.toggle();

		}

	};
	View.OnClickListener listener2 = new View.OnClickListener() {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			mEditText.setText("");
			mGestureOverlayView.clear(true);
			mButton1.setEnabled(false);
		}

	};
}
