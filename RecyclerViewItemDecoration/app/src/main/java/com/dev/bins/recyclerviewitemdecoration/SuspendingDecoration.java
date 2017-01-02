package com.dev.bins.recyclerviewitemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SoundEffectConstants;
import android.view.View;

import java.util.List;

/**
 * Created by bin on 02/01/2017.
 */

public class SuspendingDecoration extends RecyclerView.ItemDecoration {
    private List<CityBean> mDatas;
    private int height = 50;

    public SuspendingDecoration(List<CityBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (pos == 0) {
            outRect.set(0, height, 0, 0);
        } else if (!mDatas.get(pos).getTag().equals(mDatas.get(pos - 1).getTag())) {
            outRect.set(0, height, 0, 0);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setColor(Color.GRAY);
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(20);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childAt = parent.getChildAt(i);
            int pos = ((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition();
            if (i == 0) {
                c.drawRect(0, childAt.getTop() - 50, parent.getWidth(), childAt.getTop(), paint);
                c.drawText(mDatas.get(pos).getTag(), 10, childAt.getTop() - 10, textPaint);
            } else if (!mDatas.get(pos).getTag().equals(mDatas.get(pos - 1).getTag())) {
                c.drawRect(0, childAt.getTop() - 50, parent.getWidth(), childAt.getTop(), paint);
                c.drawText(mDatas.get(pos).getTag(), 10, childAt.getTop() - 10, textPaint);
            }
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setColor(Color.GRAY);
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(20);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);

        int pos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        View view = parent.findViewHolderForLayoutPosition(pos).itemView;
        c.save();
        if (!mDatas.get(pos).getTag().equals(mDatas.get(pos+1).getTag())){
            if (view.getBottom() < height) {//view.getTop() + view.getHeight()和getbottom的效果是一样的
                System.out.println(view.getTop());
                System.out.println(view.getHeight());
                c.translate(0, view.getTop() + view.getHeight() - height);
            }
        }
        c.drawRect(0, parent.getTop(), parent.getWidth(), parent.getTop()+height, paint);
        c.drawText(mDatas.get(pos).getTag(), 10, 35, textPaint);
        c.restore();

    }
}
