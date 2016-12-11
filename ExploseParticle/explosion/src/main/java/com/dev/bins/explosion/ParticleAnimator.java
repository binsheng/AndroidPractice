package com.dev.bins.explosion;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.dev.bins.explosion.factory.Factory;
import com.dev.bins.explosion.partcle.Particle;

/**
 * Created by bin on 11/12/2016.
 */

public class ParticleAnimator extends ValueAnimator {

    private View mContainer;
    private Particle[][] particles;
    private Paint mPaint;

    public ParticleAnimator(View view,Bitmap bitmap, Rect rect, Factory factory) {
        particles = factory.generateParticles(bitmap, rect);
        mContainer = view;
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        setDuration(1000);
        setFloatValues(0.0f, 1.0f);

    }

    public void draw(Canvas canvas) {
        if (!isStarted()) {
            return;
        }

        for (Particle[] particle : particles) {
            for (Particle particle1 : particle) {
                particle1.advance((Float) getAnimatedValue(), canvas, mPaint);
            }
        }
        mContainer.invalidate();
    }


    @Override
    public void start() {
        super.start();
        mContainer.invalidate();
    }
}
