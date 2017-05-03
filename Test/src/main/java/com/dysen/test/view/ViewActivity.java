package com.dysen.test.view;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ToggleButton;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

public class ViewActivity extends BaseActivity {

    ToggleButton tbBtn;
    ImageView img;
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    private String[] res = {"a", "ab", "abc", "abcd", "b", "bcd"};

    SoundPool sp;//声明一个SoundPool
    private int musicId, musicOff, musicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        init();

        control(autoCompleteTextView);
        control(multiAutoCompleteTextView);
    }

    private void control(View view) {
        switch (view.getId()) {
            case R.id.autoCompleteTextView:
                autoCompleteTextView.setAdapter(getAdapter(res));
                break;
            case R.id.multiAutoCompleteTextView:
                multiAutoCompleteTextView.setAdapter(getAdapter(res));
                //设置以逗号为分隔符作为结束符号
                multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                break;
        }
    }

    /**
     * 初始化 adapter 及 数据源
     */
    private ArrayAdapter<String> getAdapter(String[] res) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);

        return adapter;
    }

    @Override
    protected void init() {
        super.init();
        sp = new SoundPool(5, AudioManager.STREAM_SYSTEM, 5);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        musicOff = sp.load(this, R.raw.light_off, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        musicOn = sp.load(this, R.raw.light_on, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级

        autoCompleteTextView = bindView(R.id.autoCompleteTextView);
        multiAutoCompleteTextView = bindView(R.id.multiAutoCompleteTextView);

        tbBtn = bindView(R.id.tb_btn);
        img = bindView(R.id.light_img);

        tbBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                playMusic(isChecked);//播放音效

                img.setBackgroundResource(isChecked ? R.drawable.ic_light_on : R.drawable.ic_light_off);
            }
        });
    }

    private void playMusic(boolean isChecked) {

        sp.stop(musicId);
        musicId = isChecked ? musicOn : musicOff;
        sp.play(musicId, 1, 1, 0, 0, 1);//id, 左声道， 右声道， 优先级，循环，速率
    }
}
