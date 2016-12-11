package com.dev.bins.explosion.partcle;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by bin on 11/12/2016.
 */

public abstract class Particle {

    protected float cx;
    protected float cy;
    protected int color;

    public Particle() {
    }

    public Particle(float cx, float cy, int color) {
        this.cx = cx;
        this.cy = cy;
        this.color = color;
    }

    public float getCx() {
        return cx;
    }

    public void setCx(int cx) {
        this.cx = cx;
    }

    public float getCy() {
        return cy;
    }

    public void setCy(int cy) {
        this.cy = cy;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void advance(float factor, Canvas canvas, Paint paint) {
        calculate(factor);
        draw(canvas, paint);
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract void calculate(float factor);
}
