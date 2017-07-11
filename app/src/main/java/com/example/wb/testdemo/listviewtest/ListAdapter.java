package com.example.wb.testdemo.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import java.util.List;

/**
 * Created by Zhangchen on 2017/7/11.
 */

public class ListAdapter extends BaseAdapter{

    private Context context;
    private List<String> s;

    public ListAdapter(Context context,List<String> s){
        this.context = context;
        this.s = s;
    }

    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_textviewcolor,parent,false);
            holder.info = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.info.setText(s.get(position));
        return convertView;
    }

    public final class ViewHolder{
        public TextView info;
    }

}
