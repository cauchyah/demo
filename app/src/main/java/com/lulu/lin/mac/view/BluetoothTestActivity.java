package com.lulu.lin.mac.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lulu.lin.mac.DragSwipeActivity;
import com.lulu.lin.mac.HomeActivity;
import com.lulu.lin.mac.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class BluetoothTestActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE = 11;
    private BroadcastReceiver mHomeKeyEventReceiver;
    private AlarmManager mAlarmManager;
    private PendingIntent pi;
    private RecyclerView recyclerView;
    private ArrayList<BluetoothDevice> mData = new ArrayList<>();
    private MyAdapter mAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bluetooth_test);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BluetoothTestActivity.MyAdapter();
        recyclerView.setAdapter(mAdapter);

//        监听是否点击了home键将客户端推到后台
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mHomeKeyEventReceiver = new BroadcastReceiver() {
            String SYSTEM_REASON = "reason";
            String SYSTEM_HOME_KEY = "homekey";
            String SYSTEM_HOME_KEY_LONG = "recentapps";

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    String reason = intent.getStringExtra(SYSTEM_REASON);
                    if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                        //表示按了home键,程序到了后台
//                        Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_SHORT).show();
                        restartAfter();
                    } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
                        //表示长按home键,显示最近使用的程序列表
                        Toast.makeText(getApplicationContext(), "recent", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    mData.add(device);
                    mAdapter.notifyDataSetChanged();
                }


            }
        };


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mHomeKeyEventReceiver != null) {
            registerReceiver(mHomeKeyEventReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        }
        if (mAlarmManager != null && pi != null) {
            mAlarmManager.cancel(pi);

        }
        if (mReceiver != null)
            registerReceiver(mReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

    }


    private void restartAfter() {
        pi = PendingIntent.getActivity(this, 1, new Intent(this, BluetoothTestActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000, pi);

    }

    public void onNext(View view) {
        Intent intent = new Intent(this, DragSwipeActivity.class);
        startActivity(intent);
    }

    public void onBluetooth(View view) {
        //点击开启蓝牙
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "不支持蓝牙设备", Toast.LENGTH_SHORT).show();
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE);
            } else {
                //开始搜索
//                Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//                startActivity(discoverableIntent);
                mBluetoothAdapter.startDiscovery();


            }


        }
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());
            } catch (IOException e) {
            }
            mmSocket = tmp;
        }

        public void run() {
            //在连接的时候取消扫描
            mBluetoothAdapter.cancelDiscovery();

            try {

                mmSocket.connect();
            } catch (IOException connectException) {
                try {
                    mmSocket.close();
                } catch (IOException closeException) {
                }
                return;
            }


        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
            }
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<BluetoothTestActivity.MyViewHolder> {

        @Override
        public BluetoothTestActivity.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new BluetoothTestActivity.MyViewHolder(LayoutInflater.from(BluetoothTestActivity.this).inflate(R.layout.home_item, parent, false));
        }

        @Override
        public void onBindViewHolder(BluetoothTestActivity.MyViewHolder holder, final int position) {
            holder.text.setText(mData.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new ConnectThread(mData.get(position)).start();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ENABLE && requestCode == RESULT_OK) {
//            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//// If there are paired devices
//            if (pairedDevices.size() > 0) {
//                // Loop through paired devices
//                for (BluetoothDevice device : pairedDevices) {
//                    // Add the name and address to an array adapter to show in a ListView
//                    mData.add(device);
//                }
//                mAdapter.notifyDataSetChanged();
//            }
            mBluetoothAdapter.startDiscovery();


        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mHomeKeyEventReceiver != null) {
            unregisterReceiver(mHomeKeyEventReceiver);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);

    }
}
