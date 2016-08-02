package com.dev.bins.explodetransition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

public class ActivityTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
//        getWindow().setEnterTransition(new Explode());
        setContentView(R.layout.activity_second);
    }
}
