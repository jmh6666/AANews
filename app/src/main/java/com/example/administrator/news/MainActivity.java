package com.example.administrator.news;

import android.app.Activity;
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
    private List<NewsBean> mDatas;
    private DisplayImageOptions options;
    private String[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);
        initView();
        initDatas();
        // xxxDatas();
    }

//    private void xxxDatas() {
//        mDatas=new ArrayList<NewsBean>();
//        mDatas.add(new NewsBean(1,"标题","作者","内容","时间","图片",0));
//        mAdapter.setmList(mDatas);
//    }


    private void initDatas() {
        NewsBean mNews = new NewsBean();
        Bundle bundle = getIntent().getExtras();
        images = bundle.getStringArray(Constants.IMAGES);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中

                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)
                .handler(new Handler()) // default
                .build();
        ImageLoader imageLoader = ImageLoader.getInstance();
        NetApi.getNewsList(new ICallback() {
            @Override
            public void showList(final List<NewsBean> list) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setmList(list);
                    }
                });
            }
        });
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


}
