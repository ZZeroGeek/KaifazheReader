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
    private static final int[] page = {R.layout.guide_view01,R.layout.guide_view02,R.layout.guide_view03,R.layout.guide_view04};
    private ImageView[] bar;
    private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initData();

    }

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

    private void initData() {
        initPoint();
        mViewPager.setOnPageChangeListener(this);
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
        initPoint();
    }

    private void initPoint() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);

        bar = new ImageView[page.length];

        for (int i = 0; i < page.length; i++) {
            bar[i] = (ImageView) linearLayout.getChildAt(i);
            bar[i].setEnabled(true);
            bar[i].setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    int position = (Integer)v.getTag();
                    setCurDot(position);
//                    setCurView(position);
                         }
            });
            bar[i].setTag(i);
        }

        currentIndex = 0;
        bar[currentIndex].setEnabled(false);
    }
//    private void setCurView(int position){
//        if (position < 0 || position >= page.length) {
//            return;
//        }
//        mViewPager.setCurrentItem(position);
//    }
    private void setCurDot(int positon){
        if (positon < 0 || positon > page.length - 1 || currentIndex == positon) {
            return;
        }
        bar[positon].setEnabled(false);
        bar[currentIndex].setEnabled(true);

//        currentIndex = positon;
        mViewPager.setCurrentItem(currentIndex);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void startbutton() {
        Intent intent = new Intent();
        intent.setClass(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }
 }

