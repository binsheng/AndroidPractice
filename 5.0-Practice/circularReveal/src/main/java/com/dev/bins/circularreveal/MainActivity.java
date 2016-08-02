package com.dev.bins.circularreveal;

import android.animation.Animator;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        findViewById(R.id.circular_eval).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x  = (v.getLeft()+v.getRight())/2;
                int y = (v.getTop()+v.getBottom())/2-v.getHeight();
                float endRadius = (float) Math.hypot(x,y);
                Animator anim = ViewAnimationUtils.createCircularReveal(iv, x, y, v.getWidth(), endRadius);
                anim.setDuration(2000);
                anim.start();

            }
        });
    }
}
