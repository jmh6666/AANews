package com.example.administrator.news.present;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.model.NewsBean;
import com.example.administrator.news.net.NetApi;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {;

    private  final static int TYPE_ONE=1;
    private  final static int TYPE_TWO=2;
    private List<NewsBean> mList;
    private ImageLoader mImageLoader;
    private DisplayImageOptions options;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        if (viewType==TYPE_ONE)
            return new MyViewHolder(inflater
                    .inflate(R.layout.layout_news_list_one,parent,false));
            return new MyViewHolder2(inflater
                    .inflate(R.layout.layout_news_list_two,parent,false));
    }
    public void setmList(List<NewsBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }
    public RecyclerAdapter(){
        this.mList=new ArrayList<>();
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean news=mList.get(position);
        if (holder instanceof MyViewHolder){
            MyViewHolder holder1= (MyViewHolder)holder;
            mImageLoader=ImageLoader.getInstance();
          //  int newsImage=Integer.parseInt(news.getPic_url());
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

            mImageLoader.displayImage(news.getPic_url(),holder1.newsImage,options);


            holder1.newsTitle.setText(news.getTitle());
            holder1.newsContent.setText(news.getContent());
        }
        if (holder instanceof MyViewHolder2){
            MyViewHolder2 holder2=(MyViewHolder2)holder;
            int newsImage=Integer.parseInt(news.getPic_url());
            holder2.newsTitle2.setText(news.getTitle());
            holder2.newsImage2.setImageResource(newsImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        NewsBean newsType=mList.get(position);
//        if (newsType.getTp()==0)
            return TYPE_ONE;
//        if (newsType.getTp()==1)
//            return TYPE_TWO;
//        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        TextView newsTitle;
        TextView newsContent;
        public MyViewHolder(View itemView) {
            super(itemView);
            newsImage=(ImageView) itemView.findViewById(R.id.news_image);
            newsTitle=(TextView)itemView.findViewById(R.id.news_title);
            newsContent=(TextView)itemView.findViewById(R.id.news_details);

        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView newsImage2;
        TextView newsTitle2;
        public MyViewHolder2(View itemView) {
            super(itemView);
            newsImage2=(ImageView)itemView.findViewById(R.id.news_image2);
            newsTitle2=(TextView)itemView.findViewById(R.id.news_title2);
        }
    }
}
