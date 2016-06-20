package com.example.administrator.news;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import com.example.administrator.news.model.NewsBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/6/19.
 */
public class DetailsActivity extends Activity {
    private TextView dTitle;
    private TextView dAuthor;
    private TextView dDate;
    private TextView dContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initDatas();
    }
    private void initDatas() {

        String str=getIntent().getExtras().getString("title");

        dTitle.setText(str);
//        dDate.setText();
//        dAuthor.setText();
//        dContent.setText();


    }
    private void initView() {
        dTitle=(TextView)findViewById(R.id.details_title);
        dAuthor=(TextView)findViewById(R.id.details_author);
        dDate=(TextView)findViewById(R.id.details_ts);
        dContent=(TextView)findViewById(R.id.details_content);
    }
}
