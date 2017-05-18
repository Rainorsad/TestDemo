package com.example.wb.testdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/3/27.
 */

public class TextViewColorSize extends AppCompatActivity {

    @InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textviewcolor);
        ButterKnife.inject(this);

        String s = "哈哈哈哈哈哈哈哈哈哈哈哈哈";
        SpannableStringBuilder style = new SpannableStringBuilder(s);
        style.setSpan(new BackgroundColorSpan(Color.RED),4,5, Spanned.SPAN_INCLUSIVE_INCLUSIVE); //设置指定位置textview的背景颜色
        style.setSpan(new ForegroundColorSpan(Color.YELLOW),0,2,Spanned.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
        tv.setText(style);
    }
}
