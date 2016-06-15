package com.example.administrator.news;

import com.example.administrator.news.model.News;
import com.example.administrator.news.model.NewsBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public interface ICallback {
    void showList(List<NewsBean> list);
}
