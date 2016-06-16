package com.example.administrator.news;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.news.model.News;
import com.example.administrator.news.model.NewsBean;
import com.example.administrator.news.net.GsonParser;
import com.example.administrator.news.net.NetApi;
import com.example.administrator.news.net.OkHttpHelper;
import com.example.administrator.news.present.RecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/6/12.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private NewsListSQL helper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);
        initView();
        //initDatas();
        // xxxDatas();
    }

//    private void xxxDatas() {
//        mDatas=new ArrayList<NewsBean>();
//        mDatas.add(new NewsBean(1,"标题","作者","内容","时间","图片",0));
//        mAdapter.setmList(mDatas);
//    }


    private void initDatas() {
        helper=new NewsListSQL(this);
//        NewsBean mNews=new NewsBean();
//        //helper.searchNewsList(mNews.getNid());
//       // NewsBean mNews = new NewsBean();
//        Cursor cursor=helper.query();



        NetApi.getNewsList(new ICallback() {
            @Override
            public void showList(final List<NewsBean> list) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        helper.setmList(list);
                    }
                });
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter(this,cursor);
        helper=new NewsListSQL(this);
        cursor=helper.query();
        mRecyclerView.setAdapter(mAdapter);

    }


}
