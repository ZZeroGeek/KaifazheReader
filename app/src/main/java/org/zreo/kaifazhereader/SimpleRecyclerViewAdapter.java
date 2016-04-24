package org.zreo.kaifazhereader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 85789 on 2016/4/23.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<String> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
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

    public SimpleRecyclerViewAdapter(Context context, List<String> items ) {
        this.mContext=context;
        mDatas = items;
        mInflater=LayoutInflater.from(context);
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView tv;
    //这个方法是用来初始化（1）布局的各个控件
    public MyViewHolder(View view) {
        super(view);
        tv=(TextView)view.findViewById(R.id.tv);
    }
}
