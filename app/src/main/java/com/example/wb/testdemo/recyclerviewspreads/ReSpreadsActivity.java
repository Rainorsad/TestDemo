package com.example.wb.testdemo.recyclerviewspreads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wb.testdemo.R;
import com.example.wb.testdemo.itemDecoration.MyItemOrition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangchen on 2017/9/7.
 */

public class ReSpreadsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respreads);
        List<String> data = new ArrayList<>();
        for (int i=0;i<20;i++){
            data.add(i+"测试");
        }

        RecyclerView r = (RecyclerView) findViewById(R.id.recycleview);
        MyItemOrition orition = new MyItemOrition(this);
        r.addItemDecoration(orition);
        r.setLayoutManager(new LinearLayoutManager(this));
        SpreadsAdapter adapter = new SpreadsAdapter(data);
        r.setAdapter(adapter);
    }
}
