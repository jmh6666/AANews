package com.example.administrator.news;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
    private NewsListSQLite helper;
    private List<NewsBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);
        initView();
        initDatas();
    }

    private void initDatas() {

        NetApi.getNewsList(new ICallback() {
            @Override
            public void showList(final List<NewsBean> list) {
                helper.save(list);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.setmList(helper.findAll());
                    }
                });
            }
        });
        mAdapter.setmOnClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                NewsBean news=mList.get(position);
                Bundle bundle=new Bundle();
                bundle.putString("title",news.getTitle());
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getApplication(),"您点击了第"+position+"个",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecyclerAdapter();
        mList=new ArrayList<NewsBean>();
        helper=new NewsListSQLite(this);
        mList=helper.findAll();
        mAdapter.setmList(helper.findAll());
        mRecyclerView.setAdapter(mAdapter);



    }

}
