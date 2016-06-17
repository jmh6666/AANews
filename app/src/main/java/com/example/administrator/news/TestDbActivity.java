package com.example.administrator.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.news.R;
import com.example.administrator.news.model.NewsBean;

import java.util.List;

/**
 * Created by yixuanxuan on 16/6/16.
 */
public class TestDbActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mInsertBtn;
    private Button mFindBtn;
    private Button mUpdateBtn;
    private Button mDeleteBtn;

    private NewsDao mDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_db);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_find).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);

        mDao = new NewsDao(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_insert:
                NewsBean news = new NewsBean();
                news.setTp(0);
                news.setAuthor("author");
                news.setTitle("title");
                news.setContent("content");
                news.setPublish_ts("0");
                news.setPic_url("url");
                mDao.save(news);
                break;
            case R.id.btn_find:
                List<NewsBean> list = mDao.findAll();
                Log.e("xxx", list+"");
                break;
            case R.id.btn_update:
                NewsBean news1 = new NewsBean();
                news1.setNid(1);
                news1.setTp(0);
                news1.setAuthor("author1");
                news1.setTitle("title1");
                news1.setContent("content1");
                news1.setPublish_ts("0");
                news1.setPic_url("url1");
                mDao.update(news1);
                break;
            case R.id.btn_delete:
                mDao.deleteAll();
                break;
        }
    }
}
