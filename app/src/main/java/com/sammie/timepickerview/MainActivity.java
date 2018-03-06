package com.sammie.timepickerview;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {
    private WheelView mNumPickerWheel;
    private TimePickerView mTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        mNumPickerWheel = (WheelView) findViewById(R.id.id_num_picker_wheel);

        initNumPickerWheelView();
        initTimePicker();

    }

    public void showNumPickerValue(View view){
        Toast.makeText(MainActivity.this,(100+mNumPickerWheel.getCurrentItem())+"",Toast.LENGTH_SHORT).show();
    }

    private void initNumPickerWheelView() {
        mNumPickerWheel.setAdapter(new NumericWheelAdapter(100, 200));
        mNumPickerWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
//                Toast.makeText(WheelViewActivity.this,(100+index)+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //初始化时间选择器
    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1970, 0, 23);
        final Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 11, 31);
        //时间选择器
        mTimePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String timeString = formatter.format(date);
                Toast.makeText(MainActivity.this, timeString + "", Toast.LENGTH_SHORT).show();
            }
        }).setTitleBgColor(Color.parseColor("#00ADEC"))
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel(getString(R.string.pickerview_year), getString(R.string.pickerview_month), getString(R.string.pickerview_day), "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.TRANSPARENT)
                .setContentSize(18)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(R.color.bg_color) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    public void showTimePicker(View view) {
        if (mTimePickerView != null)
            mTimePickerView.show(view);
    }
}
