package com.dev.bins.recyclerviewitemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by bin on 02/01/2017.
 */

public class SuspendingDecoration extends RecyclerView.ItemDecoration {
    private List<CityBean> mDatas;
    private int height = 50;
    private Paint mPaint;
    private Paint mTextPaint;

    public SuspendingDecoration(List<CityBean> mDatas) {
        this.mDatas = mDatas;
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GRAY);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setStrokeWidth(20);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(50);
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

        for (int i = 0; i < parent.getChildCount(); i++) {
            View childAt = parent.getChildAt(i);
            int pos = ((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition();
            if (i == 0) {
                c.drawRect(0, childAt.getTop() - 50, parent.getWidth(), childAt.getTop(), mPaint);
                c.drawText(mDatas.get(pos).getTag(), 10, childAt.getTop() - 10, mTextPaint);
            } else if (!mDatas.get(pos).getTag().equals(mDatas.get(pos - 1).getTag())) {
                c.drawRect(0, childAt.getTop() - 50, parent.getWidth(), childAt.getTop(), mPaint);
                c.drawText(mDatas.get(pos).getTag(), 10, childAt.getTop() - 10, mTextPaint);
            }
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);


        int pos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        View view = parent.findViewHolderForLayoutPosition(pos).itemView;
        c.save();
        if (!mDatas.get(pos).getTag().equals(mDatas.get(pos + 1).getTag())) {
            if (view.getBottom() < height) {//view.getTop() + view.getHeight()和getbottom的效果是一样的
                System.out.println(view.getTop());
                System.out.println(view.getHeight());
                c.translate(0, view.getTop() + view.getHeight() - height);
            }
        }
        c.drawRect(0, parent.getTop(), parent.getWidth(), parent.getTop() + height, mPaint);
        c.drawText(mDatas.get(pos).getTag(), 10, 35, mTextPaint);
        c.restore();

    }


}
