package com.example.administrator.news.present;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.model.NewsBean;
import com.example.administrator.news.net.NetApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {;

    private  final static int TYPE_ONE=1;
    private  final static int TYPE_TWO=2;
    private ArrayList<NewsBean> mDatas=new ArrayList<>();
    private List<NewsBean> mList;
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
        this.mList=mList;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean news=mList.get(position);
        if (holder instanceof MyViewHolder){
            MyViewHolder holder1= (MyViewHolder)holder;
            int newsImage=Integer.valueOf(news.getPic_url());
            holder1.newsImage.setImageResource(newsImage);
            holder1.newsTitle.setText(news.getTitle());
            holder1.newsContent.setText(news.getContent());
        }
        if (holder instanceof MyViewHolder2){
            MyViewHolder2 holder2=(MyViewHolder2)holder;
            int newsImage=Integer.valueOf(news.getPic_url());
            holder2.newsTitle2.setText(news.getTitle());
            holder2.newsImage2.setImageResource(newsImage);
        }
    }

    @Override
    public int getItemViewType(int position) {
        NewsBean newsType=mList.get(position);
        if (newsType.getTp()==0)
            return TYPE_ONE;
        if (newsType.getTp()==1)
            return TYPE_TWO;
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
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
