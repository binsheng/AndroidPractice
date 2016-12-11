package com.dev.bins.explosion.partcle;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by bin on 11/12/2016.
 */

public class ExplodeParticle extends Particle {
    public ExplodeParticle(int cx, int cy, int color) {
        super(cx, cy, color);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawCircle(cx, cy, 8, paint);
    }

    @Override
    public void calculate(float factor) {
        cx = (int) (cx + factor+5);
        cy = (int) (cy + factor+5);
    }
}
