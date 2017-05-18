package com.example.wb.testdemo.activity.canves;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.example.wb.testdemo.R;

/**
 * Created by Zhangchen on 2017/5/18.
 */

public class CanvesView extends View{
    private Paint mPaint;
    private Context context;

    public CanvesView(Context context) {
        super(context);
        this.context = context;
//        initView();
    }

    public CanvesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        initView();
    }

    public CanvesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
//        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();

        mPaint.setColor(getResources().getColor(R.color.black));

        //获得屏幕宽高，取中心为圆心
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        canvasCircle(canvas,width,height); //画圆
        canvasMinutes(canvas,width,height); //画分钟刻度
        canvasHour(canvas,width,height);//画小时指针
    }

    /**
     * 画小时指针
     * @param canvas
     * @param width
     * @param height
     */
    private void canvasHour(Canvas canvas, int width, int height) {
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(width/2,height/2);
        canvas.drawLine(0,0,100,100,paintHour);
        canvas.drawLine(0,0,100,200,paintMinute);
        canvas.restore();
    }

    /**
     * 画分钟刻度
     * @param canvas
     * @param width
     * @param height
     */
    private void canvasMinutes(Canvas canvas, int width, int height) {
        mPaint.setStrokeWidth(3);
        for (int i=0;i<12;i++){
            if (i == 0 || i==3 || i==6 || i==9){
                mPaint.setStrokeWidth(5);
                mPaint.setTextSize(30);
                canvas.drawLine(width/2,height/2-width/2,width/2,
                        height/2-width/2+60,mPaint);
                String degree =null;
                if (i == 0){
                    degree = String.valueOf(12);
                }else {
                    degree = String.valueOf(i);
                }
                canvas.drawText(degree,width/2-mPaint.measureText(degree)/2,height/2-width/2+90,mPaint);
            }else {
                mPaint.setTextSize(15);
                mPaint.setStrokeWidth(3);
                canvas.drawLine(width/2,height/2-width/2,width/2,
                        height/2-width/2+30,mPaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree,width/2-mPaint.measureText(degree)/2,height/2-width/2+60,mPaint);
            }
            //通过旋转画布简化坐标运算
            canvas.rotate(30,width/2,height/2);
        }
    }

    /**
     * 画圆
     * @param canvas
     * @param width
     * @param height
     */
    private void canvasCircle(Canvas canvas, int width, int height) {
        //画圆
        mPaint.setStyle(Paint.Style.STROKE);//选择空心效果
        mPaint.setAntiAlias(true);//设置锯齿效果
        mPaint.setStrokeWidth(5);//设置空心边框的宽度
        canvas.drawCircle(width/2,height/2,width/2,mPaint);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    break;
            }
        }
    };
}
