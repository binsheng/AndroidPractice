package com.dev.bins.taiji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by bin on 20/11/2016.
 */

public class TaiJi extends View {
    private int width;
    private Paint blackPaint;
    private Paint whitePaint;
    private RectF rectF;
    private int degress = 0;

    public TaiJi(Context context) {
        this(context, null);
    }

    public TaiJi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.GRAY);
        blackPaint = new Paint();
        blackPaint.setStyle(Paint.Style.FILL);
        blackPaint.setColor(Color.BLACK);

        whitePaint = new Paint();
        whitePaint.setStyle(Paint.Style.FILL);
        whitePaint.setColor(Color.WHITE);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = 0;
        int h = 0;
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if (wMode == MeasureSpec.AT_MOST) {
            w = 200;
        } else if (wMode == MeasureSpec.EXACTLY) {
            w = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (hMode == MeasureSpec.AT_MOST) {
            h = 200;
        } else if (hMode == MeasureSpec.EXACTLY) {
            h = MeasureSpec.getSize(heightMeasureSpec);
        }
        width = Math.min(w, h);
        setMeasuredDimension(width, width);
        rectF = new RectF(0, 0, width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(degress += 10,width/2,width/2);
        canvas.drawArc(rectF, 0, 180, false, whitePaint);
        canvas.drawArc(rectF, 180, 180, false, blackPaint);

        canvas.drawCircle(width / 4, width / 2, width / 4, whitePaint);
        canvas.drawCircle(width / 4 * 3, width / 2, width / 4, blackPaint);

        canvas.drawCircle(width / 4, width / 2, width / 4 / 4, blackPaint);
        canvas.drawCircle(width / 4 * 3, width / 2, width / 4 / 4, whitePaint);

        postInvalidateDelayed(100);
    }
}
