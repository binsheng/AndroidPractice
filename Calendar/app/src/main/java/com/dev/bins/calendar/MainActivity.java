package com.dev.bins.calendar;

import android.bluetooth.le.PeriodicAdvertisingParameters;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dev.bins.calendar.view.RecycleViewCalendar;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements RecycleViewCalendar.OnItemClickListener {


    RecyclerView mRecyclerView;
    RecycleViewCalendar mCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mCalendar = (RecycleViewCalendar) findViewById(R.id.calendar);
        mCalendar.setOnItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(new Adapter());
    }

    @Override
    public void onItemClick(int position, Date date) {
        if (null != date){
            Toast.makeText(this, date.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    class Adapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }


    class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
