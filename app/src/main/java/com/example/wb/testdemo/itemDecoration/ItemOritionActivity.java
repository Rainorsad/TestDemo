package com.example.wb.testdemo.itemDecoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    private List<String> s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenorition);
        ButterKnife.inject(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        MyItemOrition orition = new MyItemOrition(this,MyItemOrition.VERTICAL);
        orition.setColor(0xFFDBD6D2);
//        orition.setLabel(true,"ceshi",10,0XFFDBD6D2,16);
//        orition.setImageLabel(true,R.mipmap.fragme_mysmschool);
        recycleview.addItemDecoration(orition);
        recycleview.setLayoutManager(manager);

        s = new ArrayList<>();
        for (int i=0;i<20;i++){
            s.add(i+"哈哈哈");
        }
        MyAdapter adapter = new MyAdapter(s);
        recycleview.setAdapter(adapter);

    }

}
