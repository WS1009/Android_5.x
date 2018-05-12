package com.wangshun.test.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.wangshun.test.R;

import java.util.Random;

/**
 * Created by ThinkPad on 2018-03-12.
 * 自定义View的步骤：
 * 1、自定义View的属性
 * 2、在view的构造方法中获得我们自定义的属性
 * 3、重写onMeasure
 * 4、重写onDraw
 */

public class CustomTitleView extends TextView {

    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        int indexCount = typedArray.getIndexCount();

        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText = typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    mTitleTextColor = typedArray.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    mTitleTextSize = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
            }
        }

        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText=randomText();
                postInvalidate();
            }
        });
    }

    private String randomText() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width=0;
        int height=0;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textWidth = mBound.width();
            int desired = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            width=desired;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mBound);
            float textHeight = mBound.height();
            int desired = (int) (getPaddingLeft() + textHeight + getPaddingRight());
            height=desired;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}