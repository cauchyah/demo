package com.lulu.lin.mac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ActivityBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
    }


    private void initData() {
        mData.add(new ActivityBean("MPChart", MPChartActivity.class));
        mData.add(new ActivityBean("scrollingActivity", ScrollingActivity.class));
        mData.add(new ActivityBean("drag&swipe", DragSwipeActivity.class));
        /*for (int i = 0; i < 20; i++) {
            mData.add(new ActivityBean("drag&swipe", DragSwipeActivity.class));
        }*/

    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MyViewHolder(LayoutInflater.from(HomeActivity.this).inflate(R.layout.home_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.text.setText(mData.get(position).getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, mData.get(position).getaClass());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public void onChange(View view){
        Toast.makeText(this,"i have change,xixi",Toast.LENGTH_LONG).show();
    }
}
