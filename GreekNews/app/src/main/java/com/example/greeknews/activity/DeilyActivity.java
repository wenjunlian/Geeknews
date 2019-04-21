package com.example.greeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.greeknews.R;
import com.example.greeknews.utils.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeilyActivity extends AppCompatActivity {

    private static final String TAG = "DeilyActivity";
    private Toolbar mToolbar;
    private MaterialCalendarView mCalendarView;
    /**
     * 确定
     */
    private Button mBtn;
    private CalendarDay currentDate;
    private String newMonth;
    private String newDay;
    private String mDate;
    private ImageView mIv;
    private int mYear;
    private int mMonth;
    private int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deily);
        initView();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        mBtn = (Button) findViewById(R.id.btn);
        mCalendarView.addDecorator(new TodayDecorator());
        mIv = (ImageView) findViewById(R.id.iv);

        //编辑日历属性
        mCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存

        // 设置点击日期的监听
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget,
                                       @NonNull CalendarDay date,
                                       boolean selected) {
                Date newDate = date.getDate();
                //设置日期格式
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                mDate = df.format(newDate);
                Toast.makeText(DeilyActivity.this,  "你选中的是：" +mDate, Toast.LENGTH_LONG).show();

            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("date",mDate);
                setResult(200,intent);
                finish();
            }
        });
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
