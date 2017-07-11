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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/6/26.
 */

public class ItemOritionActivity extends AppCompatActivity {

    @InjectView(R.id.recycleview)
    RecyclerView recycleview;

    private String[]data = {"a","啊","啊啊","啊啊啊","啊啊啊啊","b","吧","吧吧","吧吧吧","吧吧吧吧","c","从","从从","从从从","从从从从"};
    private List<String> s;

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

        s = new ArrayList<>();
        for (int i=0;i<10;i++){
            s.add(i+"哈哈哈");
        }
        MyAdapter adapter = new MyAdapter(this,s);
        recycleview.setAdapter(adapter);

        View headview = LayoutInflater.from(recycleview.getContext()).inflate(R.layout.activity_textviewcolor,recycleview,false);
        TextView tvhead = (TextView) headview.findViewById(R.id.tv);
        tvhead.setText("我是头部");
        adapter.setmHeaderView(tvhead);
    }

}
