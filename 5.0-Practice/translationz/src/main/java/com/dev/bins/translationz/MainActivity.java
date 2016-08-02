package com.dev.bins.translationz;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_translation_z).setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        ObjectAnimator trans = ObjectAnimator.ofFloat(v, "translationZ", 30);
        trans.setDuration(1000);
        trans.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator.ofFloat(v, "translationZ", 0).setDuration(500).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        trans.start();
    }
}
