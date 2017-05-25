package com.example.wb.testdemo.guaguale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/5/24.
 */

public class GuaguaLeActivity extends AppCompatActivity {


    @InjectView(R.id.image)
    GuaguaLeView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guaguale);
        ButterKnife.inject(this);
    }
}
