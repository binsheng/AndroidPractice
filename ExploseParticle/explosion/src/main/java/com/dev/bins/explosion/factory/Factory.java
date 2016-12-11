package com.dev.bins.explosion.factory;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import com.dev.bins.explosion.partcle.Particle;

/**
 * Created by bin on 11/12/2016.
 */

public abstract class Factory {

    abstract public Particle[][] generateParticles(Bitmap bitmap, Rect bound);

}
