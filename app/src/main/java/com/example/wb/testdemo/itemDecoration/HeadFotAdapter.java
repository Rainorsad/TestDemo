package com.example.wb.testdemo.itemDecoration;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import java.util.List;

/**
 * Created by Zhangchen on 2017/8/21.
 */

public class HeadFotAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> list;
    private int TYPE_HEADVIEW = 1001;
    private int TYPE_FOOTVIEW = 2002;
    private View headView;
    private View footView;

    public HeadFotAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("头部测试",viewType+"");
        if (viewType == TYPE_HEADVIEW) {
            return new MyViewHolder(headView);
        } else if (viewType == TYPE_FOOTVIEW) {
            return new MyViewHolder(footView);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_textviewcolor, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeadView() && position == 0) {
            return TYPE_HEADVIEW;
        } else {
            return 0;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originaSpanSize = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int i = 1;
                    if ((isHeadView() && position == 0)) {
                        i = ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    return i;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeadView()) position--;
        if (position <0){
            return;
        }
        MyViewHolder h = (MyViewHolder) holder;
        h.text.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        int coun = list.size();
        if (isHeadView()) coun++;
        return coun;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    private boolean isHeadView() {
        if (headView != null) {
            return true;
        } else {
            return false;
        }
    }


    public void addHeadView(View view) {
        Log.e("测试", "添加头部");
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        headView = view;
        notifyItemInserted(0);
    }
}
