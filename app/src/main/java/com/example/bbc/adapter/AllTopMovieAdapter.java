package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemWatchAllMovieBinding;
import com.example.bbc.model.TopMovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllTopMovieAdapter extends RecyclerView.Adapter<AllTopMovieAdapter.AllTopMovieHolder> {

    ItemWatchAllMovieBinding binding;
    List<TopMovieModel> list;

    public AllTopMovieAdapter(List<TopMovieModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AllTopMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWatchAllMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AllTopMovieHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllTopMovieHolder holder, int position) {
        holder.onBindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AllTopMovieHolder extends RecyclerView.ViewHolder {
        ItemWatchAllMovieBinding view;

        public AllTopMovieHolder(@NonNull ItemWatchAllMovieBinding v) {
            super(v.getRoot());
            view = v;
        }

        public void onBindView(TopMovieModel item) {
            Picasso.get().load(item.getImg()).into(view.ivWatchAllMovie);
            view.tvWatchAllMoviesName.setText(item.getName());
            view.tvWatchAllMoviesDirector.setText(item.getDirector());
            view.tvWatchAllMoviesTime.setText(item.getTime());
            view.tvWatchAllMoviesRate.setText(String.valueOf(item.getRate_imdb()));
        }
    }
}
