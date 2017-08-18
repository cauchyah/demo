package com.lulu.lin.mac.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lin on 2017/8/15.
 */

public class CalculatorView extends View {
    private Paint mPaint;
    private Paint mTextPaint;
    private boolean isPress = false;
    private int mWidth;
    private float mRowHeight;
    private int row;
    private int col;

    public CalculatorView(Context context) {
        this(context, null);
    }

    public CalculatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalculatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#ff0000"));
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.parseColor("#bababa"));
        mTextPaint.setTextSize(44);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isPress) {
            mPaint.setColor(Color.parseColor("#cfcfcf"));
            float startY, endY;
            if (col > 2) {
                if (row <= 1) {
                    startY = 0;
                    endY = mRowHeight * 2;
                } else {
                    startY = mRowHeight * 2;
                    endY = mRowHeight * 4;
                }
            } else {
                startY = mRowHeight * row;
                endY = mRowHeight * (row + 1);
            }
            canvas.drawRect(col * mRowHeight, startY, (col + 1) * mRowHeight, endY, mPaint);

        }
        mPaint.setColor(Color.parseColor("#bababa"));
        //画文字
        mTextPaint.setColor(Color.parseColor("#bababa"));
        float value = mTextPaint.measureText("9", 0, 1);
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();

        float startYPosition = mRowHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2;
        canvas.drawText("9", mRowHeight / 2 - value / 2, startYPosition, mTextPaint);


        canvas.drawText("8", mRowHeight + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText("7", mRowHeight * 2 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);


        startYPosition = mRowHeight + mRowHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2;
        canvas.drawText("6", mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText("5", mRowHeight + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText("4", mRowHeight * 2 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);

        startYPosition = mRowHeight * 2 + mRowHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2;
        canvas.drawText("3", mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText("2", mRowHeight + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText("1", mRowHeight * 2 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);

        startYPosition = mRowHeight * 3 + mRowHeight / 2 - fm.descent + (fm.bottom - fm.top) / 2;
        value = mTextPaint.measureText("00", 0, 2);
        canvas.drawText("00", mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        value = mTextPaint.measureText("0", 0, 1);
        canvas.drawText("0", mRowHeight + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        canvas.drawText(".", mRowHeight * 2 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);

        startYPosition = mRowHeight - fm.descent + (fm.bottom - fm.top) / 2;
        canvas.drawText("C", mRowHeight * 3 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);
        value = mTextPaint.measureText("添加", 0, 2);
        startYPosition = mRowHeight * 3 - fm.descent + (fm.bottom - fm.top) / 2;
        mTextPaint.setColor(Color.parseColor("#169bd5"));
        canvas.drawText("添加", mRowHeight * 3 + mRowHeight / 2 - value / 2, startYPosition, mTextPaint);

        //画横线

        canvas.drawLine(0, 0, getRight(), 0, mPaint);
        canvas.drawLine(0, mRowHeight, mWidth - mRowHeight, mRowHeight, mPaint);
        canvas.drawLine(0, mRowHeight * 2, mWidth, mRowHeight * 2, mPaint);
        canvas.drawLine(0, mRowHeight * 3, mWidth - mRowHeight, mRowHeight * 3, mPaint);
        canvas.drawLine(0, mWidth, getRight(), mWidth, mPaint);

        //画竖线
        canvas.drawLine(0, 0, 0, mWidth, mPaint);
        canvas.drawLine(mRowHeight, 0, mRowHeight, mWidth, mPaint);
        canvas.drawLine(mRowHeight * 2, 0, mRowHeight * 2, mWidth, mPaint);
        canvas.drawLine(mRowHeight * 3, 0, mRowHeight * 3, mWidth, mPaint);
        canvas.drawLine(mRowHeight * 4, 0, mRowHeight * 4, mWidth, mPaint);


//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mRowHeight = mWidth / 4.0f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPosition = event.getX();
        float yPosition = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                col = (int) Math.floor(xPosition / mRowHeight);
                row = (int) Math.floor(yPosition / mRowHeight);

                isPress = true;
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                isPress = false;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
