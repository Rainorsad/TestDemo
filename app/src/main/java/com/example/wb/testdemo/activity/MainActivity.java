package com.example.wb.testdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wb.testdemo.R;
import com.example.wb.testdemo.activity.canves.CanvesActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.viewmotiontest)
    TextView viewmotiontest;
    @InjectView(R.id.erweimatest)
    TextView erweimatest;
    @InjectView(R.id.textviewtest)
    TextView textviewtest;
    @InjectView(R.id.qqdrawable)
    TextView qqdrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.viewmotiontest, R.id.erweimatest,R.id.textviewtest,R.id.qqdrawable,R.id.canves})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewmotiontest:
                initclass(ViewMotioneventActivity.class);
                break;
            case R.id.erweimatest:
                break;
            case R.id.textviewtest:
                initclass(TextViewColorSize.class);
                break;
            case R.id.qqdrawable:
                initclass(QqDrawableActivity.class);
                break;
            case R.id.canves:
                initclass(CanvesActivity.class);
                break;
        }
    }

    private void initclass(Class c) {
        Intent it = new Intent(this, c);
        startActivity(it);
    }
}
