package com.dev.bins.calendar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev.bins.calendar.R;

import java.util.Calendar;

/**
 * Created by bin on 10/10/2017.
 */

public class RecycleViewCalendar extends LinearLayout{


    private Calendar mCalendar;

    private RecyclerView mRecyclerView;

    public RecycleViewCalendar(Context context) {
        this(context,null);
    }

    public RecycleViewCalendar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RecycleViewCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.recyclerview_calendar, this);
        mCalendar = Calendar.getInstance();
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context,7));



    }



    class Adapter extends RecyclerView.Adapter<Holder>{

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_text_day, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return calculateDayOfMonth();
        }

        private int calculateDayOfMonth() {
            Calendar temp = (Calendar) mCalendar.clone();
            temp.set(Calendar.DAY_OF_MONTH, 1);
            int preDays = temp.get(Calendar.DAY_OF_WEEK) - 1;
            int dayOfMonth = temp.get(Calendar.DAY_OF_MONTH);
            return preDays + dayOfMonth;
        }
    }

    static class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }


}
