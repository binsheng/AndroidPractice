package com.dev.bins.calendar.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.bins.calendar.R;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by bin on 10/10/2017.
 */

public class RecycleViewCalendar extends LinearLayout {


    private Calendar mCalendar;
    private int mCurrentSelectionPosition = -1;
    private RecyclerView mRecyclerView;

    private GestureDetectorCompat mGestureDetectorCompat;
    private Adapter mAdapter;

    public RecycleViewCalendar(Context context) {
        this(context, null);
    }

    public RecycleViewCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecycleViewCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.recyclerview_calendar, this);
        mCalendar = Calendar.getInstance();
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 7));
        mAdapter = new Adapter();
        mRecyclerView.setAdapter(mAdapter);
        mGestureDetectorCompat = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                if (e2.getX() - e1.getX() > 10) {
                    mCalendar.add(Calendar.MONTH, -1);
                } else {
                    mCalendar.add(Calendar.MONTH, 1);
                }
                mAdapter.notifyDataSetChanged();

                return super.onFling(e1, e2, velocityX, velocityY);
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    public void onScroll(int dy){
        View selctView = mRecyclerView.getChildAt(mCurrentSelectionPosition);
        int top = selctView.getTop();

        if (-getTop()<top){
            offsetTopAndBottom(-dy);
        }else{
            offsetTopAndBottom(-top-getTop());
        }

    }


    public void open(){
        int top = getTop();
        offsetTopAndBottom(-top);
    }


    public int getCurrentPosition(){
        return mCurrentSelectionPosition;
    }



    class Adapter extends RecyclerView.Adapter<Holder> {

        private List<Date> dates = new ArrayList<>();
        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_text_day, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dates.get(position));
            Calendar current = mCalendar;
            holder.textView.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));

            if (current.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
                holder.textView.setTextColor(Color.BLACK);
            } else {
                holder.textView.setTextColor(Color.GRAY);
            }
            Calendar today = Calendar.getInstance();

            if (today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                holder.textView.setTextColor(Color.RED);
                mCurrentSelectionPosition = position;
            }
        }

        @Override
        public int getItemCount() {
            return calculateDayOfMonth();
        }

        private int calculateDayOfMonth() {
            dates.clear();
            Calendar temp = (Calendar) mCalendar.clone();
            temp.set(Calendar.DAY_OF_MONTH, 1);
            int preDays = temp.get(Calendar.DAY_OF_WEEK) - 1;
            int dayInMonth = temp.getActualMaximum(Calendar.DATE);
            int days = preDays + dayInMonth;
            if (days % 7 != 0) {
                int count = days / 7;
                days = (count + 1) * 7;
            }
            temp.add(Calendar.DAY_OF_MONTH, -preDays);
            for (int i = 0; i < days; i++) {
                dates.add(temp.getTime());
                temp.add(Calendar.DAY_OF_MONTH, 1);
            }
            return dates.size();
        }






    }


    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_calendar_day);
        }
    }




}
