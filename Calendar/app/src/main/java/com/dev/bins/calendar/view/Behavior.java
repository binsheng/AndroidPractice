package com.dev.bins.calendar.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bin on 11/10/2017.
 */

public class Behavior extends CoordinatorLayout.Behavior<RecyclerView> {


    public Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        RecycleViewCalendar calendarView = (RecycleViewCalendar) parent.getChildAt(0);
        int height = calendarView.getMeasuredHeight();
        if (calendarView.getState() != RecycleViewCalendar.STATE_COLLAPSE) {
            parent.onLayoutChild(child, layoutDirection);
            child.offsetTopAndBottom(height);
        }
        return true;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View directTargetChild, View target, int nestedScrollAxes) {
//        System.out.println("onStartNestedScroll");
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dx, int dy, int[] consumed) {
//        System.out.println("onNestedPreScroll");

        RecycleViewCalendar calendarView = (RecycleViewCalendar) coordinatorLayout.getChildAt(0);
        int top = child.getTop();
        int height = calendarView.getMeasuredHeight();
        if (top >= height && dy <0) {
            consumed[0] = 0;
            consumed[1] = 0;
            child.offsetTopAndBottom(height - top);
            calendarView.offsetTopAndBottom(-calendarView.getTop());
            return;
        }
        if (top<=calendarView.getMinTop() && dy > 0){
            consumed[0] = 0;
            consumed[1] = 0;
            return;
        }
        child.offsetTopAndBottom(-dy);
//        calendarView.offsetTopAndBottom((int) (-dy*0.8));
        calendarView.onScroll(dy);

    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target) {
        RecycleViewCalendar calendarView = (RecycleViewCalendar) coordinatorLayout.getChildAt(0);
        int top = child.getTop();
        int height = calendarView.getMeasuredHeight();

        calendarView.onStateChange(top>height/2);

    }


    //    @Override
//    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        System.out.println("dyConsumed:"+dyConsumed);
//        System.out.println("dyUnconsumed:"+dyUnconsumed);
//        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
//        RecycleViewCalendar calendarView = (RecycleViewCalendar) coordinatorLayout.getChildAt(0);
//        calendarView.offsetTopAndBottom(-dyUnconsumed);
//    }
}
