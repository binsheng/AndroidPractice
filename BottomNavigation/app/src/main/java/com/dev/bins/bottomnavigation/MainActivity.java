package com.dev.bins.bottomnavigation;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.phone:
                        Toast.makeText(MainActivity.this, "phone", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.sms:
                        Toast.makeText(MainActivity.this, "sms", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        Toast.makeText(MainActivity.this, "location", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
