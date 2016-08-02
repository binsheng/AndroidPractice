package com.dev.bins.fade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Window;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Fade());
        setContentView(R.layout.activity_main2);
    }
}
