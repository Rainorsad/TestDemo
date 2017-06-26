package com.example.wb.testdemo.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by Zhangchen on 2017/6/26.
 */

public class MyItemOrition extends RecyclerView.ItemDecoration{

    private int mHeight = 5;//分割线高度
    private Paint mPaint;

    public MyItemOrition(){
        mPaint = new Paint();
    }
    //通过Rect为每个Item设置偏移，用于绘制Decoration
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (position != 0){
            //为第一个item预留空间
            outRect.top = mHeight;
        }
    }

    //通过该方法，在Canvas上绘制内容，在绘制Item之前调用。（如果没有通过getItemOffsets设置偏移的话，Item的内容会将其覆盖）
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            final View childView = parent.getChildAt(i);
            final int bottom = childView.getTop();
            final int top = bottom - mHeight;
            c.drawRect(left,top,right,bottom,mPaint);
        }
    }

    //通过该方法，在Canvas上绘制内容,在Item之后调用。(画的内容会覆盖在item的上层)
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    public interface GroupTitle{
        String getGroupName(int position);
    }

    private boolean isTitle(int positon){
        if (positon == 0){
            return true;
        }else {
            String titleId = getGroupName(positon - 1);
            String contect = getGroupName(positon);
            return !TextUtils.equals(titleId,contect);
        }
    }

    private String getGroupName(int i) {
        return null;
    }
}
