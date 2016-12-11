package com.dev.bins.explosion.partcle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by bin on 11/12/2016.
 */

public class FallingParticle extends Particle {
    private Rect bound;
    private Random random = new Random();
    private float radius = 8;

    public FallingParticle(float cx, float cy, int color, Rect bound) {
        super(cx, cy, color);
        this.bound = bound;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(cx, cy, 8, paint);
    }

    @Override
    public void calculate(float factor) {
        cx = cx + factor * random.nextInt(bound.width()) * random.nextFloat();
        cy = cy + factor * random.nextInt(bound.height() / 2);
        radius = radius - factor * random.nextInt(2);
    }
}
