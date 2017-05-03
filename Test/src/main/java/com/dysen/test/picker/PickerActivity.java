package com.dysen.test.picker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.dysen.lib.base.BaseActivity;
import com.dysen.test.R;

import java.util.Calendar;

//import android.icu.util.Calendar;

/**
 * 时间，日期选择器
 */
public class PickerActivity extends BaseActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Calendar calendar;
    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

            //获取日历的一个对象
            calendar = Calendar.getInstance();
            //获取年月日时分秒的对象
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);

        setTitle(year+"-"+(month+1)+"-"+day+" "+hour+":"+minute);
        init();
        control();
    }

    private void control() {
        //初始化
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minuteOfHour) {

                setTitle(hourOfDay+":"+minuteOfHour);
            }
        });

        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        }, year, month, day).show();

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfHour) {

                setTitle(hourOfDay+":"+minuteOfHour);
            }
        }, hour, minute, true).show();
    }

    @Override
    protected void init() {
        super.init();
        datePicker = bindView(R.id.data_picker);
        timePicker = bindView(R.id.time_picker);
    }
}
