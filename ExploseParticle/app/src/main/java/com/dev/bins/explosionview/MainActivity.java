package com.dev.bins.explosionview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dev.bins.explosion.ExplosionView;
import com.dev.bins.explosion.Utils;
import com.dev.bins.explosion.factory.ExplodeFactory;

import static android.R.transition.explode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ExplosionView explosionView = new ExplosionView(this);
        explosionView.attach2Activity(this);
        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explosionView.explode(view,new ExplodeFactory());
            }
        });


    }

}
