package com.example.administrator.news.net;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
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
    private final static String newsTitle="Title";
    private final static String newsImage="pic_url";
    private final static String newsContent="content";

    private final static String newsListUrl
            ="http://104.224.140.47/news/api/list?index=0&size=2";
    private final static String newsUrl
            ="http://104.224.140.47/news/api/news?nid=2";

    public static void getNewsList(){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().url(newsListUrl).build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()){
                String json=response.body().toString();
                Map<String,String> header=new HashMap<>();


                Log.e("111",json);
            }
            }
        });
    }
}
