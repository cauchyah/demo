package com.lulu.lin.mac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragSwipeActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_swipe);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        preparaData();
        adapter = new DragSwipeActivity.MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback=new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                if (viewHolder.getItemViewType() != target.getItemViewType()) {
                    return false;
                }

                // Notify the adapter of the move
                adapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.onSwipe(viewHolder.getAdapterPosition());
            }
        };
        /**
         * 实例化ItemTouchHelper对象,然后添加到RecyclerView
         */
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

    }

    private void preparaData() {
        for (int i = 0; i < 30; i++) {
            mData.add("data="+i);

        }
    }

    private class MyAdapter extends RecyclerView.Adapter<DragSwipeActivity.MyViewHolder> implements OnItemCallbackListener{

        @Override
        public DragSwipeActivity.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new DragSwipeActivity.MyViewHolder(LayoutInflater.from(DragSwipeActivity.this).inflate(R.layout.home_item, parent, false));
        }

        @Override
        public void onBindViewHolder(DragSwipeActivity.MyViewHolder holder, final int position) {
            holder.text.setText(mData.get(position));

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public void onMove(int fromPosition, int toPosition) {
            /**
             * 在这里进行给原数组数据的移动
             */
            Collections.swap(mData, fromPosition, toPosition);
            /**
             * 通知数据移动
             */
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onSwipe(int position) {
            /**
             * 原数据移除数据
             */
            mData.remove(position);
            /**
             * 通知移除
             */
            notifyItemRemoved(position);
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
