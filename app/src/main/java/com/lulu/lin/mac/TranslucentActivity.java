package com.lulu.lin.mac;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

public class TranslucentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
//            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            getWindow().setStatusBarColor(Color.WHITE);
//        }

        setContentView(R.layout.activity_translucent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("这是标题");
        final float scale = getApplication().getResources().getDisplayMetrics().density;
        int statusHeight = 0;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            statusHeight = 0;
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        statusHeight = (int) (45 * scale + 0.5f);
//        }
        toolbar.getLayoutParams().height = getStatusBarHeight(this) + statusHeight;
        Toast.makeText(this, toolbar.getLayoutParams().height + "h", Toast.LENGTH_SHORT).show();
        setSupportActionBar(toolbar);

    }

    public static int getStatusBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.top == 0 ? 60 : rect.top;
    }
}
