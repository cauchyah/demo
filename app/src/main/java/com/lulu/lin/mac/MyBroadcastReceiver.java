package com.lulu.lin.mac;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by lin on 2017/5/23.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("a","=======");
        Toast.makeText(context,intent.getStringExtra("from"),Toast.LENGTH_SHORT).show();
    }
}
