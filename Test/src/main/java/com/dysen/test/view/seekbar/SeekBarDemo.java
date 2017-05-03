package com.dysen.test.view.seekbar;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class SeekBarDemo extends BaseActivity implements SeekBar.OnSeekBarChangeListener {

	SeekBar seekBar;
	TextView txtFirst, txtSecond;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seek_bar);

		init();
		logic();
	}

	@Override
	protected void logic() {
		super.logic();

		seekBar.setOnSeekBarChangeListener(this);
	}

	@Override
	protected void init() {
		super.init();

		seekBar = bindView(R.id.sk_seekbar);
		txtFirst = bindView(R.id.txt_first_seekbar);
		txtSecond = bindView(R.id.txt_second_seekbar);
	}

	/**
	 * 数值改变时调用
	 * @param seekBar
	 * @param progress
	 * @param fromUser
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

		txtFirst.setText("当前进度："+progress+"\n"+seekBar.getProgress()+"\n"+seekBar.getSecondaryProgress());
		seekBar.getProgress();
		seekBar.getSecondaryProgress();
	}

	/**
	 *  开始拖动
	 * @param seekBar
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	/**
	 *  停止拖动
	 * @param seekBar
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}
}
