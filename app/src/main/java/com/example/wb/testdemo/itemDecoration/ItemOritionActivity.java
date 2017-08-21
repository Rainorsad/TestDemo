package com.example.wb.testdemo.itemDecoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.wb.testdemo.R;

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
        s = new ArrayList<>();
        for (int i=0;i<20;i++){
            s.add(i+"哈哈哈");
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        MyItemOrition orition = new MyItemOrition(this);
        recycleview.addItemDecoration(orition);
        recycleview.setLayoutManager(manager);
        HeadFotAdapter adapter = new HeadFotAdapter(this,s);
        recycleview.setAdapter(adapter);

        adapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.activity_textviewcolor,null));
    }

}
