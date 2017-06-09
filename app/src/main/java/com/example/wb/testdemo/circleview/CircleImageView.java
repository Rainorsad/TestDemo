package com.example.wb.testdemo.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.wb.testdemo.R;

import utils.Utils;

/**
 * Created by Zhangchen on 2017/6/7.
 */

public class CircleImageView extends ImageView{

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static final int DEFAULT_FILL_COLOR = Color.TRANSPARENT;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;

    private Bitmap mSrcBitmap,mRefBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint ;//图片画笔
    private final Paint mBorderPaint = new Paint();//边界画笔
    private final Paint mFillPaint = new Paint();
    private PorterDuffXfermode mXfermode;
    private int mBorderColor = DEFAULT_BORDER_COLOR;//边界颜色
    private int mBorderWidth = DEFAULT_BORDER_WIDTH; //边界宽度
    private int mFillColor = DEFAULT_FILL_COLOR;

    private int mBitmapWidth;
    private int mBitmapHeight;

    private final Matrix mShaderMatrix = new Matrix();
    private float mBorderRadius;
    private float mDrawableRadius;

    private final RectF mBorderRect = new RectF();
    private final RectF mDrawableRect = new RectF();
    private boolean mBorderOverlay;
    private boolean mReady = false;
    private boolean mSetupPending;
    private boolean mDisableCircularTransformation = true;


    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView,defStyleAttr,0);
        // 获取边界的宽度
        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, DEFAULT_BORDER_WIDTH);
        // 获取边界的颜色
        mBorderColor = a.getColor(R.styleable.CircleImageView_civ_border_color, DEFAULT_BORDER_COLOR);
        mBorderOverlay = a.getBoolean(R.styleable.CircleImageView_civ_border_overlay, DEFAULT_BORDER_OVERLAY);
        //调用 recycle() 回收TypedArray,以便后面重用
        a.recycle();
        inite();
    }

    private void inite() {
        super.setScaleType(ScaleType.CENTER_CROP);
        mReady = true;
        if (mSetupPending){
            setup();
            mSetupPending = false;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mSrcBitmap = bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mSrcBitmap = Utils.getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mSrcBitmap = Utils.getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
        mSrcBitmap = Utils.getBitmapFromDrawable(getDrawable());
        setup();
    }


    private void setup() {
        if (!mReady){
            mSetupPending = true;
            return;
        }

        if (mSrcBitmap == null){
            return;
        }
        mDisableCircularTransformation = false;
        // 构建渲染器，用mBitmap来填充绘制区域 ，参数值代表如果图片太小的话 就直接拉伸

//
//        //设置边界画笔样式
//        mBorderPaint.setStyle(Paint.Style.STROKE);
//        mBorderPaint.setAntiAlias(true);
//        mBorderPaint.setColor(mBorderColor);
//        mBorderPaint.setStrokeWidth(mBorderWidth); //画笔边界宽度
//
//        //取原图片的宽高
//        mBitmapWidth = mSrcBitmap.getWidth();
//        mBitmapHeight = mSrcBitmap.getHeight();
//
//        //设置含边界显示区域，取的是CircleImageView的布局实际大小，为方形，查看xml也就是160dp(240px)  getWidth得到是某个view的实际尺寸
//        mBorderRect.set(0,0,getWidth(),getHeight());
//        mBorderRadius = Math.min((mBorderRect.height() - mBorderWidth) / 2, (mBorderRect.width() - mBorderWidth) / 2);
//        // 初始图片显示区域为mBorderRect（CircleImageView的布局实际大小）
//        mDrawableRect.set(mBorderRect);
//        if (!mBorderOverlay){
//            //demo里始终执行
//            //通过inset方法  使得图片显示的区域从mBorderRect大小上下左右内移边界的宽度形成区域，查看xml边界宽度为2dp（3px）,所以方形边长为就是160-4=156dp(234px)
//            mDrawableRect.inset(mBorderWidth,mBorderWidth);
//        }
//        //这里计算的是内圆的最小半径，也即去除边界宽度的半径
//        mDrawableRadius = Math.min(mDrawableRect.height()/2,mDrawableRect.width()/2);
//        //设置渲染器的变换矩阵也即是mBitmap用何种缩放形式填充
//        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix() {
        float scale;
        float dx = 0;
        float dy = 0;

        mShaderMatrix.set(null);
        //这个不等式也就是(mBitmapWidth / mDrawableRect.width()) > (mBitmapHeight / mDrawableRect.height())
        //取最小的缩放比例
        if (mBitmapWidth * mDrawableRect.height() > mDrawableRect.width() * mBitmapHeight) {
            //y轴缩放 x轴平移 使得图片的y轴方向的边的尺寸缩放到图片显示区域（mDrawableRect）一样）
            scale = mDrawableRect.height() / (float) mBitmapHeight;
            dx = (mDrawableRect.width() - mBitmapWidth * scale) * 0.5f;
        } else {
            //x轴缩放 y轴平移 使得图片的x轴方向的边的尺寸缩放到图片显示区域（mDrawableRect）一样）
            scale = mDrawableRect.width() / (float) mBitmapWidth;
            dy = (mDrawableRect.height() - mBitmapHeight * scale) * 0.5f;
        }
        // shaeder的变换矩阵，我们这里主要用于放大或者缩小。
        mShaderMatrix.setScale(scale, scale);
        // 平移
        mShaderMatrix.postTranslate((int) (dx + 0.5f) + mDrawableRect.left, (int) (dy + 0.5f) + mDrawableRect.top);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("测试一下","1");
        if (mDisableCircularTransformation) {
            super.onDraw(canvas);
            return;
        }
        Log.d("测试一下",mDrawableRect.centerX()+"  "+mDrawableRect.centerY()+"  "+mDrawableRadius);
        if (mSrcBitmap == null){
            Log.d("测试一下","哈哈");
        }
        mBitmapShader = new BitmapShader(mSrcBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        //设置画笔反锯齿
        mPaint = new Paint();
//        mPaint.setAntiAlias(true);
        //设置画笔渲染器
        mPaint.setShader(mBitmapShader);
        canvas.drawCircle(375, 375, 200,mPaint);
    }
}
