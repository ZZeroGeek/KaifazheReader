package org.zreo.kaifazhereader;


import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 85789 on 2016/4/19.
 */
public class PageFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
   private int mPage;
    private SimpleRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SwipeRefreshLayout mSwipeRefreshLayout;
   private Handler handler=new Handler() {
       @Override
       public void close() {

       }

       @Override
       public void flush() {

       }

       @Override
       public void publish(LogRecord record) {

       }
       public void handleMessage(Message msg){
           setAdapter();
           mSwipeRefreshLayout.setRefreshing(false);
       }
   };
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
            //   initDatas();//初始化数据
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefreshlayout);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
         //   mSwipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) getContext());
            mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
            //   mAdapter = new SimpleRecyclerViewAdapter(getContext(), mDatas);//为适配器传入上下文和数据
            //   mRecyclerView.setAdapter(mAdapter);//使用适配器
            //LayoutManager进行布局管理
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
                protected int getExtraLayoutSpace(RecyclerView.State state) {
                    return 3000;
                }
            };//第二个参数是说明这是垂直的布局
            mRecyclerView.setLayoutManager(linearLayoutManager);//通过LayoutManager控制显示风格
            mDatas = new ArrayList<>();

            mSwipeRefreshLayout.post(new Runnable() {

                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });

            loadData();
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // TODO Auto-generated method stub
                   Toast.makeText(getContext(),"刷新开始",Toast.LENGTH_LONG).show();
                    if (mSwipeRefreshLayout.isRefreshing()==true)
                        getActivity().runOnUiThread(new Runnable() {
                            //  mSwipeRefreshLayout.setRefreshing(true);

                            public void run() {
                                mDatas = new ArrayList<>();
                                mDatas.clear();
                                int index = mDatas.size();
                                for (int i = index; i < index + 20; i++) {
                                    mDatas.add("新的第" + i + "个数据");
                                }
                                setAdapter();
                                mSwipeRefreshLayout.setRefreshing(false);
                                Toast.makeText(getContext(), "刷新中", Toast.LENGTH_LONG).show();
                            }
                        });
                    Toast.makeText(getContext(),"刷新结束",Toast.LENGTH_LONG).show();
                }
            });
        }
         else if(mPage==2){
            view = inflater.inflate(R.layout.fragment_page2, container, false);
             mRecyclerView = (RecyclerView)view. findViewById(R.id.id_recyclerView);
             /*
             Runnable runnable = new Runnable() {
                 @Override
                 public void run() {  //10秒后执行该方法
                     // handler自带方法实现定时器
                     try {
                         mRecyclerView.setVisibility(View.INVISIBLE);  //隐藏
                         System.out.println("do...");
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                         System.out.println("exception...");
                     }
                 }
             };*/
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
    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new SimpleRecyclerViewAdapter(getContext(), mDatas);//为适配器传入上下文和数据
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void loadData() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    int index = mDatas.size();
                    for (int i = index; i < index + 20; i++) {
                        mDatas.add("第" + i + "个数据");
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapter();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }.start();

    }
    private void loadData1() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                    int index = mDatas.size();
                    for (int i = index; i < index + 20; i++) {
                        mDatas.add("新的第" + i + "个数据");
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setAdapter();
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }.start();

    }

}