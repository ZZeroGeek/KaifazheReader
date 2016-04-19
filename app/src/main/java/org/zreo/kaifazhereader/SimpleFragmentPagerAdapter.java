package org.zreo.kaifazhereader;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 85789 on 2016/4/19.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"tab1","tab2","tab3"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }


    public int getCount() {
        return PAGE_COUNT;
    }


    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}