package com.dev.bins.calendar.view;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.bins.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.dev.bins.calendar.view.RecycleViewCalendar.STATE_COLLAPSE;

/**
 * Created by bin on 25/10/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.Holder> {


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
        if (mCurrentState == STATE_COLLAPSE) {
            holder.textView.setTextColor(Color.BLACK);
            return;
        }
        if (current.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            holder.textView.setTextColor(Color.BLACK);
        } else {
            holder.textView.setTextColor(Color.GRAY);
        }
        Calendar today = Calendar.getInstance();

        if (isToday(calendar, today)) {
            holder.textView.setTextColor(Color.RED);
            mCurrentSelectionPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    private boolean isToday(Calendar calendar, Calendar today) {
        return today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_calendar_day);
        }
    }
}
