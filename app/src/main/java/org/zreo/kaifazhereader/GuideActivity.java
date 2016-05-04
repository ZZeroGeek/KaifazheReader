package org.zreo.kaifazhereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/22 0022.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {
    private Button startButton;
    private ViewPager mViewPager;
    private ViewPagerAdapter vpAdapter;
    private ArrayList<View> views;
    private View view1, view2, view3, view4;
    private ImageView[] bar;
    private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();


    }
    //初始化滑动的页面
    private void initView() {
        LayoutInflater mLi = LayoutInflater.from(this);
        view1 = mLi.inflate(R.layout.guide_view01, null);
        view2 = mLi.inflate(R.layout.guide_view02, null);
        view3 = mLi.inflate(R.layout.guide_view03, null);
        view4 = mLi.inflate(R.layout.guide_view04, null);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        views = new ArrayList<View>();
        vpAdapter = new ViewPagerAdapter(views);
        startButton= (Button)view4. findViewById(R.id.startButton);

    }
    //初始化数据
    private void initData() {
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(vpAdapter);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startbutton();
            }
        });
        vpAdapter.notifyDataSetChanged();
        initBar();
    }




    // 初始化下方横线
    private void initBar() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
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
    }
    //设置当前的下方横线的位置
    public void setCurbar(int positon){
        if (positon < 0 || positon > views.size() - 1 || currentIndex == positon) {
            return;
        }
        bar[positon].setEnabled(false);
        bar[currentIndex].setEnabled(true);
        currentIndex = positon;
        mViewPager.setCurrentItem(currentIndex);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        // 设置下方横线选中状态
        setCurbar(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //实现按钮的跳转
    private void startbutton() {
        Intent intent = new Intent();
        intent.setClass(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onDestroy() {
        mViewPager.removeOnPageChangeListener(this);
        super.onDestroy();
    }
}

