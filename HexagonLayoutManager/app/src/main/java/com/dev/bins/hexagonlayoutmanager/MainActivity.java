package com.dev.bins.hexagonlayoutmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HexagonAdapter mAdapter;
    private List<Bitmap> bitmapList = new ArrayList<>();
    private Bitmap bitmap;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mAdapter = new HexagonAdapter(bitmapList);
        mRecyclerView.setLayoutManager(new HexagonLayoutManager());
        mRecyclerView.setAdapter(mAdapter);
        random = new Random();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img);
        for (int i = 0; i < 7; i++) {
            bitmapList.add(bitmap);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void add(View view) {
        bitmapList.add(bitmap);
        mAdapter.notifyItemInserted(random.nextInt(bitmapList.size() - 1));
    }

    public void del(View view) {
        int i = random.nextInt(bitmapList.size() - 1);
        bitmapList.remove(i);
        mAdapter.notifyItemRemoved(i);
    }


}
