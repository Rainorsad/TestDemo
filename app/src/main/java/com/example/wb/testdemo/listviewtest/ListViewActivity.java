package com.example.wb.testdemo.listviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wb.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Zhangchen on 2017/7/11.
 */

public class ListViewActivity extends AppCompatActivity {

    @InjectView(R.id.list)
    ListView list;
    private List<String> s;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewtest);
        ButterKnife.inject(this);

        s = new ArrayList<>();
        for (int i=0;i<10;i++){
            s.add(i+"哈哈");
        }

        final ListAdapter adapter = new ListAdapter(ListViewActivity.this,s);
//        list.setAdapter(adapter);

        View headview = LayoutInflater.from(list.getContext()).inflate(R.layout.activity_textviewcolor,list,false);
        TextView tvhead = (TextView) headview.findViewById(R.id.tv);
        tvhead.setText("我是头部");
       list.addHeaderView(tvhead);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                s.set(position,"haha");
//                adapter.notifyDataSetChanged();
//            }
//        });
    }
}
