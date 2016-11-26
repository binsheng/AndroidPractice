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

/**
 * Created by bin on 26/11/2016.
 */

public class HexagonDrawable extends Drawable {


    private Paint mPaint;
    private Path mPath;
    private BitmapShader mBitmapShader;
    public HexagonDrawable(Bitmap bitmap) {
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

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
