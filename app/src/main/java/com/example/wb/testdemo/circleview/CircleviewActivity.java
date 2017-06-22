package com.example.wb.testdemo.circleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/6/6.
 */

public class CircleviewActivity extends AppCompatActivity {

    @InjectView(R.id.circle)
    CircleImage circle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circleview);
        ButterKnife.inject(this);

//        circle.setImageResource(R.drawable.two);
    }
}
