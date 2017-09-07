package com.example.wb.testdemo.recyclerviewspreads;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by Zhangchen on 2017/5/11.
 */

public class SpreadsDrawable extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    private View mMenuView,mMainView;
    private int mWidth;
    private Context context;
    private int window_width;

    public SpreadsDrawable(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public SpreadsDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SpreadsDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        window_width =size.x;
        mViewDragHelper = ViewDragHelper.create(this,callback);
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_RIGHT);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //此方法置顶哪一个子view可以被移动
            return mMainView == child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {  //水平方向滑动
            Log.d("测试：clampViewPositionHorizontal",left+ "  " + dx);
            if (left < 0) {
                if (left> -(window_width*0.5)) {
                    return left;
                }else {
                    return (int) -(window_width*0.5);
                }
            }else {
                return 0;
            }
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {  //垂直方向滑动
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {  //手指抬起时时间回调
            super.onViewReleased(releasedChild, xvel, yvel);
            Log.d("测试：onViewReleased",mMainView.getLeft()+"  "+xvel+"  "+yvel);
            if (mMainView.getLeft()> -120){
                //关闭菜单
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(SpreadsDrawable.this);
            }else {
                //打开菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, (int) -(window_width*0.5),0);
                ViewCompat.postInvalidateOnAnimation(SpreadsDrawable.this);
            }
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return ViewDragHelper.EDGE_RIGHT;
        }
    };

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) { //获得点击事件
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) { //获得点击事件
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //加载完布局文件后调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);

        ViewGroup.LayoutParams layoutParams = mMenuView.getLayoutParams();
        layoutParams.width = window_width;
        mMenuView.setLayoutParams(layoutParams);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
    }
}
