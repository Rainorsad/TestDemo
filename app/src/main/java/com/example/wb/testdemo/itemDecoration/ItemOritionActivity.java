package com.example.wb.testdemo.itemDecoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wb.testdemo.R;
import com.example.wb.testdemo.animation.MyAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/6/26.
 */

public class ItemOritionActivity extends AppCompatActivity {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;

    private String[]data = {"a","啊","啊啊","啊啊啊","啊啊啊啊","b","吧","吧吧","吧吧吧","吧吧吧吧","c","从","从从","从从从","从从从从"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenorition);
        ButterKnife.inject(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        MyItemOrition orition = new MyItemOrition(MyItemOrition.VERTICAL);
        orition.setColor(0xFFDBD6D2);
        orition.setHeight(2);
        recycleview.addItemDecoration(orition);
        recycleview.setLayoutManager(manager);

        MyAdapter adapter = new MyAdapter(this,data);
        recycleview.setAdapter(adapter);

        View headview = LayoutInflater.from(recycleview.getContext()).inflate(R.layout.activity_textviewcolor,recycleview,false);
        TextView tvhead = (TextView) headview.findViewById(R.id.tv);
        tvhead.setText("我是头部");
        adapter.setmHeaderView(tvhead);
//        https://www.baidu.com/s?wd=The%20specified%20child%20already%20has%20a%20parent.%20You%20must%20call%20removeView()%20on%20the&rsv_spt=1&rsv_iqid=0x977d78940002695a&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=monline_3_dg&rsv_enter=0&rsv_n=2&rsv_sug3=1&inputT=40853&rsv_sug4=40854
//        The specified child already has a parent. You must call removeView() on the child's parent first.
    }

}
