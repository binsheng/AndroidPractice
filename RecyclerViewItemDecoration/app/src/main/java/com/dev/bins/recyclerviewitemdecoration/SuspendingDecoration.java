package com.dev.bins.recyclerviewitemdecoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by bin on 02/01/2017.
 */

public class SuspendingDecoration extends RecyclerView.ItemDecoration {
    private List<CityBean> mDatas;

    public SuspendingDecoration(List<CityBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, 20);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);
        paint.setColor(Color.BLACK);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View childAt = parent.getChildAt(i);
            c.drawRect(0, childAt.getBottom(), parent.getWidth(), childAt.getBottom() + 20,paint);
        }

    }
}
