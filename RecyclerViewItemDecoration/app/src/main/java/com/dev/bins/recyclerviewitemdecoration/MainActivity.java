package com.dev.bins.recyclerviewitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<CityBean> mDatas = new ArrayList<>();
    private RecyclerView mRv;
    private Adapter mAdapter;
    private SuspendingDecoration mDecoration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.rv);
        initData();
        mAdapter = new Adapter(mDatas);
        mRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        mRv.setAdapter(mAdapter);
        mDecoration = new SuspendingDecoration(mDatas);
        mRv.addItemDecoration(mDecoration);
    }

    private void initData() {
        String[] array = getResources().getStringArray(R.array.provinces);
        for (String s : array) {
            CityBean cityBean = new CityBean(s);
            mDatas.add(cityBean);
        }



    }
}
