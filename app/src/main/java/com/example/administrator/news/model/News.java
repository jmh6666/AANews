package com.example.administrator.news.model;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 */
public class News {
    private int index;
    private List<NewsBean> data;

    @Override
    public String toString() {
        return "News{" +
                "index=" + index +
                ", data=" + data +
                '}';
    }
}
