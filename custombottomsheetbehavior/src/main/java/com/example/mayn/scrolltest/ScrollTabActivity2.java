package com.example.mayn.scrolltest;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ScrollTabActivity2 extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private View mBlankView;
    private ScrollLinearLayout2 sll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_tab2);

        initViews();
    }

    private void initViews() {
        int height = DisplayUtils.getStatusBarHeight(this);
        Toast.makeText(this, "height:" + height, Toast.LENGTH_SHORT).show();
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(i + "");
        }

        RecyclerView bottomRv = findViewById(R.id.bottom_sheet_rv);
        bottomRv.setLayoutManager(new LinearLayoutManager(this));
        BaseQuickAdapter<String, BaseViewHolder> mAdapter2 = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_rv, data) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item, "item=" + item);
            }
        };
        bottomRv.setAdapter(mAdapter2);
        /*mTabHost = findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(new ColorDrawable(Color.TRANSPARENT));
        TabHost.TabSpec tabSpec = mTabHost.newTabSpec(TaskFragment.class.getName())
                .setIndicator(TabViewUtils.getTabItemView(this, R.mipmap.ic_launcher, "任务",
                        R.color.colorPrimary));
        mTabHost.addTab(tabSpec, TaskFragment.class, null);

        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec(ToolsFragment.class.getName())
                .setIndicator(TabViewUtils.getTabItemView(this, R.mipmap.ic_launcher, "工具", R.color.colorPrimary));
        mTabHost.addTab(tabSpec1, ToolsFragment.class, null);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override public void onTabChanged(String tabId) {

            }
        });*/

        int blankViewHeight = (int) (DisplayUtils.getScreenHeightPixels(this)
                        - DisplayUtils.getAppContentHeight(this)*(1.0f/15)
                        - DisplayUtils.getStatusBarHeight(this));
        sll = findViewById(R.id.sll);
        sll.setContentViewTranslateY(blankViewHeight);
    }
}
