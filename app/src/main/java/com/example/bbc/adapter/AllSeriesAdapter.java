package com.example.bbc.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemWatchAllSeriesBinding;
import com.example.bbc.model.SeriesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllSeriesAdapter extends RecyclerView.Adapter<AllSeriesAdapter.AllSeriesViewHolder> {

    ItemWatchAllSeriesBinding binding;
    List<SeriesModel> list;

    public AllSeriesAdapter(List<SeriesModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AllSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWatchAllSeriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AllSeriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllSeriesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBindView(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), list.get(position).getId() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AllSeriesViewHolder extends RecyclerView.ViewHolder {


        ItemWatchAllSeriesBinding view;

        public AllSeriesViewHolder(@NonNull ItemWatchAllSeriesBinding v) {
            super(v.getRoot());
            view = v;
        }


        public void onBindView(SeriesModel item) {
            Picasso.get().load(item.getImg()).into(view.ivWatchAllMovie);
            view.tvWatchAllSeriesName.setText(item.getName());
            view.tvWatchAllSeriesDirector.setText(item.getDirector());
            view.tvWatchAllSeriesTime.setText(item.getSeasonsAndEpisodes());
            view.tvWatchAllSeriesRate.setText(item.getRate_imdb());
        }

    }
}
