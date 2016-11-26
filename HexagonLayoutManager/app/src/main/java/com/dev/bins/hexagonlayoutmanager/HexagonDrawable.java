package com.dev.bins.hexagonlayoutmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import static android.R.attr.width;

/**
 * Created by bin on 26/11/2016.
 */

public class HexagonDrawable extends Drawable {


    private Paint mPaint;
    private Path mPath;
    private BitmapShader mBitmapShader;
    private Bitmap mBitmap;

    public HexagonDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        if (bitmap != null)
            mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(mBitmapShader);
        mPath = new Path();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        int w = right - left;
        int h = bottom - top;
        int padding = (h - w) / 2;
        int edgeLength = h / 2;
        int width = (int) ((edgeLength * Math.sin(Math.PI / 3)) * 2);
        mPath.moveTo(padding + width / 2, 0);
        mPath.lineTo(padding, (h - edgeLength) / 2);
        mPath.lineTo(padding, (h - edgeLength) / 2 + edgeLength);
        mPath.lineTo(padding + width / 2, h);
        mPath.lineTo(padding + width, (h - edgeLength) / 2 + edgeLength);
        mPath.lineTo(padding + width, (h - edgeLength) / 2);
        mPath.close();
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }


    @Override
    public int getIntrinsicWidth() {
        if (mBitmap != null) return mBitmap.getWidth();
        return super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        if (mBitmap != null) return mBitmap.getHeight();
        return super.getIntrinsicHeight();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
