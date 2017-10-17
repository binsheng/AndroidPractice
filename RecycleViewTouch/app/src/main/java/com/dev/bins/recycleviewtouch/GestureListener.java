package com.dev.bins.recycleviewtouch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by bin on 7/27/16.
 */
public class GestureListener extends GestureDetector.SimpleOnGestureListener {

    private Context content;
    private RecyclerView recyclerView;
    public GestureListener(Context content,RecyclerView recyclerView) {
        this.content = content;
        this.recyclerView = recyclerView;
    }



    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();
        View child = recyclerView.findChildViewUnder(x, y);
        int position = recyclerView.getChildLayoutPosition(child);
        RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(child);
        Toast.makeText(content, "click:"+position, Toast.LENGTH_SHORT).show();
        return super.onSingleTapUp(e);
    }
}
