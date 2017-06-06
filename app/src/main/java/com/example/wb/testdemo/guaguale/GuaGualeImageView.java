package com.example.wb.testdemo.guaguale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Zhangchen on 2017/5/26.
 */

public class GuaGualeImageView extends ImageView {

    private Bitmap mFgBitmap;
    private Paint mPaint;
    private Canvas mCanvas;
    private Path mPath;
    private Context mContext;
    private int mWidth;
    private int mHeight;

    public GuaGualeImageView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public GuaGualeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public GuaGualeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }


    private void initView() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(50);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPath = new Path();

        mFgBitmap = Bitmap.createBitmap(1000,1000,Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mFgBitmap);
        mCanvas.drawColor(Color.GRAY);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mFgBitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        mCanvas.drawPath(mPath, mPaint);
        invalidate();
        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getMode(widthMeasureSpec);
        mHeight = MeasureSpec.getMode(heightMeasureSpec);
    }
}
