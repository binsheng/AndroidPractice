package com.dev.bins.recyclerviewitemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bin on 08/01/2017.
 */

public class IndexBar extends View {
    private float indexHeight;
    private Paint mPaint;

    private String[] index = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int width;
    private OnIndexTouchListener indexTouchListener;

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setTextSize(30);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        indexHeight = h * 1f / index.length;
        width = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < index.length; i++) {
            Rect rect = new Rect();
            mPaint.getTextBounds(index[i], 0, index[i].length(), rect);
            canvas.drawText(index[i], width * 1f / 2, i * indexHeight + rect.height() / 2 + indexHeight / 2, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int pos = (int) (y / indexHeight);
                if (indexTouchListener != null) {
                    indexTouchListener.onTouch(index[pos]);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (indexTouchListener != null) {
                    indexTouchListener.up();
                }
                break;
        }


        return true;
    }

    public void setIndexTouchListener(OnIndexTouchListener indexTouchListener) {
        this.indexTouchListener = indexTouchListener;
    }

    interface OnIndexTouchListener {
        void onTouch(String letter);

        void up();
    }
}
