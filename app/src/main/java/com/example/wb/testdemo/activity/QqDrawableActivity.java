package com.example.wb.testdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wb.testdemo.R;
import com.example.wb.testdemo.view.QqDrawable;

/**
 * Created by Zhangchen on 2017/5/11.
 */

public class QqDrawableActivity extends AppCompatActivity{

    private QqDrawable qqDrawable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqdrawable);

        qqDrawable = (QqDrawable) findViewById(R.id.qqdrawable);
    }
}
