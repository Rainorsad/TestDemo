package com.example.wb.testdemo.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/6/23.
 */

public class AnimationActivity extends AppCompatActivity {

    @InjectView(R.id.ll)
    LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);

        //设置过渡动画
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        //设置布局动画显示权限
        LayoutAnimationController lac = new LayoutAnimationController(sa,0.5F);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);//子view显示顺序，ORDER_NORMAL顺序，ORDER_RANDOM随机，ORDER_REVERSE反序
        ll.setLayoutAnimation(lac);
    }
}
