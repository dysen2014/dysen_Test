package com.dysen.test.emoji;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;
import com.luolc.emojirain.EmojiRainLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmojiRainDemo extends BaseActivity implements AdapterView.OnItemClickListener {
	String name;
	GridView gridView;
	private List<Map<String, Object>> dataList;
	private int[] icons={0,0,0,0,0,0,0,0,0};
	private int[] icon= {R.drawable.ic_num_1, R.drawable.ic_num_2, R.drawable.ic_num_3, R.drawable.ic_num_4,
			R.drawable.ic_num_5, R.drawable.ic_num_6, R.drawable.ic_num_7, R.drawable.ic_num_8, R.drawable.ic_num_9};
	private String[] iconName = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
	private SimpleAdapter simpleAdapter;

	@BindView(R.id.group_emoji_container)
	EmojiRainLayout mContainer;

	@BindView(R.id.et_per)
	EditText mPer;

	@BindView(R.id.et_duration) EditText mDuration;

	@BindView(R.id.et_drop_duration) EditText mDropDuration;

	@BindView(R.id.et_frequency) EditText mFrequency;
	@BindView(R.id.edt_content) EditText mEdtContent;

	@OnClick(R.id.btn_drop)
	void onClickDrop() {
		if (!checkInputParams(mPer, mDuration, mDropDuration, mFrequency)) {
			Toast.makeText(this, R.string.please_input_all_params, Toast.LENGTH_SHORT).show();
			return;
		}
		mContainer.stopDropping();
		int per = Integer.parseInt(mPer.getText().toString());
		int duration = Integer.parseInt(mDuration.getText().toString());
		int dropDuration = Integer.parseInt(mDropDuration.getText().toString());
		int frequency = Integer.parseInt(mFrequency.getText().toString());
//		mContainer.setPer(per);
//		mContainer.setDuration(duration);
//		mContainer.setDropDuration(dropDuration);
//		mContainer.setDropFrequency(frequency);
		mContainer.setPer(1);
		mContainer.setDuration(10000);
		mContainer.setDropDuration(4000);
		mContainer.setDropFrequency(500);

		mContainer.startDropping();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emoji_rain);

		init();
		logic();
	}

	private List<Map<String, Object>> getData() {

		for (int i=0; i<icons.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", icons[i]);
			map.put("txt", iconName[i]);
			dataList.add(map);
		}

		return dataList;
	}

	@Override
	protected void init() {
		super.init();

		gridView = bindView(R.id.grid_view);
		dataList = new ArrayList<Map<String, Object>>();
	}

	@Override
	protected void logic() {
		super.logic();

		ButterKnife.bind(this);

		mEdtContent.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {

				boolean flag = false;
				name = mEdtContent.getText().toString().trim();
				if (flag = name.contains("钩子")){
					mContainer.addEmoji(R.drawable.emoji_1_3);
				}else if (flag = name.contains("袜子")){
					mContainer.addEmoji(R.drawable.emoji_2_3);
				}else if (flag = name.contains("帽子")){
					mContainer.addEmoji(R.drawable.emoji_3_3);
				}else if (flag = name.contains("树")){
					mContainer.addEmoji(R.drawable.emoji_4_3);
				}else if (flag = name.contains("雪人")){
					mContainer.addEmoji(R.drawable.emoji_5_3);
				}

				if (flag) {
					doDrop();
				}
			}
		});

		simpleAdapter = new SimpleAdapter(this, getData(), R.layout.gridview_item,new String[]{"img", "txt"}
				, new int[]{R.id.img_icon, R.id.txt_icon_name});
		gridView.setAdapter(simpleAdapter);

		//设置监听
		gridView.setOnItemClickListener(this);
	}

	/**
	 * 控制下落
	 */
	private void doDrop() {

		mContainer.stopDropping();

		mContainer.setPer(1);
		mContainer.setDuration(4000);
		mContainer.setDropDuration(4000);
		mContainer.setDropFrequency(500);

		mContainer.startDropping();
		mContainer.clearEmojis();
	}

	private boolean checkInputParams(EditText... params) {
		return Stream.of(params).allMatch(param -> param.getText().length() != 0);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

		mContainer.addEmoji(icon[position]);
		doDrop();
	}
}
