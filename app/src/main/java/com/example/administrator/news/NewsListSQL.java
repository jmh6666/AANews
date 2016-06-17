package com.example.administrator.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.administrator.news.model.NewsBean;
import com.example.administrator.news.net.NetApi;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class NewsListSQL extends SQLiteOpenHelper {
    private final static String DB_NAME = "news.db";
    private final static int DB_VERSION = 1;
    private final static String NEWS_TBL = "newslist";
    private final static String NEWS_ID = "nid";
    private final static String NEWS_TITLE = "title";
    private final static String NEWS_CONTENT = "content";
    private final static String NEWS_AUTHOR = "author";
    private final static String NEWS_PIC_URL = "pic_url";
    private final static String NEWS_PUBLISH_TS = "publish_ts";
    private final static String NEWS_TP = "tp";

    public NewsListSQL(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public void setmList(List<NewsBean> mList){
        //this.mList=mList;
        //notifyDataSetChanged();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("create table if not exists " +
                NEWS_TBL + "(" +
                NEWS_ID + " Integer primary key autoincrement," +
                NEWS_TITLE + " varchar(50)," +
                NEWS_CONTENT + " varchar(2000)," +
                NEWS_AUTHOR + " varchar(20)," +
                NEWS_PUBLISH_TS + " datatime," +
                NEWS_TP + " Integer," +
                NEWS_PIC_URL + " varchar(200)"+")");
        db.execSQL(sBuilder.toString());
        List<NewsBean> mList
                ;
//        for (NewsBean nList : mList) {
//            ContentValues cValues = new ContentValues();
//            cValues.put(NEWS_ID, nList.getNid());
//            cValues.put(NEWS_TITLE, nList.getTitle());
//            cValues.put(NEWS_CONTENT, nList.getContent());
//            cValues.put(NEWS_AUTHOR, nList.getAuthor());
//            cValues.put(NEWS_PUBLISH_TS, nList.getPublish_ts());
//            cValues.put(NEWS_PIC_URL, nList.getPic_url());
//            cValues.put(NEWS_TP, nList.getTp());
//            db.insert(NEWS_TBL, null, cValues);
//            Log.e("111", NEWS_TITLE);
        }
 //   }

    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(NEWS_TBL, null, null, null, null, null, null);
        return c;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + NEWS_TBL);
        onCreate(db);
    }
}
