package com.dev.bins.android50practice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tower);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch lightMutedSwatch = palette.getVibrantSwatch();
                if (lightMutedSwatch != null) {
                    tv.setTextColor(lightMutedSwatch.getRgb());
                }
            }
        });
    }
}
