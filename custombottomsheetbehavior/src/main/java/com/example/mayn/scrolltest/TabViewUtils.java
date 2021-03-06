package com.example.mayn.scrolltest;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TabViewUtils {
    public static View getTabItemView(Context context, int iconId, String title, int tabSelectedColor) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_main_tab_view, null);
        if (iconId != 0) {
            ImageView imageView = view.findViewById(R.id.iv_tab_icon);
            imageView.setImageDrawable(ContextCompat.getDrawable(context,iconId));
            imageView.setVisibility(View.VISIBLE);
        }
        TextView textView = view.findViewById(R.id.tv_tab_title);
        textView.setText(title);

        return view;
    }
}
