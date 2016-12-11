package com.dev.bins.explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dev.bins.explosion.partcle.FlyParticle;
import com.dev.bins.explosion.partcle.Particle;

/**
 * Created by bin on 11/12/2016.
 */

public class FlyFactory extends Factory {

    int count = 15;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int width = bound.width() / count;
        int height = bound.height() / count;
        Particle[][] particles = new Particle[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                int color = bitmap.getPixel(j * width, i * height);
                int x = bound.left + j * width;
                int y = bound.top + i * height;
                particles[i][j] = createParticle(x, y, color, bound);
            }
        }


        return particles;
    }

    private Particle createParticle(int x, int y, int color, Rect bound) {
        return new FlyParticle(x, y, color, bound);
    }
}
