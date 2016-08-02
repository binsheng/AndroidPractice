package com.dev.bins.explodetransition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_activit_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ActivityTransitionActivity.class);
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,v,"").toBundle());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, v, "share");
                ActivityCompat.startActivity(MainActivity.this,intent,options.toBundle());
            }
        });
        findViewById(R.id.btn_content_transition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ContentTransitionActivity.class);
                //注释了的需要API>=21
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,v,"").toBundle());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(v,v.getLeft()+500,v.getTop(),0,0);
                ActivityCompat.startActivity(MainActivity.this,intent,options.toBundle());
            }
        });
    }
}
