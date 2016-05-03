package org.zreo.kaifazhereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85789 on 2016/4/23.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<SaveNews> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
   //     holder.newsText.setText(mDatas.get(position));
        SaveNews saveNews=mDatas.get(position);
        holder.newsImage.setImageResource(saveNews.getImageId());
        holder.newsText.setText(saveNews.getTitle());
        holder.resource.setText(saveNews.getResource());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.item_single_textview,parent,false);//这里就要加载Item的布局，也就是行的布局。（1）item_single_textview
        MyViewHolder viewHolder=new MyViewHolder(view);//初始化（1）布局的控件，详见MyViewHolder类的构造函数
        return viewHolder;
    }

    public SimpleRecyclerViewAdapter(Context context, List<SaveNews> items ) {
        this.mContext=context;
        mDatas = items;
        mInflater=LayoutInflater.from(context);
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView newsText;
    CircleImageView newsImage;
    TextView resource;
    ImageView itemComment;
    ImageView itemLike;
    TextView itemCommentNumber;
    TextView itemLikeNumber;
    //这个方法是用来初始化（1）布局的各个控件
    public MyViewHolder(View view) {
        super(view);
        newsText=(TextView)view.findViewById(R.id.newsText);
        newsImage= (CircleImageView) view.findViewById(R.id.newsImage);
        resource= (TextView) view.findViewById(R.id.newsRresource);
        itemComment=(ImageView)view.findViewById(R.id.ic_item_comment);
        itemLike= (ImageView) view.findViewById(R.id.ic_item_like);
        itemCommentNumber= (TextView) view.findViewById(R.id.ic_item_comment_number);
        itemLikeNumber= (TextView) view.findViewById(R.id.ic_item_like_number);

    }
}
