package com.dev.bins.stickness;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;

import static android.R.attr.path;

/**
 * Created by bin on 09/01/2017.
 */

public class StickNessView extends View {

    private Paint mPaint;
    private PointF[] mStickCircle;
    private PointF[] mDragCircle;
    private PointF mStickCenter;
    private PointF mDragCenter;
    private float mRadius = 20f;
    private boolean isOut = false;
    private ValueAnimator mResetAnim;

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
        mStickCenter = new PointF(w * 0.8f, h * 0.2f + mRadius);
        mDragCenter = new PointF(w * 0.2f, h * 0.2f + mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDragCircle = calculate(mDragCenter, mRadius, calculateK(mDragCenter, mStickCenter));
        mStickCircle = calculate(mStickCenter, mRadius, calculateK(mDragCenter, mStickCenter));
        canvas.drawCircle(mDragCenter.x, mDragCenter.y, mRadius, mPaint);
        if (!isOut) {
            canvas.drawCircle(mStickCenter.x, mStickCenter.y, mRadius, mPaint);
            Path path = new Path();
            float cx = (mDragCenter.x - mStickCenter.x) * 0.618f + mStickCenter.x;
            float cy = (mDragCenter.y - mStickCenter.y) * 0.618f + mStickCenter.y;

            PointF controlPoint1 = new PointF(cx, cy);
            path.moveTo(mDragCircle[0].x, mDragCircle[0].y);
            path.quadTo(controlPoint1.x, controlPoint1.y, mStickCircle[0].x, mStickCircle[0].y);
            path.lineTo(mStickCircle[1].x, mStickCircle[1].y);
            path.quadTo(controlPoint1.x, controlPoint1.y, mDragCircle[1].x, mDragCircle[1].y);
            path.close();
            canvas.drawPath(path, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isOut = false;
                mDragCenter.set(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mDragCenter.set(event.getX(), event.getY());


                if (calulateDistance(mDragCenter, mStickCenter) > 100) {
                    isOut = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                mResetAnim = ValueAnimator.ofInt(1);
                mResetAnim.setDuration(1000);
                mResetAnim.setInterpolator(new OvershootInterpolator());
                final PointF startPoint = new PointF(mDragCenter.x, mDragCenter.y);
                mResetAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = valueAnimator.getAnimatedFraction();
                        float cx = startPoint.x - (startPoint.x - mStickCenter.x) * fraction;
                        float cy = startPoint.y - (startPoint.y - mStickCenter.y) * fraction;
                        mDragCenter.set(cx, cy);
                        invalidate();
                    }
                });
                mResetAnim.start();


                break;
        }
        invalidate();
        return true;
    }

    public PointF[] calculate(PointF center, float radius, double k) {
        PointF[] pointFs = new PointF[2];
        double atan = Math.atan(k);
        float x = (float) (Math.sin(atan) * radius);
        float y = (float) (Math.cos(atan) * radius);
        pointFs[0] = new PointF(center.x + x, center.y - y);
        pointFs[1] = new PointF(center.x - x, center.y + y);
        return pointFs;
    }

    public double calculateK(PointF p1, PointF p2) {
        return (p1.y - p2.y) / (p1.x - p2.x);
    }

    public float calulateDistance(PointF p1, PointF p2) {
        return (float) Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }


}
