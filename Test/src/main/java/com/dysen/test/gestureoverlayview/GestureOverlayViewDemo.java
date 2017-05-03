package com.dysen.test.gestureoverlayview;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;
import com.dysen.test.gesturebuilder.GestureBuilderDemo;

import java.io.File;
import java.util.ArrayList;

public class GestureOverlayViewDemo extends BaseActivity {

	GestureOverlayView gestureOverlayView;
	private String gesPath;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gesture_overlay_view);

		init();
		logic();
	}

	@Override
	protected void logic() {
		super.logic();

		gesPath = new File(Environment.getExternalStorageDirectory(),
				"gestures").getAbsolutePath();
		final GestureLibrary gestureLibrary = GestureLibraries.fromFile(gesPath);
//				GestureLibraries.fromRawResource(atiy, R.raw.gestures);
		gestureLibrary.load();

		gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
			@Override
			public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
				//识别手势
				ArrayList<Prediction> recognizeList = gestureLibrary.recognize(gesture);
				Prediction prediction = recognizeList.get(0);

					if(prediction.score >= 5.0){//score (0.0---10.0)

						showShort("识别结果 相似", 1);
						if (prediction.name.equals("exit")){
							showShort("退出程序", 1);
						}else if (prediction.name.equals("next")){
							showShort("下一个", 1);
						}else if (prediction.name.equals("pervious")){
							showShort("上一个", 1);
						}else {
							showShort("无视别的手势", 1);
						}
					}else {
						showShort("识别结果     不相似", 1);
					}
			}
		});
	}

	@Override
	protected void init() {
		super.init();

		gestureOverlayView = bindView(R.id.gesture_overlay_view);
	}

	@Override
	public void doClick(View view) {
		super.doClick(view);

		switch (view.getId()){
			case R.id.btn_add_gesturebuilder:
				showActivity(atiy, GestureBuilderDemo.class);
				break;
			case R.id.btn_view_gesturebuilder:
				showActivity(atiy, ViewGestures.class);
				break;
		}
	}
}
