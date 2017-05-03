package com.dysen.test.view.panoramaImageView;

import android.os.Bundle;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.views.panorama_imageview.GyroscopeObserver;
import com.dysen.lib.views.panorama_imageview.PanoramaImageView;
import com.dysen.test.R;

public class PanoramaImageViewDemo extends BaseActivity {

	PanoramaImageView panoramaImageView;
	private GyroscopeObserver gyroscopeObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panorama_image_view);

		init();
		logic();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Register GyroscopeObserver.
		gyroscopeObserver.register(atiy);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Unregister GyroscopeObserver.
		gyroscopeObserver.unregister();
	}

	@Override
	protected void logic() {
		super.logic();

		gyroscopeObserver = new GyroscopeObserver();
		// Set the maximum radian the device should rotate to show image's bounds.
		// It should be set between 0 and π/2.
		// The default value is π/9.
		gyroscopeObserver.setMaxRotateRadian(Math.PI / 9);
		panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

		panoramaImageView.setOnPanoramaScrollListener(new PanoramaImageView.OnPanoramaScrollListener() {
			@Override
			public void onScrolled(PanoramaImageView view, float offsetProgress) {
				// Do something here.
				// The offsetProgress range from -1 to 1, indicating the image scrolls
				// from left(top) to right(bottom).
			}
		});
	}

	@Override
	protected void init() {
		super.init();
		panoramaImageView = bindView(R.id.panorama_image_view);
	}
}
