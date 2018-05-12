package com.wangshun.test.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.widget.LinearLayout;


/**
 * Created by Administrator on 2017/2/14 0014.
 * E-Mil：543441727@qq.com
 */

public class MyNestedScrollTestChild extends LinearLayout implements NestedScrollingChild {

    public MyNestedScrollTestChild(Context context) {
        super(context);
    }

    public MyNestedScrollTestChild(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollTestChild(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyNestedScrollTestChild(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}