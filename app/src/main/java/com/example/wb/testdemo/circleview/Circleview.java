package com.example.wb.testdemo.circleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.wb.testdemo.R;

/**
 * Created by Zhangchen on 2017/6/6.
 */

public class Circleview extends View{

    private Bitmap mSrcBitmap,mRefBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public Circleview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public Circleview(Context context) {
        super(context);
        initView(context);
    }

    public Circleview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        mBitmapShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(500,200,200,mPaint);
    }
}
