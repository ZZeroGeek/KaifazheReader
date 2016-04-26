package org.zreo.kaifazhereader;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mDatas;
    private List<String> mData;
    private SimpleRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // recyclerViewIn();
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        NavigationView navigationView =
                (NavigationView) findViewById(R.id.nv_main_navigation);
        //判断抽屉窗口是否为空值
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        //获取viewpager，并对viewpager设置
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void recyclerViewIn() {
        //   initDatas();//初始化数据
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerView);
        //   mAdapter = new SimpleRecyclerViewAdapter(getContext(), mDatas);//为适配器传入上下文和数据
        //   mRecyclerView.setAdapter(mAdapter);//使用适配器
        //LayoutManager进行布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false) {
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 6000;
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


    }



    private void loadData() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    int index = mDatas.size();
                    for (int i = index; i < index + 20; i++) {
                        mDatas.add("第" + i + "个数据");
                    }
                    runOnUiThread(new Runnable() {
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

    private void setAdapter() {
        if (mAdapter == null) {
            mAdapter = new SimpleRecyclerViewAdapter(this, mDatas);//为适配器传入上下文和数据
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onRefresh() {
        mDatas.clear();
        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //重写setupViewPager方法；并填充选项卡TabLayout
    private void setupViewPager() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("订阅");
        titles.add("发现");
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        fragments.add(new ListFragment());
        //获取FragmentAdapter，调用ViewPager
        FragmentAdapter adapter =
                new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    //重写NavigationView
    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    public void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mDatas.add("这是第" + i + "行");
        }
    }
}