package com.example.administrator.news;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.news.model.NewsBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2016/6/19.
 */
public class DetailsActivity extends Activity {
    private TextView dTitle;
    private TextView dAuthor;
    private TextView dDate;
    private ImageView dImage;
    private TextView dContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news_content);
        initView();
        initDatas();
    }
    private void initDatas() {

        String title=getIntent().getExtras().getString("title");
        String date=getIntent().getExtras().getString("date");
        String author=getIntent().getExtras().getString("author");
        String content=getIntent().getExtras().getString("content");
        Uri uri=Uri.parse(getIntent().getExtras().getString("image"));

        dTitle.setText(title);
        dImage.setImageURI(uri);
        dDate.setText(date);
        dAuthor.setText(author);
        dContent.setText(content);


    }
    private void initView() {
        dTitle=(TextView)findViewById(R.id.details_title);
        dAuthor=(TextView)findViewById(R.id.details_author);
        dDate=(TextView)findViewById(R.id.details_ts);
        dImage=(ImageView)findViewById(R.id.details_image);
        dContent=(TextView)findViewById(R.id.details_content);
    }
}
