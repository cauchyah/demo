package com.lulu.lin.mac;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Parcelable;
import android.support.constraint.solver.widgets.Rectangle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    PendingIntent intent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        TextView text=(TextView) findViewById(R.id.text);
        LevelListDrawable level= (LevelListDrawable) text.getBackground();
        level.setLevel(2);
        ShapeDrawable shapeDrawable=new ShapeDrawable();


    }

    public void onShow(View view) {
        Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
        intent.putExtra("from", "something");
        intent1 = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60, intent1);
    }

    public void stop(View view) {
        alarmManager.cancel(intent1);
    }
}
