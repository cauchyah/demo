package com.lulu.lin.mac.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lin on 2017/12/10.
 * 波浪
 */

public class WaveView extends View implements View.OnClickListener {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int waveLenght = 800;//一个波浪的长度，类似一个周期的正弦函数
    private int waveCount;//屏幕内可以绘制的波浪个数
    private int centerY;//波浪中心点Y，相当于正弦函数的x轴（y=0）
    private int amplitude = 60;//振幅
    private Path mPath;
    private ValueAnimator mValueAnimator;
    private int offset = 0;


    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPath = new Path();
        mPaint.setColor(Color.parseColor("#ff00ff"));
        setOnClickListener(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        waveCount = Math.round(mWidth / waveLenght + 1.5f);
        centerY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-waveLenght, centerY);
        for (int i = 0; i < waveCount; i++) {
            mPath.quadTo(-waveLenght * 3 / 4 + i * waveLenght + offset, centerY + amplitude, -waveLenght / 2 + i * waveLenght + offset, centerY);
            mPath.quadTo(-waveLenght / 4 + i * waveLenght + offset, centerY - amplitude, waveLenght * i + offset, centerY);
        }
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onClick(View v) {
        mValueAnimator = ValueAnimator.ofInt(0, waveLenght);
        mValueAnimator.setDuration(1500);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }
}
