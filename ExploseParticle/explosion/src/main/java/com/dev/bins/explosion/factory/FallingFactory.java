package com.dev.bins.explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dev.bins.explosion.partcle.FallingParticle;
import com.dev.bins.explosion.partcle.Particle;

import static android.R.attr.width;

/**
 * Created by bin on 11/12/2016.
 */

public class FallingFactory extends Factory {
    private int size = 15;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int wCount = bound.width()/size;
        int hCount = bound.height()/size;
        Particle[][] particles = new Particle[hCount][wCount];
        for (int i = 0; i < hCount; i++) {
            for (int j = 0; j < wCount; j++) {
                int color = bitmap.getPixel(j * size, i * size);
                int x = bound.left + j * size;
                int y = bound.top + i * size;
                particles[i][j] = createParticle(x, y, color,bound);
            }
        }
        return particles;
    }

    private Particle createParticle(int x, int y, int color, Rect bound) {
        return new FallingParticle(x,y,color,bound);
    }
}
