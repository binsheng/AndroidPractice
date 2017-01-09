package com.dev.bins.stickness;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by bin on 09/01/2017.
 */

public class StickNessView extends View {

    private Paint mPaint;
    private PointF[] mStickCircle;
    private PointF[] mDragCircle;

    private float mRadius = 20f;

    public StickNessView(Context context) {
        this(context, null);
    }

    public StickNessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickNessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStickCircle = new PointF[]{new PointF(w * 0.8f, h * 0.2f), new PointF(w * 0.8f, h * 0.2f + 2 * mRadius)};
        mDragCircle = new PointF[]{new PointF(w * 0.2f, h * 0.2f), new PointF(w * 0.2f, h * 0.2f + 2 * mRadius)};

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mStickCircle[0].x, mStickCircle[0].y + mRadius, mRadius, mPaint);
        canvas.drawCircle(mDragCircle[0].x, mDragCircle[0].y + mRadius, mRadius, mPaint);

        Path path = new Path();
        float cx1 = (mDragCircle[0].x + mStickCircle[0].x) * 0.618f;
        float cy1 = mDragCircle[0].y + mRadius;
        float cx2 = (mDragCircle[1].x + mStickCircle[1].x) * 0.618f;
        float cy2 = mDragCircle[1].y - mRadius;

        PointF controlPoint1 = new PointF(cx1, cy1);
        path.moveTo(mDragCircle[0].x, mDragCircle[0].y);
        path.quadTo(controlPoint1.x, controlPoint1.y, mStickCircle[0].x, mStickCircle[0].y);
        path.lineTo(mStickCircle[1].x, mStickCircle[1].y);
        path.quadTo(cx2, cy2, mDragCircle[1].x, mDragCircle[1].y);
        path.close();


        canvas.drawPath(path, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
