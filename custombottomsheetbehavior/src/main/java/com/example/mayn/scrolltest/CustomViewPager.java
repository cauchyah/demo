package com.example.mayn.scrolltest;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float lastX;
    float lastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
     /*   float currentX = ev.getX();
        float currentY = ev.getY();
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            getParent().requestDisallowInterceptTouchEvent(true);

        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(lastX - currentX) - Math.abs(lastY - currentY) > 0) {

            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }*/
        Log.d("tag","viewpager  dispatchTouchEvent,"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("tag","viewpager  onTouchEvent,"+ev.getAction());
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("tag","viewpager  onInterceptTouchEvent,"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }
}
