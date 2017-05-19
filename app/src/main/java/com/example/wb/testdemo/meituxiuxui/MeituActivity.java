package com.example.wb.testdemo.meituxiuxui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/5/19.
 */

public class MeituActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.seekone)
    SeekBar seekone;
    @InjectView(R.id.seektwo)
    SeekBar seektwo;
    @InjectView(R.id.seekthree)
    SeekBar seekthree;

    private Bitmap bitmap;
    private ColorMatrix colorMatrix;//颜色矩阵

    private int mHue;  //色调
    private int mStauration;  //饱和度
    private int mLum;  //亮度

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meitu);
        ButterKnife.inject(this);

        colorMatrix = new ColorMatrix();
        getBitmap(); //得到图像的复制图
        seekone.setOnSeekBarChangeListener(this);
        seektwo.setOnSeekBarChangeListener(this);
        seekthree.setOnSeekBarChangeListener(this);
    }

    private void getBitmap() {
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.one);
        img.setImageBitmap(bitmap);

    }

    //seekbar获取输入值变化
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.seekone:
                mHue = progress/100;
                break;
            case R.id.seektwo:
                mStauration = 2;
                break;
            case R.id.seekthree:
                mLum = progress/100;
                break;
        }
        img.setImageBitmap(handlerImageEffect(bitmap,mHue,mStauration,mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public static Bitmap handlerImageEffect(Bitmap bitmap,float hue,float staturation,float lum){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix huMax = new ColorMatrix();
        huMax.setRotate(0,hue);
        huMax.setRotate(1,hue);
        huMax.setRotate(2,hue);

        ColorMatrix staMax = new ColorMatrix();
        staMax.setSaturation(staturation);

        ColorMatrix lumMax = new ColorMatrix();
        lumMax.setScale(lum,lum,lum,1);

        ColorMatrix imageMax = new ColorMatrix();
        imageMax.postConcat(huMax);
        imageMax.postConcat(staMax);
        imageMax.postConcat(lumMax);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMax));
        canvas.drawBitmap(bitmap,0,0,paint);
        return bmp;
    }
}
