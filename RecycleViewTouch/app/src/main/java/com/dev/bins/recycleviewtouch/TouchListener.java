package com.dev.bins.recycleviewtouch;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by bin on 7/27/16.
 */
public class TouchListener implements RecyclerView.OnItemTouchListener {
    GestureDetectorCompat gestureDetectorCompat;

    public TouchListener(Context context,RecyclerView recyclerView) {
        gestureDetectorCompat = new GestureDetectorCompat(context,new GestureListener(context,recyclerView));
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    本应用直接从用户手机出发，通过终端获取的网络信息来反映当前网络优良状况，为网优提供及时有效的现场反馈，有助于问题的顺利解决。应用内的各模块均在最大程度上取代了传统pc端的相应工作，例如：查看场强和干扰系数、ftp测试、ping测试、拉网路测等，同时也涵盖了部分网优/督导上站测量天馈所需的方位角、机械俯仰角、经纬度、挂高、天馈周围环境情况等功能，对于通信行业人员不失为一个好帮手。

}
