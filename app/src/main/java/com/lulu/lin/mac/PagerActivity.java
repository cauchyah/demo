package com.lulu.lin.mac;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));

    }
    private static class MyViewPagerAdapter extends FragmentPagerAdapter{
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==1){
                return TestFragment.newInstance("position=="+position);
            }
            return BlankFragment.newInstance("position="+position);
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
