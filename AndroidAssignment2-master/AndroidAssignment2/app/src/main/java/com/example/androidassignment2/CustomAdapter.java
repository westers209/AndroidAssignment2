package com.example.androidassignment2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    PojoMusic dataSet;

    public CustomAdapter(PojoMusic body) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder customViewHolder, int i) {
        Picasso.get().load(dataSet.data.get(i).collectionName).into(customViewHolder.collection);
        customViewHolder.track.setText(dataSet.data.get(i).artworkUrl60);
        customViewHolder.artist.setText(dataSet.data.get(i).artistName);
        customViewHolder.price.setText(dataSet.data.get(i).trackPrice);
    }

    @Override
    public int getItemCount() {
        return dataSet.data.size() > 0 ? dataSet.data.size() : 0;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView collection;
        TextView track;
        TextView artist;
        TextView price;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            collection = itemView.findViewById(R.id.iv_cover);
            track = itemView.findViewById(R.id.tv_song);
            artist = itemView.findViewById(R.id.tv_artist);
            price = itemView.findViewById(R.id.tv_cost);
        }
    }
}
