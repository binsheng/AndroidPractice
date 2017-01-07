package com.dev.bins.slidingmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static com.dev.bins.slidingmenu.R.id.add;
import static com.dev.bins.slidingmenu.R.id.rv_menu;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvMain;
    private RecyclerView mRvMenu;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvMenu = (RecyclerView) findViewById(rv_menu);
        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
        mAdapter = new Adapter();
        mRvMain.setAdapter(mAdapter);
        mRvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvMenu.setAdapter(mAdapter);
        mRvMenu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
