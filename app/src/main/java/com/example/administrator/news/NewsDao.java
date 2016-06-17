package com.example.administrator.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.news.model.News;
import com.example.administrator.news.model.NewsBean;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by yixuanxuan on 16/6/16.
 */
public class NewsDao extends BaseDao<NewsBean> {
    public static final int DB_VERSION = 1;
    private final static String NEWS_TBL = "newslist";
    private final static String NEWS_ID = "nid";
    private final static String NEWS_TITLE = "title";
    private final static String NEWS_CONTENT = "content";
    private final static String NEWS_AUTHOR = "author";
    private final static String NEWS_PIC_URL = "pic_url";
    private final static String NEWS_PUBLISH_TS = "publish_ts";
    private final static String NEWS_TP = "tp";

    public NewsDao(Context context) {
        super(context, "news.db", null, DB_VERSION, "news");
    }

    @Override
    protected Map<String, String> getColNameTypeMap() {
        Map<String, String> map = new Hashtable<>();
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
//        values.put(NEWS_ID, newsBean.getNid()); Don`t do this...
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 16/6/16
    }

    public void update(NewsBean news) {
        ContentValues values = getContentValues(news);
        String selection = NEWS_ID + "=?";
        String[] args = new String[]{String.valueOf(news.getNid())};
        update(values, selection, args);
    }

}
