package com.example.wb.testdemo.animation;

import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Zhangchen on 2017/6/29.
 */

public class GoldAnimaActivity extends AppCompatActivity {

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.textview)
    TextView textview;
    @InjectView(R.id.ll)
    LinearLayout ll;

    private int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldanimal);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX",0,1);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleY",0,1);
        View view = textview;
        view.animate().setInterpolator(new MyTimInputer()).scaleX(1).scaleY(1).setDuration(1000);
    }
}
