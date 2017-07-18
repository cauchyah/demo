package com.lulu.lin.mac.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lulu.lin.mac.R;

/**
 * Created by lin on 2017/7/17.
 */

public class SecondBezier extends View {
    private PointF mStart=new PointF();
    private PointF mEnd=new PointF();
    private PointF mControler=new PointF();
    private Paint mPaint;
    public SecondBezier(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);


    }

    public SecondBezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SecondBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int halfWidth=w/2;
        int halfHeight=h/2;
        mStart.set(halfWidth-100,halfHeight);
        mEnd.set(halfWidth+100,halfHeight);
        mControler.set(halfWidth,halfHeight-100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(mStart.x,mStart.y,mPaint);
        canvas.drawPoint(mEnd.x,mEnd.y,mPaint);
        canvas.drawPoint(mControler.x,mControler.y,mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(8);
        Path path=new Path();
        path.moveTo(mStart.x,mStart.y);
        path.quadTo(mControler.x,mControler.y,mEnd.x,mEnd.y);
        canvas.drawPath(path,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControler.x=event.getX();
        mControler.y=event.getY();
        invalidate();
        return true;
    }
}
