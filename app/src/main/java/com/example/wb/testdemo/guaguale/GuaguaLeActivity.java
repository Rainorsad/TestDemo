package com.example.wb.testdemo.guaguale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.wb.testdemo.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/5/24.
 */

public class GuaguaLeActivity extends AppCompatActivity {

    @InjectView(R.id.image)
    GuaguaLeView image;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guaguale);
        ButterKnife.inject(this);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        image.setmBgBitmap(bm);
    }
}
