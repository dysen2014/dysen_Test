package com.dysen.test.view.expand;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;
import com.github.zagum.expandicon.ExpandIconView;


public class ExpandIconViewDemo extends BaseActivity implements GestureDetector.OnGestureListener{

	private ExpandIconView expandIconView1;
	private ExpandIconView expandIconView2;
	private ExpandIconView expandIconView3;
	private GestureDetector gestureDetector;
	private View swipeDetectionView;
	private View click;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expand_icon_view);

		init();
		logic();
	}

	private void setUpSlidingContainer() {
		gestureDetector = new GestureDetector(this, this);
		gestureDetector.setIsLongpressEnabled(false);

		swipeDetectionView = findViewById(R.id.swipe_detector);
		swipeDetectionView.setClickable(true);
		swipeDetectionView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_UP) {
					expandIconView1.setFraction(.5f, true);
					expandIconView2.setFraction(.5f, true);
				}
				return gestureDetector.onTouchEvent(event);
			}
		});
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		float fraction;
		if (Math.signum(distanceY) > 0) {
			fraction = 1f;
		} else {
			fraction = 0f;
		}
		expandIconView1.setFraction(fraction, true);
		expandIconView2.setFraction(fraction, true);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return false;
	}

	@Override
	protected void logic() {
		super.logic();

		click.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				expandIconView3.switchState();
			}
		});

		setUpSlidingContainer();
	}

	@Override
	protected void init() {
		super.init();
		expandIconView1 = bindView(R.id.expand_icon1);
		expandIconView2 = bindView(R.id.expand_icon2);
		expandIconView3 = bindView(R.id.expand_icon3);

		expandIconView1.setFraction(.5f, false);
		expandIconView2.setFraction(.5f, false);

		click = bindView(R.id.click);
	}
}
