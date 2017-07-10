package com.example.wb.testdemo.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    @InjectView(R.id.img)
    ImageView img;

    private int mHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldanimal);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.button)
    public void onClick() {

        View view = ll;
//        Animation operatingAnim = AnimationUtils.loadAnimation(GoldAnimaActivity.this, R.anim.test);
//        LinearInterpolator interpolator = new LinearInterpolator();
//        operatingAnim.setInterpolator(interpolator);
//        textview.startAnimation(operatingAnim);
        view.animate().setInterpolator(new MyTimInputer()).scaleX(1).scaleY(1).setDuration(1000);

//        ObjectAnimator animator = ObjectAnimator.ofFloat(textview,"scaleX",1);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textview,"scaleY",1);
//        animator.setInterpolator(new MyTimInputer());
//        animator.setDuration(1000);
//        animator.start();
//        animator1.setInterpolator(new MyTimInputer());
//        animator1.setDuration(1000);
//        animator1.start();


    }
}
