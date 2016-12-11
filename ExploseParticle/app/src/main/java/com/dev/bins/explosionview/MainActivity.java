package com.dev.bins.explosionview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dev.bins.explosion.ExplosionView;
import com.dev.bins.explosion.factory.FlyFactory;
import com.dev.bins.explosion.factory.FallingFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ExplosionView explosionView = new ExplosionView(this);
        findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explosionView.explode(view,new FlyFactory());
            }
        });
        findViewById(R.id.iv_sec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explosionView.explode(view,new FallingFactory());
            }
        });



    }

}
