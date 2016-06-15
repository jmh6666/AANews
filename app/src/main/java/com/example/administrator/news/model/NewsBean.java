package com.example.administrator.news.model;

/**
 * Created by Administrator on 2016/6/12.
 */
public class NewsBean {
    private int nid;
    private String title;

    @Override
    public String toString() {
        return "NewsBean{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", publish_ts='" + publish_ts + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", tp=" + tp +
                '}';
    }

    private String author;
    private String content;
    private String publish_ts;
    private String pic_url;
    private int tp;

    public NewsBean(int nid, String title, String author, String content, String publish_ts, String pic_url, int tp) {
        this.nid = nid;
        this.title = title;
        this.author = author;
        this.content = content;
        this.publish_ts = publish_ts;
        this.pic_url = pic_url;
        this.tp = tp;
    }

    public NewsBean() {
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublish_ts(String publish_ts) {
        this.publish_ts = publish_ts;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public String getTitle() {

        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNid() {
        return nid;
    }

    public String getContent() {
        return content;
    }

    public String getPublish_ts() {
        return publish_ts;
    }

    public String getPic_url() {
        return pic_url;
    }

    public int getTp() {
        return tp;
    }
}
