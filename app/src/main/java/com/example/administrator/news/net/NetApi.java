package com.example.administrator.news.net;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.news.ICallback;
import com.example.administrator.news.model.News;
import com.example.administrator.news.model.NewsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/6/13.
 */
public class NetApi {

    private final static String newsListUrl
            ="http://104.224.140.47/news/api/list?index=0&size=2";
    private final static String newsUrl
            ="http://104.224.140.47/news/api/news?nid=2";


    public static void getNewsList(final ICallback callback){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(newsListUrl).build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()){
                String json=response.body().string();
                Gson gson=new Gson();
                News mList=gson.fromJson(json,News.class);

                callback.showList(mList.getData());
//                NewsBean mNews=mList.get(0);
//                newsTitle=mNews.getTitle();
//                newsContent=mNews.getContent();
//
//                newsImage=mNews.getPic_url();
                    Log.e("111",json);
                    Log.e("222", String.valueOf(mList));
            }
            }

        });

    }

}
