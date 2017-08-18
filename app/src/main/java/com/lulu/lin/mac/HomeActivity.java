package com.lulu.lin.mac;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.lulu.lin.mac.view.BluetoothTestActivity;
import com.taobao.sophix.SophixManager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ActivityBean> mData = new ArrayList<>();
//    private TextInputLayout textinputlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        textinputlayout = (TextInputLayout) findViewById(R.id.textinputlayout);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

    }


    private void initData() {
        mData.add(new ActivityBean("MPChart", MPChartActivity.class));
        mData.add(new ActivityBean("scrollingActivity", ScrollingActivity.class));
        mData.add(new ActivityBean("drag&swipe", DragSwipeActivity.class));
        mData.add(new ActivityBean("贝塞尔曲线", BesierActivity.class));
        mData.add(new ActivityBean("蓝牙", BluetoothTestActivity.class));
        mData.add(new ActivityBean("VIEWPAGER",PagerActivity.class));
        mData.add(new ActivityBean("VLayout",VLayoutTestActivity.class));
        mData.add(new ActivityBean("FBUtton",FButtonActivity.class));
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
//        textinputlayout.setError("密码格式不正确");
        /*Intent intent=new Intent();
        intent.setAction("thisisaction");
        startActivity(intent);*/
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setTicker("ticker")
                .setContentText("content text")
                .setContentTitle("content title")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this,1,new Intent(this,ScrollingActivity.class),PendingIntent.FLAG_UPDATE_CURRENT));


        //自定义通知栏样式，添加remoteview
      /*  RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.home_item);
        remoteViews.setTextViewText(R.id.text,"this is remote text");
        PendingIntent pendingIntent2=PendingIntent.getActivity(this,2,new Intent(this,MPChartActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.text,pendingIntent2);
        builder.setContent(remoteViews);*/



        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }
}
