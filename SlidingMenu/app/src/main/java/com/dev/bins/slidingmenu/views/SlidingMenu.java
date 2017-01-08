package com.dev.bins.slidingmenu.views;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by bin on 04/01/2017.
 */

public class SlidingMenu extends FrameLayout {


    private View mMenuView;
    private View mMainView;

    private ViewDragHelper mViewDragHelper;
    private int mWidth;
    private float mRange;

    private DragState mDragState = DragState.CLOSE;

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return mMenuView == child || mMainView == child;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                if (mMainView == child) {
                    if (left < 0) left = 0;
                    if (left > mRange) left = (int) mRange;
                }

                return left;
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return (int) mRange;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                if (mMenuView == changedView) {
                    mMenuView.layout(0, 0, mMenuView.getMeasuredWidth(), mMenuView.getMeasuredHeight());
                    int newLeft = mMainView.getLeft() + dx;
                    if (newLeft < 0) newLeft = 0;
                    if (newLeft > mRange) newLeft = (int) mRange;
                    mMainView.layout(newLeft, mMainView.getTop(), newLeft + mMainView.getMeasuredWidth(), mMainView.getBottom());
                }
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (mMainView.getLeft() < mRange / 2) {
                    mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                    ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    mDragState = DragState.CLOSE;
                } else {
                    mViewDragHelper.smoothSlideViewTo(mMainView, (int) mRange, 0);
                    ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    mDragState = DragState.OPEN;
                }

                if (xvel > 200 && mDragState != DragState.OPEN) {
                    mViewDragHelper.smoothSlideViewTo(mMainView, (int) mRange, 0);
                    ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    mDragState = DragState.OPEN;
                }

                if (xvel < -200 && mDragState != DragState.CLOSE) {
                    mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                    ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
                    mDragState = DragState.CLOSE;
                }


            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mRange = mWidth * 0.6f;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 2) {
            throw new IllegalArgumentException("SlidingMenu only have 2 children");
        }

        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);


    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(SlidingMenu.this);
        }
    }


    enum DragState {
        OPEN, CLOSE
    }
}
