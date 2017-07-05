package com.lulu.lin.mac;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by lin on 2017/6/26.
 */

public class ParentLayout extends LinearLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper nestedScrollingParentHelper;
    private int mImgHeight;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mMinVelocity;
    private boolean isFirst=true;
    private RecyclerView recyclerView;

    public ParentLayout(Context context) {
        super(context);
        init();
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        mScroller = new Scroller(getContext());
        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewGroup.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        boolean isImageVisible = (dy > 0 && getScrollY() < mImgHeight) || (dy < 0 &&getScrollY()>0&&!ViewCompat.canScrollVertically(target,-1));
        if (isImageVisible) {
            consumed[1] = dy;
            scrollBy(0, dy);
        }

    }

    @Override
    public void onStopNestedScroll(View child) {
        nestedScrollingParentHelper.onStopNestedScroll(child);
    }

   @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY()>=mImgHeight) return false;
        fling(velocityY);
        return true;
    }

   /* @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        if (getScrollY()>=mImgHeight) return false;
        fling(velocityY);
        return true;
    }*/

    private void fling(float velocityY) {
        if (!mScroller.isFinished()) mScroller.abortAnimation();
        mScroller.fling(0,getScrollY(),0, (int) velocityY,0,0,0,mImgHeight);

    }

    @Override
    protected void onFinishInflate() {
        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
        imageview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImgHeight = imageview.getMeasuredHeight();
                imageview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = recyclerView.getLayoutParams();
        params.height = getMeasuredHeight() -mImgHeight;
        setMeasuredDimension(getMeasuredWidth(), mImgHeight +recyclerView.getMeasuredHeight());
    }

    @Override
    public void scrollTo(@Px int x, @Px int y) {
        if (y >= mImgHeight) {
            y = mImgHeight;
            if (isFirst){

                isFirst=false;
            }
        }
        if (y < 0)
            y = 0;
        super.scrollTo(x, y);
    }

    private float mlastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y=event.getY();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mlastY=y;
                return true;

            case MotionEvent.ACTION_MOVE:
                int diff= (int) (mlastY-y);
                scrollBy(0,diff);
                mlastY=y;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                countVelocityTracker();
                break;
        }

        return super.onTouchEvent(event);
    }
    private void countVelocityTracker() {
        mVelocityTracker.computeCurrentVelocity(1000);
        float yVelocity = mVelocityTracker.getYVelocity();
        if (Math.abs(yVelocity) > mMinVelocity) {
            mScroller.fling(0,getScrollY(), 0,-(int) yVelocity, 0,0,0, mImgHeight);
        }
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            Log.d("a",mScroller.getCurrY()+"=======");
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
