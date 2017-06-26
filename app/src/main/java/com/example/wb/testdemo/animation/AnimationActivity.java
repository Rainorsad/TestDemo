package com.example.wb.testdemo.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Zhangchen on 2017/6/23.
 */

public class AnimationActivity extends AppCompatActivity {

    @InjectView(R.id.ll)
    LinearLayout ll;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.img)
    ImageView img;

    private Matrix matrix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);

        //设置过渡动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        //设置布局动画显示权限
        LayoutAnimationController lac = new LayoutAnimationController(sa, 0.5F);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);//子view显示顺序，ORDER_NORMAL顺序，ORDER_RANDOM随机，ORDER_REVERSE反序
        ll.setLayoutAnimation(lac);
    }

    @OnClick(R.id.button)
    public void onClick() {
        MyAnimation myAnimation = new MyAnimation();
        myAnimation.start();

    }

    //自定义动画
    class MyAnimation extends Animation {

        private int mCenterWidth;
        private int mCenterHeigh;

        /**
         * 在此方法中定义动画的进行过程
         *
         * @param interpolatedTime 插值器的时间因子，用来控制速率，取值范围0 ~ 1.0
         * @param t                矩阵封装类，用来获得当前的矩阵对象
         */
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            matrix = t.getMatrix();
            AnimationActivity.this.matrix.preScale(1, 1 - interpolatedTime, mCenterWidth, mCenterHeigh);
            Camera camera = new Camera();
            camera.save();
            camera.rotateY(10 * interpolatedTime);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(mCenterWidth, mCenterHeigh);
            matrix.postTranslate(-mCenterWidth, -mCenterHeigh);
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(2000); //设置时长
            setFillAfter(true); //动画结束后保留状态
            setInterpolator(new BounceInterpolator()); //设置增值器
            mCenterHeigh = height / 2;
            mCenterWidth = width / 2;
        }
    }

}
