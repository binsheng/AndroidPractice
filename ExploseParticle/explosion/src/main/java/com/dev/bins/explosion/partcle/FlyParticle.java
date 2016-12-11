package com.dev.bins.explosion.partcle;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by bin on 11/12/2016.
 */

public class FlyParticle extends Particle {
    private Random random = new Random();
    private Rect bounds;
    public FlyParticle(float cx, float cy, int color, Rect bounds) {
        super(cx, cy, color);
        this.bounds = bounds;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(cx, cy, 8, paint);
    }

    @Override
    public void calculate(float factor) {
        cx = cx + factor * random.nextInt(bounds.width());
        cy = cy - factor * random.nextInt(bounds.height()/2);
    }
}
