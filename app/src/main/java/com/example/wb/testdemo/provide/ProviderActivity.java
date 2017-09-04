package com.example.wb.testdemo.provide;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.wb.testdemo.R;

/**
 * Created by Zhangchen on 2017/8/31.
 */

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide);

        Uri bookUri = Uri.parse("content://com.android.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id",6);
        values.put("name","三国志");
        getContentResolver().insert(bookUri,values);
        Cursor bookCursor = getContentResolver().query(bookUri,new String[]{"_id","name"},null,null,null);
        while (bookCursor.moveToNext()){
            Book book = new Book();
            book.setId(bookCursor.getInt(0));
            book.setName(bookCursor.getString(1));
            Log.d("测试book",book.toString());
        }
        bookCursor.close();

        Uri userUri = Uri.parse("content://com.android.provider/user");
        Cursor userCursor = getContentResolver().query(userUri,new String[]{"_id","name","sex"},null,null,null);
        while (userCursor.moveToNext()){
            User user = new User();
            user.setId(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setSex(userCursor.getInt(2));
            Log.d("测试user",user.toString());
        }
        userCursor.close();
    }

}
