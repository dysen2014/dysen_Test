package com.dysen.test.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.dysen.lib.base.BaseActivity;
import com.dysen.lib.util.LogUtils;
import com.dysen.lib.util.MyRandom;
import com.dysen.test.R;

public class ProgressBarDemo extends BaseActivity implements View.OnClickListener{

    TextView txt;
    SeekBar seekBar;
    Button btnAdd, btnSubtract, btnReset, btnDialog;
    ProgressBar pgbH, pgbLarge, pgbNomal, pgbSmall;
    private ProgressDialog prodialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启用
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_progress_bar);

        setProgressBarVisibility(true);
        setProgressBarIndeterminateVisibility(true);
        //max = 10000
        setProgress(600);
        init();
        logic();
    }

    @Override
    protected void logic() {
        super.logic();

        //获取第一进度值
        int first = pgbH.getProgress();
        //获取第二进度值
        int second = pgbH.getSecondaryProgress();
        //获取最大进度值
        int max = pgbH.getMax();

        txt.setText("第一进度百分比："+(int)(first/(float)max*100)+"%"+"第二进度百分比:"+(int)(second/(float)max*100)+"%");
    }

    @Override
    protected void init() {
        super.init();

        txt = bindView(R.id.txt_progressbar);
        btnAdd = bindView(R.id.btn_add);
        btnSubtract = bindView(R.id.btn_subtract);
        btnReset = bindView(R.id.btn_reset);
        btnDialog = bindView(R.id.btn_dialog);
        pgbH = bindView(R.id.pgb_h);

        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        seekBar = bindView(R.id.seekbar_progressbar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                LogUtils.i("i="+i);
                pgbH.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        /**
         * incrementProgressBy();
         * incrementSecondaryProgressBy();
         * 对 progressbar 进度的加减
         */
        switch (view.getId()){
            case R.id.btn_add:
                pgbH.incrementProgressBy(10);
                pgbH.incrementSecondaryProgressBy(10);
                break;
            case R.id.btn_subtract:
                pgbH.incrementProgressBy(-10);
                pgbH.incrementSecondaryProgressBy(-10);
                break;
            case R.id.btn_reset:
                pgbH.setProgress(50);
                pgbH.setSecondaryProgress(80);
                break;
            case R.id.btn_dialog:
                initProgressDialog();
                break;
        }
        txt.setText("第一进度百分比："+(int)(pgbH.getProgress()/(float)pgbH.getMax()*100)+"%"
                +"第二进度百分比:"+(int)(pgbH.getSecondaryProgress()/(float)pgbH.getMax()*100)+"%");
    }

    private void initProgressDialog() {

        prodialog = new ProgressDialog(ProgressBarDemo.this);
        prodialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        prodialog.setTitle("对话框");
        prodialog.setMessage("欢迎大家");
        prodialog.setIcon(R.drawable.user);
        prodialog.setMax(100);
//                prodialog.setProgress(50);
        prodialog.incrementProgressBy(MyRandom.random2Int(100));
        //进度条是明确显示进度
        prodialog.setIndeterminate(false);
        prodialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showLong("欢迎大家支持我", 3);
            }
        });
        //是否可以通过返回按钮退出对话框
        prodialog.setCancelable(true);
        prodialog.show();
    }
}
