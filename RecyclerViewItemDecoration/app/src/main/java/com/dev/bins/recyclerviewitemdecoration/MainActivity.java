package com.dev.bins.recyclerviewitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {


    private List<CityBean> mDatas = new ArrayList<>();
    private RecyclerView mRv;
    private Adapter mAdapter;
    private SuspendingDecoration mDecoration;
    private TextView tv;
    private IndexBar mIndexBar;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.index_tv);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mIndexBar = (IndexBar) findViewById(R.id.indexbar);
        initData();
        mAdapter = new Adapter(mDatas);
        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.setAdapter(mAdapter);
        mDecoration = new SuspendingDecoration(mDatas);
        mRv.addItemDecoration(mDecoration);
        mIndexBar.setIndexTouchListener(new IndexBar.OnIndexTouchListener() {
            @Override
            public void onTouch(String letter) {
                tv.setVisibility(View.VISIBLE);
                tv.setText(letter);
                for (int i = 0; i < mDatas.size(); i++) {
                    CityBean cityBean = mDatas.get(i);
                    if (cityBean.getTag().equals(letter)) {
                        layoutManager.scrollToPositionWithOffset(i, 0);
                        break;
                    }
                }
            }

            @Override
            public void up() {
                tv.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        String[] array = getResources().getStringArray(R.array.provinces);
        for (String s : array) {
            CityBean cityBean = new CityBean(s);
            mDatas.add(cityBean);
        }

        Collections.sort(mDatas, new Comparator<CityBean>() {
            @Override
            public int compare(CityBean lhs, CityBean rhs) {
                return lhs.getTag().compareTo(rhs.getTag());
            }
        });

    }
}
