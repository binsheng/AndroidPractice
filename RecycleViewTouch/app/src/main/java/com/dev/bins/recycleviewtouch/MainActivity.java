package com.dev.bins.recycleviewtouch;

import android.graphics.Color;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter mAdapter;
    private List<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 20; i++) {
            mDatas.add(String.valueOf(i));
        }
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.addOnItemTouchListener(new TouchListener(this, recyclerView));
        mAdapter = new Adapter(mDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlag, swipeFlag);//int dragFlags拖动 int swipeFlags滑动
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int endPosition = target.getAdapterPosition();
                System.out.println(fromPosition + "******" + endPosition);
                if (fromPosition > endPosition) {
                    for (int i = fromPosition; i < endPosition; i++) {
                        Collections.swap(mDatas, i, i++);
                    }
                } else {
                    for (int i = fromPosition; i > endPosition; i--) {
                        Collections.swap(mDatas, i, i--);
                    }
                }

                mAdapter.notifyItemMoved(fromPosition, endPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mAdapter.notifyItemRemoved(position);
                mDatas.remove(position);
            }


            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {//不加会奔溃
                    viewHolder.itemView.setBackgroundColor(Color.GRAY);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setBackgroundColor(0);
                super.clearView(recyclerView, viewHolder);
            }

            /**默认所有的都可以长按拖拽，如果需要自定义，需返回false，
             * 在手势touchlistener中使用itemTouchHelper.startDrag(ViewHolder)
             * 即可自定义item是否可拖拽
             * @return
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }
}
