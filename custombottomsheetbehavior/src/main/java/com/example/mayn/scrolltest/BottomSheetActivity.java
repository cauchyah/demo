package com.example.mayn.scrolltest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);


       /* ViewPager contentPager=findViewById(R.id.content_viewpager);
        contentPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return new BlankFragment();
            }
        });
        contentPager.setOffscreenPageLimit(3);*/
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(i + "");
        }
        BaseQuickAdapter<String, BaseViewHolder> mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_rv, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item, "item=" + item);
            }
        };
        rv_content.setAdapter(mAdapter);

     /*   RecyclerView bottomRv = findViewById(R.id.bottom_sheet_rv);
        bottomRv.setLayoutManager(new LinearLayoutManager(this));
        BaseQuickAdapter<String, BaseViewHolder> mAdapter2 = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_rv, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item, "item=" + item);
            }
        };
        bottomRv.setAdapter(mAdapter2);*/


        final RelativeLayout design_bottom_sheet = findViewById(R.id.design_bottom_sheet);
        final CustomBottomSheetBehavior bottomSheetBehavior = CustomBottomSheetBehavior.from(design_bottom_sheet);
        bottomSheetBehavior.setBottomSheetCallback(new CustomBottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {


            }
        });


        /*View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet_dialog, null, false);

        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.dialog);
        dialog.setContentView(view);
        findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });*/


        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return new BlankFragment();
            }
        });
       // viewPager.setOffscreenPageLimit(3);
    }
}
