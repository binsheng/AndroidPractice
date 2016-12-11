package com.dev.bins.explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.dev.bins.explosion.partcle.ExplodeParticle;
import com.dev.bins.explosion.partcle.Particle;

/**
 * Created by bin on 11/12/2016.
 */

public class ExplodeFactory extends Factory {

    int count = 15;

    @Override
    public Particle[][] generateParticles(Bitmap bitmap, Rect bound) {
        int width = bound.width() / count;
        int height = bound.height() / count;
        System.out.println(bitmap.getPixel(bitmap.getWidth()/2,bitmap.getHeight()/2));
        Particle[][] particles = new Particle[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                int color = bitmap.getPixel(j * width, i * height);
                int x = bound.left + j * width;
                int y = bound.top + i * height;
                particles[i][j] = createParticle(x, y, color);
            }
        }


        return particles;
    }

    private Particle createParticle(int x, int y, int color) {


        return new ExplodeParticle(x, y, color);
    }
}
