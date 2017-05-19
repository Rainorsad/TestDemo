package com.example.wb.testdemo.canves;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wb.testdemo.R;

/**
 * Created by Zhangchen on 2017/5/18.
 */

public class CanvesActivity extends AppCompatActivity{
    private CanvesView canvas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canves);
        canvas = (CanvesView) findViewById(R.id.canvas);
    }
}
