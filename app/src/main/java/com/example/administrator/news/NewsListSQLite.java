package com.example.administrator.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.news.model.NewsBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/17.
 */
public class NewsListSQLite extends BaseDao<NewsBean> {
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
    public NewsListSQLite(Context context) {
        super(context, DB_NAME, null, 1, NEWS_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    protected Map<String, String> getColNameTypeMap() {
        Map<String,String> map=new HashMap<>();
        map.put(NEWS_ID, COL_TYPE_INT_PK_AUTOINCREMENT);
        map.put(NEWS_TITLE, COL_TYPE_TEXT);
        map.put(NEWS_CONTENT, COL_TYPE_TEXT);
        map.put(NEWS_AUTHOR, COL_TYPE_TEXT);
        map.put(NEWS_PIC_URL, COL_TYPE_TEXT);
        map.put(NEWS_PUBLISH_TS, COL_TYPE_LONG);
        map.put(NEWS_TP, COL_TYPE_INT);
        return map;
    }

    @Override
    protected ContentValues getContentValues(NewsBean newsBean) {
        ContentValues values = new ContentValues();
        values.put(NEWS_TITLE, newsBean.getTitle());
        values.put(NEWS_CONTENT, newsBean.getContent());
        values.put(NEWS_AUTHOR, newsBean.getAuthor());
        values.put(NEWS_PIC_URL, newsBean.getPic_url());
        values.put(NEWS_PUBLISH_TS, newsBean.getPublish_ts());
        values.put(NEWS_TP, newsBean.getTp());
        return values;
    }

    @Override
    protected NewsBean findByCursor(Cursor cursor) {
        if (cursor == null)
            return null;

        NewsBean news = new NewsBean();
        news.setNid(cursor.getInt(cursor.getColumnIndex(NEWS_ID)));
        news.setTitle(cursor.getString(cursor.getColumnIndex(NEWS_TITLE)));
        news.setContent(cursor.getString(cursor.getColumnIndex(NEWS_CONTENT)));
        news.setAuthor(cursor.getString(cursor.getColumnIndex(NEWS_AUTHOR)));
        news.setPic_url(cursor.getString(cursor.getColumnIndex(NEWS_PIC_URL)));
        news.setPublish_ts(cursor.getString(cursor.getColumnIndex(NEWS_PUBLISH_TS)));
        news.setTp(cursor.getInt(cursor.getColumnIndex(NEWS_TITLE)));
        return news;
    }
    public void update(NewsBean news) {
        ContentValues values = getContentValues(news);
        String selection = NEWS_ID + "=?";
        String[] args = new String[]{String.valueOf(news.getNid())};
        update(values, selection, args);
    }
    public Cursor query()
    {
        //获得SQLiteDatabase实例
        SQLiteDatabase db=getWritableDatabase();
        //查询获得Cursor
        Cursor c=db.query(NEWS_TBL, new String[]{"title","pulish_ts","author","content"}, null, null, null, null,null);
        return c;
    }
}
