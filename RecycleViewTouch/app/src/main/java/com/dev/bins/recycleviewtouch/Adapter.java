package com.dev.bins.recycleviewtouch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 7/27/16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<String> mDatas;

    public Adapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
            holder.textView.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView;
        public Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
