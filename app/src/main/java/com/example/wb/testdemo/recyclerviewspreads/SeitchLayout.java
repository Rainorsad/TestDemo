package com.example.wb.testdemo.recyclerviewspreads;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Zhangchen on 2017/9/7.
 */

public class SeitchLayout extends FrameLayout{

    private Context context;

    public SeitchLayout(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public SeitchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public SeitchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {

    }
}
