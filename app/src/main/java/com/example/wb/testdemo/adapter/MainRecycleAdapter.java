package com.example.wb.testdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wb.testdemo.R;

/**
 * Created by Zhangchen on 2017/3/20.
 */

public class MainRecycleAdapter extends RecyclerView.Adapter{

    private Context context;
    private String[] title;
    private View.OnClickListener listener = null;

    public MainRecycleAdapter(Context context, String[] title){
        this.context = context;
        this.title = title;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycleview,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tv.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_recycleview_tv);
        }
    }
}
