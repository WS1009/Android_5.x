package com.wangshun.test.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 嵌套滑动机制父View
 */

public class MyNestedScrollTestParent extends LinearLayout implements NestedScrollingParent
{

    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private ImageView img;
    private TextView tv;
    private int imgHeight;
    private int tvHeight;
    private RecyclerView mRecyclerView;

    public MyNestedScrollTestParent(Context context)
    {
        this(context, null);
    }

    public MyNestedScrollTestParent(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public MyNestedScrollTestParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        this(context, attrs, defStyleAttr, 0);
    }

    public MyNestedScrollTestParent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        System.out.println("MyNestedScrollTestParent");
        init();
    }

    private void init()
    {
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }


    @Override
    protected void onFinishInflate()
    {
        super.onFinishInflate();
        img = (ImageView) getChildAt(0);
        tv = (TextView) getChildAt(1);
        mRecyclerView = (RecyclerView) getChildAt(2);
        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (imgHeight <= 0) {
                    imgHeight = img.getMeasuredHeight();
                }
            }
        });

        tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                if (tvHeight <= 0) {
                    tvHeight = img.getMeasuredHeight();
                }
            }
        });
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes)
    {
        if (target instanceof RecyclerView && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed)
    {

        //上滑且顶部控件未完全隐藏，getScrollY可以理解为幕布
        boolean hiddenTop = dy > 0 && getScrollY() < imgHeight;

        //下滑且内部View已经无法继续下拉
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {//如果需要显示或隐藏图片，即需要自己(parent)滚动
            scrollBy(0, dy);//滚动
            //consumed[1] = dy;//告诉child我消费了多少
        }
    }

    //scrollBy内部会调用scrollTo
    //限制滚动范围
    @Override
    public void scrollTo(int x, int y)
    {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }

        super.scrollTo(x, y);
    }
}