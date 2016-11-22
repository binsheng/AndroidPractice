package com.dev.bins.hexagon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bin on 22/11/2016.
 */

public class Hexagon extends View {

    private Path mPath;
    private Paint mPaint;
    private int width;
    private int hexagonEdge;
    private int padding = 10;
    private int hexagonHeight;

    public Hexagon(Context context) {
        this(context, null);
    }

    public Hexagon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Hexagon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = Math.min(w, h) - 2 * padding;
        hexagonEdge = width / 2;
        hexagonHeight = (int) (hexagonEdge * Math.sin(Math.PI / 180 * 60)) * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(hexagonEdge / 2, padding);
        mPath.lineTo(padding, hexagonHeight / 2);
        mPath.lineTo(padding + hexagonEdge / 2, padding + hexagonHeight);
        mPath.lineTo(padding + hexagonEdge + hexagonEdge / 2, padding + hexagonHeight);
        mPath.lineTo(width + padding, padding + hexagonHeight / 2);
        mPath.lineTo(padding + hexagonEdge + hexagonEdge / 2, padding);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
