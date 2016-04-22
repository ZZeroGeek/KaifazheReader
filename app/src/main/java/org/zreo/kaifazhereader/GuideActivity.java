package org.zreo.kaifazhereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

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
    }

    private void initPoint() {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

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
