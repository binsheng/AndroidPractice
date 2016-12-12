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
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import static android.R.attr.offset;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by bin on 12/12/2016.
 */

public class WaveView extends View {
    private Paint mPaint;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Canvas mCanvas;
    private Bitmap mBitmapDst;
    private Bitmap mBitmap;
    private Path mPath;
    private int height;
    private int width;
    private int color = Color.YELLOW;
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
        mPath = new Path();
        mPaint.setColor(color);
        mBitmapDst = BitmapFactory.decodeResource(getResources(), R.drawable.fire);

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
        valueAnimator = ValueAnimator.ofInt(0, 5);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                offset = (int) valueAnimator.getAnimatedValue();
                System.out.println(offset);
                invalidate();
            }
        });
        valueAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBitmap.eraseColor(Color.parseColor("#00000000"));
        mPath.reset();
        mPath.moveTo(0, height / 2);
        int waveWith = width / 10;
        for (int i = 0; i < 10; i++) {
            int y = i % 2 == 0 ? height / 2 + 30 : height / 2 - 30;
            mPath.quadTo(waveWith * i + waveWith / 2 - waveWith * offset, y, waveWith * (i + 1) - waveWith * offset, height / 2);
        }
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
        mCanvas.drawBitmap(mBitmapDst, 0, 0, mPaint);
        mPaint.setXfermode(xfermode);
        mCanvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);
        canvas.drawBitmap(mBitmap, 0, 0, null);

    }
}
