package org.zreo.kaifazhereader;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85789 on 2016/4/19.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
   private int mPage;
    private SimpleRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);//获取当前页卡的位置
    }

   //初始化页卡要显示的内容
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        if(mPage==1) {
            view = inflater.inflate(R.layout.fragment_page, container, false);
            initDatas();//初始化数据
            mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
            mAdapter = new SimpleRecyclerViewAdapter(getContext(), mDatas);//为适配器传入上下文和数据
            mRecyclerView.setAdapter(mAdapter);//使用适配器
            //LayoutManager进行布局管理
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);//第二个参数是说明这是垂直的布局
            mRecyclerView.setLayoutManager(linearLayoutManager);//通过LayoutManager控制显示风格

        }else if(mPage==2){
            view = inflater.inflate(R.layout.fragment_page2, container, false);
            FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Snackbar comes out", Snackbar.LENGTH_LONG)
                            .setAction("Action", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(
                                            getContext(),
                                            "Toast comes out",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                }
            });

        }else if(mPage==3){
            view = inflater.inflate(R.layout.fragment_page3, container, false);
        }
        return view;
    }
    public void initDatas(){
        mDatas=new ArrayList<String>();
        for(int i=0;i<20;i++){
            mDatas.add("这是第"+i+"行");
        }

    }

}