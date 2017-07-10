package com.example.wb.testdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wb.testdemo.R;
import com.example.wb.testdemo.animation.AnimationActivity;
import com.example.wb.testdemo.animation.GoldAnimaActivity;
import com.example.wb.testdemo.canves.CanvesActivity;
import com.example.wb.testdemo.circleview.CircleviewActivity;
import com.example.wb.testdemo.guaguale.GuaguaLeActivity;
import com.example.wb.testdemo.itemDecoration.ItemOritionActivity;
import com.example.wb.testdemo.meituxiuxui.MeituActivity;

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
    @InjectView(R.id.photoshop)
    TextView photoshop;
    @InjectView(R.id.guaguale)
    TextView guaguale;
    @InjectView(R.id.ciecleview)
    TextView circle;
    @InjectView(R.id.animation)
    TextView animation;
    @InjectView(R.id.goldanimation)
    TextView goldanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.viewmotiontest, R.id.erweimatest,R.id.textviewtest,R.id.qqdrawable,R.id.canves,R.id.photoshop,
    R.id.guaguale,R.id.ciecleview,R.id.animation,R.id.goldanimation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.viewmotiontest:
                initclass(ViewMotioneventActivity.class);
                break;
            case R.id.erweimatest:
                break;
            case R.id.textviewtest:
//                initclass(TextViewColorSize.class);
                initclass(ItemOritionActivity.class);
                break;
            case R.id.qqdrawable:
                initclass(QqDrawableActivity.class);
                break;
            case R.id.canves:
                initclass(CanvesActivity.class);
                break;
            case R.id.photoshop:
                initclass(MeituActivity.class);
                break;
            case R.id.guaguale:
                initclass(GuaguaLeActivity.class);
                break;
            case R.id.ciecleview:
                initclass(CircleviewActivity.class);
                break;
            case R.id.animation:
                initclass(AnimationActivity.class);
                break;
            case R.id.goldanimation:
//                initclass(com.example.wb.testdemo.adapter.MainActivity.class);
                initclass(GoldAnimaActivity.class);
                break;
        }
    }

    private void initclass(Class c) {
        Intent it = new Intent(this, c);
        startActivity(it);
    }
}
