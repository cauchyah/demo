package com.lulu.lin.mac;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VLayoutTestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout_test);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < 20; i++) {
            mData.add("item====" + i);
        }


        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(mData.size());// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelp
        DelegateAdapter.Adapter linearAdapter = new MyAdapter(linearLayoutHelper, mData);

        // 创建对应的LayoutHelper对象
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add("top===" + i);
        }
        LinearLayoutHelper linearLayoutHelper2 = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 公共属性
        linearLayoutHelper.setItemCount(data.size());// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelp

        DelegateAdapter.Adapter linearAdapter2 = new MyAdapter(linearLayoutHelper2, data);
        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
        stickyLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        stickyLayoutHelper.setAspectRatio(8);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(1);// 设置吸边位置的偏移量
        List<String> listItem = new ArrayList<>();
        listItem.add("i am sticky");
        DelegateAdapter.Adapter StickyLayout = new MyAdapter(stickyLayoutHelper, listItem);


        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        adapters.add(linearAdapter2);
        adapters.add(StickyLayout);
        adapters.add(linearAdapter);
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(delegateAdapter);

    }


    private class MyAdapter extends DelegateAdapter.Adapter<MyViewHolder> {

        private LayoutHelper layoutHelper;
        private List<String> list;

        public MyAdapter(LayoutHelper layoutHelper, List<String> mData) {
            this.layoutHelper = layoutHelper;
            list = mData;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return layoutHelper;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(VLayoutTestActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ((TextView) holder.textView.findViewById(android.R.id.text1)).setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }


}
