package com.example.administrator.news;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Administrator on 2016/6/19.
 */
public class DetailsActivity extends Activity {
    private TextView dTitle;
    private TextView dAuthor;
    private TextView dDate;
    private ImageView dImage;
    private TextView dContent;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news_content);
        initView();
        initDatas();
    }



    private Bitmap getBitmapFromUrl(String imageUrl) {
        URL url;
        Bitmap bitmap = null;
        try {
            url = new URL(getIntent().getExtras().getString("image"));
            InputStream is = url.openConnection().getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }



    private void initDatas() {

        String title=getIntent().getExtras().getString("title");
        String date=getIntent().getExtras().getString("date");
        String author=getIntent().getExtras().getString("author");
        String content=getIntent().getExtras().getString("content");
        String image=getIntent().getExtras().getString("image");
        mImageLoader.displayImage(image,dImage,options);
        dTitle.setText(title);
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
        mImageLoader=ImageLoader.getInstance();
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
    }


}
