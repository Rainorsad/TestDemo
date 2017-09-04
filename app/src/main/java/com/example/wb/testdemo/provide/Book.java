package com.example.wb.testdemo.provide;

/**
 * Created by Zhangchen on 2017/8/31.
 */

public class Book {
    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }

    private int _id;
    private String name;

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
