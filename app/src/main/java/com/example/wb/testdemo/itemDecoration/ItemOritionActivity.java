package com.example.wb.testdemo.itemDecoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wb.testdemo.R;

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
        recycleview.setAdapter(new MyAdapter());
    }

    class  MyAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_textviewcolor,parent,false);
            RecyclerView.ViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.tv.setText(data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView tv;
            public MyViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.tv);
            }
        }
    }
}
