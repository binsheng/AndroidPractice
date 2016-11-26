package com.dev.bins.hexagonlayoutmanager;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by bin on 26/11/2016.
 */

public class HexagonAdapter extends RecyclerView.Adapter<HexagonAdapter.Holder> {

    List<Bitmap> data;

    public HexagonAdapter(List<Bitmap> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.imageView.setImageDrawable(new HexagonDrawable(data.get(position)));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
