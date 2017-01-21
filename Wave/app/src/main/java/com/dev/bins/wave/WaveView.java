package com.dev.bins.wave;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by bin on 12/12/2016.
 */

public class WaveView extends View {
    private Paint mPaint;
    private Paint mBitmapPaint;
    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Canvas mCanvas;
    private Bitmap mBitmapDst;
    private Bitmap mBitmap;
    private Path mPath;
    private int height;
    private int width;
    private int color = Color.RED;
    private ValueAnimator valueAnimator;
    private int offset = 0;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setDither(true);
        mPath = new Path();
        mPaint.setColor(color);
        mPaint.setXfermode(xfermode);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.q1);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBitmapDst.getWidth(), mBitmapDst.getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        valueAnimator = ValueAnimator.ofInt(-width / 2, width / 2);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                offset = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setFlags(LAYER_TYPE_SOFTWARE);
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        mPath.reset();
        int waveWith = width / 2;
        mPath.moveTo(0, height / 2);
        for (int i = 0; i < 2; i++) {
            int y = i % 2 == 0 ? height / 2 + waveWith / 5 : height / 2 - waveWith / 5;
            int x = (waveWith * (i + 1) - waveWith * i) / 2;
            mPath.quadTo(waveWith * i + offset + x, y, waveWith * (i + 1) + offset, height / 2);
        }
        mPath.lineTo(width, waveWith);
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.lineTo(0, waveWith);
        mPath.close();
        mCanvas.drawBitmap(mBitmapDst, 0, 0, mBitmapPaint);
        mCanvas.drawPath(mPath, mPaint);
        canvas.drawBitmap(mBitmap, 0, 0, null);

    }
}
