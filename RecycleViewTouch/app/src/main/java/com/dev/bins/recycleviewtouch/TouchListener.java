package com.dev.bins.recycleviewtouch;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by bin on 7/27/16.
 */
public class TouchListener implements RecyclerView.OnItemTouchListener {
    GestureDetectorCompat gestureDetectorCompat;

    public TouchListener(Context context,RecyclerView recyclerView) {
        gestureDetectorCompat = new GestureDetectorCompat(context,new GestureListener(context,recyclerView));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }



}
