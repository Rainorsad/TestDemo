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

import java.util.Calendar;

/**
 * Created by Zhangchen on 2017/5/18.
 */

public class CanvesView extends View{
    private Paint mPaint;
    private Context context;
    private int mRadius;
    private Calendar now;//时间函数
    //指针反向超过圆点的长度
    private static final float DEFAULT_POINT_BACK_LENGTH = 40f;

    public CanvesView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public CanvesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public CanvesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        handler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        now = Calendar.getInstance();
        mPaint = new Paint();

        mPaint.setColor(getResources().getColor(R.color.black));

        //获得屏幕宽高，取中心为圆心
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mRadius = width/2;

        canvasCircle(canvas,width,height); //画圆
        canvasMinute(canvas,width,height);//画分钟刻度改进版
        canvasHour(canvas,width,height);//画时指针
    }

    /**
     * 画分钟刻度改进版，角度法，合适
     * @param canvas
     * @param width
     * @param height
     */
    private void canvasMinute(Canvas canvas, int width, int height) {
        canvas.save();
        canvas.translate(width/2,height/2);

        Paint paint = new Paint(); //画刻度

        for (int i=0;i<12;i++){
            String degree =null;
            float[] floatsa = null;
            float[] floatsb = null;
            float[] floatsc = null;
            if (i==0 || i==3 || i==6 || i==9) {
                if (i == 0) {
                    degree = String.valueOf(12);
                } else {
                    degree = String.valueOf(i);
                }

                floatsa = calculatePoint(30*i, mRadius);
                floatsb = calculatePoint(30*i, mRadius-60);
                floatsc = calculatePoint(30*i, mRadius-90);

                paint.setStrokeWidth(5);
                paint.setTextSize(30);
            }else {
                floatsa = calculatePoint(30*i, mRadius);
                floatsb = calculatePoint(30*i, mRadius-30);
                floatsc = calculatePoint(30*i, mRadius-60);

                degree = String.valueOf(i);
                paint.setStrokeWidth(3);
                paint.setTextSize(20);
            }
            canvas.drawLine(floatsa[0],floatsa[1],floatsb[0],
                    floatsb[1],paint);
            canvas.drawText(degree,floatsc[0]-paint.measureText(degree)/2,floatsc[1],paint);
        }
    }

    /**
     * 画小时指针
     * @param canvas
     * @param width
     * @param height
     */
    private void canvasHour(Canvas canvas, int width, int height) {
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20); //小时针
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10); //分钟针
        Paint paintSeconds = new Paint();
        paintSeconds.setStrokeWidth(5);//秒钟针

        //前面图层旋转过后，此处需要将图层平移到圆心位置
//        canvas.save();
//        canvas.translate(width/2,height/2);

        //获得当前小时的坐标，绘制小时针
        int mHour = 0;
        if (now.get(Calendar.HOUR)<12){
            mHour = now.get(Calendar.HOUR);
        }else {
            mHour = now.get(Calendar.HOUR)-12;
        }
        float[] floatshours = calculatePoint(mHour/12f*360+now.get(Calendar.MINUTE)/60f*30, mRadius/4);
        canvas.drawLine(0,0,floatshours[0],floatshours[1],paintHour);

        //获得当前分钟的坐标，绘制分钟针
        float[] floatsminute = calculatePoint(now.get(Calendar.MINUTE)/60f*360, mRadius/2);
        canvas.drawLine(0,0,floatsminute[0],floatsminute[1],paintMinute);

        //获得当前秒钟的坐标，绘制秒针
        float[] floatsseconds = calculatePoint(now.get(Calendar.SECOND)/60f*360, (float) (mRadius*0.8));
        canvas.drawLine(0,0,floatsseconds[0],floatsseconds[1],paintSeconds);
        canvas.restore();
    }

    /**
     * 画分钟刻度  //旋转画布法，优化后去掉已
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
                canvas.drawLine(width/2,height/2-mRadius,width/2,
                        height/2-mRadius+60,mPaint);
                String degree =null;
                if (i == 0){
                    degree = String.valueOf(12);
                }else {
                    degree = String.valueOf(i);
                }
                canvas.drawText(degree,width/2-mPaint.measureText(degree)/2,height/2-mRadius+90,mPaint);
            }else {
                mPaint.setTextSize(15);
                mPaint.setStrokeWidth(3);
                canvas.drawLine(width/2,height/2-mRadius,width/2,
                        height/2-mRadius+30,mPaint);
                String degree = String.valueOf(i);
                canvas.drawText(degree,width/2-mPaint.measureText(degree)/2,height/2-mRadius+60,mPaint);
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
        canvas.drawCircle(width/2,height/2,mRadius,mPaint);
    }

    /**
     * 根据角度和半径计算起点终点的坐标
     * @param angle
     * @param length
     * @return
     */
    private float[] calculatePoint(float angle, float length){
        float[] points = new float[2];
        if(angle <= 90f){
            points[0] = (float) Math.sin(angle*Math.PI/180) * length;
            points[1] = -(float) Math.cos(angle*Math.PI/180) * length;
        }else if(angle <= 180f){
            points[0] = (float) Math.cos((angle-90)*Math.PI/180) * length;
            points[1] = (float) Math.sin((angle-90)*Math.PI/180) * length;
        }else if(angle <= 270f){
            points[0] = -(float) Math.sin((angle-180)*Math.PI/180) * length;
            points[1] = (float) Math.cos((angle-180)*Math.PI/180) * length;
        }else if(angle <= 360f){
            points[0] = -(float) Math.cos((angle-270)*Math.PI/180) * length;
            points[1] = -(float) Math.sin((angle-270)*Math.PI/180) * length;
        }
        return points;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    invalidate();
                    handler.sendEmptyMessageDelayed(0,1000);
                    break;
            }
        }
    };
}
