package com.dev.bins.explosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.dev.bins.explosion.factory.ExplodeFactory;
import com.dev.bins.explosion.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bin on 11/12/2016.
 */

public class ExplosionView extends View {
    private List<ParticleAnimator> list = new ArrayList<>();

    public ExplosionView(Context context) {
        this(context, null);
    }

    public ExplosionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExplosionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void explode(final View view, final Factory factory) {
        final Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        int top = ((ViewGroup) getParent()).getTop();
        Rect frame = new Rect();
        ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusHeight = frame.top;
        rect.offset(0, -statusHeight - top);
        if (rect.width() == 0 || rect.height() == 0) return;

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            Random random = new Random(System.currentTimeMillis());

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setTranslationX((random.nextFloat() - 0.5f) * view.getWidth() * 0.05f);
                view.setTranslationY((random.nextFloat() - 0.5f) * view.getHeight() * 0.05f);
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                explode(view, rect,factory);
            }
        });
        valueAnimator.start();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (ParticleAnimator animator : list) {
            animator.draw(canvas);
        }
    }

    private void explode(final View view, Rect rect, Factory factory) {
        final ParticleAnimator animator = new ParticleAnimator(this, Utils.createBitmap(view), rect, factory);
        list.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.animate().scaleX(0).scaleY(0).alpha(0).setDuration(200).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.animate().scaleX(1).scaleY(1).alpha(1).setDuration(200).start();
                list.remove(animator);
            }
        });
        animator.start();
    }


    public void attach2Activity(Activity activity) {
        ViewGroup root = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        root.addView(this, lp);
    }

}
