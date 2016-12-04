package com.dev.bins.hexagonlayoutmanager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 24/11/2016.
 */

public class HexagonLayoutManager extends RecyclerView.LayoutManager {

    private List<List<Rect>> rects = new ArrayList<>();
    private List<Rect> rectList = new ArrayList<>();
    private int length = 0;


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        rects.clear();
//        rectList.clear();
        int itemCount = state.getItemCount();
        if (itemCount == 0) return;
        detachAndScrapAttachedViews(recycler);//解绑所有view

//        initCenter(recycler);
//        generateRects(itemCount);
//        for (int i = 0; i < itemCount; i++) {
//            View view = recycler.getViewForPosition(i);
//            measureChildWithMargins(view, 0, 0);
//            addView(view);
//            Rect rect = rectList.get(i);
//            layoutDecoratedWithMargins(view, rect.left, rect.top, rect.right, rect.bottom);
//        }
        for (int i = 0; i < itemCount; i++) {
            if (i >= rectList.size()) {//判断对应索引的位置有没有确定，没有确定则进行计算
                generateRectsOfFloor(getFloorByPosition(i), recycler);
            }
//            布局
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            addView(view);
            Rect rect = rectList.get(i);
            layoutDecoratedWithMargins(view, rect.left, rect.top, rect.right, rect.bottom);

        }

    }

    /**
     * 产生对应层的位置
     * @param floor
     * @param recycler
     */
    private void generateRectsOfFloor(int floor, RecyclerView.Recycler recycler) {
        if (floor == 0) {
            initCenter(recycler);
            return;
        }
//        第一层特殊处理
        if (floor == 1) {
            Rect zeroRect = rectList.get(0);
            for (int j = 0; j < 6; j++) {
                int x = (int) (length * Math.cos(j * Math.PI / 3));
                int y = (int) (length * Math.sin(j * Math.PI / 3));
                Rect rect = new Rect(zeroRect);
                rect.offset(x, y);
                rectList.add(rect);
            }
            return;
        }
        int startPosition = getFloorStartPosition(floor - 1);
        int count = getFloorCount(floor - 1);
        for (int i = 0; i < count; i++) {
            Rect rect = rectList.get(startPosition + i);
            if ((i % (floor - 1)) == 0) {
                getListRect(i, floor - 1, rect);
            } else {
                int x = (int) (length * Math.cos((((i / (floor - 1) + 1) % 6) * Math.PI / 3)));
                int y = (int) (length * Math.sin((((i / (floor - 1) + 1) % 6) * Math.PI / 3)));
                rect.offset(x, y);
                rectList.add(rect);
            }
        }

    }


    private void generateRects(int itemCount) {
        int floor = getFloorByPosition(itemCount - 1);
        for (int i = 1; i <= floor; i++) {
            getFloorRectList(i - 1);
        }

    }

    private void getFloorRectList(int floor) {
        List<Rect> floorList = new ArrayList<>();
        if (floor == 0) {
            Rect zeroRect = rects.get(0).get(0);
            for (int j = 0; j < 6; j++) {
//                int height = zeroRect.height();
//                int len = height / 2;
                int x = (int) (length * Math.cos(j * Math.PI / 3));
                int y = (int) (length * Math.sin(j * Math.PI / 3));
                Rect rect = new Rect(zeroRect);
                rect.offset(x, y);
                floorList.add(rect);
                rectList.add(rect);
            }
        } else {
            List<Rect> lastRect = this.rects.get(floor);
            int size = lastRect.size();
            for (int j = 0; j < size; j++) {
                if (j % floor == 0) {
                    floorList.addAll(getListRect(j, floor, lastRect.get(j)));
                } else {
                    Rect rect = new Rect(lastRect.get(j));
                    int x = (int) (length * Math.cos((((j / floor + 1) % 6) * Math.PI / 3)));
                    int y = (int) (length * Math.sin((((j / floor + 1) % 6) * Math.PI / 3)));
                    rect.offset(x, y);
                    floorList.add(rect);
                    rectList.add(rect);
                }
            }
        }
        rects.add(floorList);
    }

    private List<Rect> getListRect(int index, int floor, Rect lastRect) {
        List<Rect> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Rect rect = new Rect(lastRect);
            int x = (int) (length * Math.cos((((index / floor + i) % 6) * Math.PI / 3)));
            int y = (int) (length * Math.sin((((index / floor + i) % 6) * Math.PI / 3)));
            rect.offset(x, y);
            list.add(rect);
            rectList.add(rect);
        }
        return list;
    }


    /**
     * 获得对应层的第一个索引
     * @param floor
     * @return
     */
    public int getFloorStartPosition(int floor) {
        int pos = 0;
        for (int i = 0; i < floor; i++) {
            pos += i * 6;
        }

        return pos + 1;
    }

    /**
     * 根据索引得到处于第几层
     * @param  index 索引
     * @return
     */
    public int getFloorByPosition(int index) {
        if (index == 0) return 0;
        int floor = 0;
        index -= 1;
        do {
            floor++;
            index -= getFloorCount(floor);

        } while (index >= 0);

        return floor;
    }

    /**
     * 对应层六边形的个数
     * @param floor
     * @return
     */
    public int getFloorCount(int floor) {
        if (floor == 0) return 1;
        return floor * 6;
    }

    /**
     * 最中间的，索引为0
     * @param recycler
     */
    private void initCenter(RecyclerView.Recycler recycler) {
        View view = recycler.getViewForPosition(0);
        //addView(view);
        measureChildWithMargins(view, 0, 0);
        int w = view.getMeasuredWidth();
        int h = view.getMeasuredHeight();
        int width = getWidth();
        int height = getHeight();
        length = (int) (Math.sqrt(3) * w / 2);
        Rect rect = new Rect(width / 2 - w / 2, height / 2 - h / 2, width / 2 + h / 2, height / 2 + h / 2);
        layoutDecoratedWithMargins(view, rect.left, rect.top, rect.right, rect.bottom);
        List<Rect> first = new ArrayList<>();
        first.add(rect);
        rects.add(first);
        rectList.add(rect);
    }
}



