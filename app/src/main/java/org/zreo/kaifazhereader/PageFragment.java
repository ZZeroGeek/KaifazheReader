package org.zreo.kaifazhereader;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.logging.LogRecord;

/**
 * Created by 85789 on 2016/4/19.
 */
public class PageFragment extends Fragment implements ViewPager.OnPageChangeListener {

    public static final String ARG_PAGE = "ARG_PAGE";
   private int mPage;
    private SimpleRecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<SaveNews> mDatas;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button landButton;
    Intent intent;

    private Button startButton;
    private ViewPager mViewPager;
    private ViewPagerAdapter vpAdapter;
    private ArrayList<View> views;
    private View view1, view2, view3, view4;
    private static final int[] page = {R.layout.guide_view01,R.layout.guide_view02,R.layout.guide_view03,R.layout.guide_view04};
    private ImageView[] bar;
    private int currentIndex;

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
        SharedPreferences sp=getActivity().getSharedPreferences("flag",getActivity().MODE_PRIVATE);
        Boolean flag=sp.getBoolean("flag",false);

        if(mPage==0) {
            view = inflater.inflate(R.layout.fragment_page, container, false);
        //   ImageView mDefaultImage = (ImageView) view.findViewById(R.id.home_default_image);
          //  mDefaultImage.setVisibility(View.GONE);
           // advPager.setVisibility(View.VISIBLE);

          //  LayoutInflater mLi = LayoutInflater.from(getContext());
         //   view1 = mLi.inflate(R.layout.guide_view01, null);
         //   view2 = mLi.inflate(R.layout.guide_view02, null);
         //   view3 = mLi.inflate(R.layout.guide_view03, null);
         //   view4 = mLi.inflate(R.layout.guide_view04, null);
            mViewPager = (ViewPager) view.findViewById(R.id.viewpager1);
            views = new ArrayList<View>();
            ImageView img1 = new ImageView(getContext());
            img1.setBackgroundResource(R.drawable.tutorial_1);
            views.add(img1);
            ImageView img2 = new ImageView(getContext());
            img2.setBackgroundResource(R.drawable.tutorial_2);
            views.add(img2);
            ImageView img3 = new ImageView(getContext());
            img3.setBackgroundResource(R.drawable.tutorial_3);
            views.add(img3);
            ImageView img4 = new ImageView(getContext());
            img4.setBackgroundResource(R.drawable.tutorial_4);
            views.add(img4);
            vpAdapter = new ViewPagerAdapter(views);

            mViewPager.addOnPageChangeListener(this);
            mViewPager.setAdapter(vpAdapter);
       //     views.add(view1);
       //     views.add(view2);
         //   views.add(view3);
         //   views.add(view4);
            vpAdapter.notifyDataSetChanged();



            LinearLayout linearLayout = (LinearLayout)view. findViewById(R.id.we);
            bar = new ImageView[views.size()];
            // 循环取得下方横线图片
            for (int i = 0; i < views.size(); i++) {
                bar[i] = (ImageView) linearLayout.getChildAt(i);
                bar[i].setEnabled(true);
                bar[i].setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
                        int position = (Integer)v.getTag();
                        setCurbar(position);
                    }
                });
                // 设置位置tag，方便取出与当前位置对应
                bar[i].setTag(i);
            }
            // 设置当面默认的位置
            currentIndex = 0;
            bar[currentIndex].setEnabled(false);

/*

            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swiperefreshlayout);
            mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
            mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
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
*/
           // loadData();
            saveYouNews();
         //   setAdapter();
            mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mAdapter = new SimpleRecyclerViewAdapter(getContext(), mDatas);//为适配器传入上下文和数据
            mRecyclerView.setAdapter(mAdapter);
            /*
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    // TODO Auto-generated method stub
                   Toast.makeText(getContext(),"刷新开始",Toast.LENGTH_LONG).show();
                  //  if (mSwipeRefreshLayout.isRefreshing()==true){}
                        /*
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
                        */
            /*
                    Toast.makeText(getContext(),"刷新结束",Toast.LENGTH_LONG).show();
                }
            });
    */
        }
         else if(mPage==1){
            if(flag){
                view = inflater.inflate(R.layout.fragment_page2_land, container, false);
            }
            else {
                view = inflater.inflate(R.layout.fragment_page2, container, false);
                landButton = (Button) view.findViewById(R.id.landButton);
                landButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent(getContext(), LandActivity.class);
                        startActivity(intent);
                    }
                });
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
            /*
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
*/
            }
        }else if(mPage==2){
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
    /*
    private void loadData() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000);
                     saveYouNews();
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
    */
    public void saveYouNews(){
        mDatas=new ArrayList<SaveNews>();
        mDatas.add(new SaveNews("详解应对平台高并发的分布式调度框架TBSchedule",R.drawable.news_1,"来自 开发分享",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("互联网服务端技术——如何学（上）",R.drawable.news_2,"来自 全栈感知营的主题",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("如何优雅的使用 phpStorm 开发工具",R.drawable.news_3,"来自 Code Collection",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("全栈工程师的黑魔法（文档篇）",R.drawable.news_4,"来自 Phodal",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("『 Spark 』5. 这些年，你不能错过的 spark 学习资源",R.drawable.news_5,"来自 大数据那些事",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("Go性能优化技巧 1/10",R.drawable.news_6,"来自 大数据那些事",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));
        mDatas.add(new SaveNews("底部导航栏（Bottom navigation）设计规范指南",R.drawable.news_7,"来自 尤条子",R.drawable.ic_item_comment,R.drawable.ic_item_like,"666","666"));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurbar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setCurbar(int positon){
        if (positon < 0 || positon > views.size() - 1 || currentIndex == positon) {
            return;
        }
        bar[positon].setEnabled(false);
        bar[currentIndex].setEnabled(true);
        currentIndex = positon;
        mViewPager.setCurrentItem(currentIndex);
    }

}