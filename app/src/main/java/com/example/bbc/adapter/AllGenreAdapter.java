package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemWatchAllGenreBinding;
import com.example.bbc.model.GenreModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllGenreAdapter extends RecyclerView.Adapter<AllGenreAdapter.AllGenreViewHolder> {


    ItemWatchAllGenreBinding binding;
    List<GenreModel> list;

    public AllGenreAdapter(List<GenreModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AllGenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemWatchAllGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AllGenreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllGenreViewHolder holder, int position) {
        holder.onBindHolder(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class AllGenreViewHolder extends RecyclerView.ViewHolder {

        ItemWatchAllGenreBinding view;

        public AllGenreViewHolder(@NonNull ItemWatchAllGenreBinding v) {
            super(v.getRoot());
            view = v;
        }


        public void onBindHolder(GenreModel item) {
            Picasso.get().load(item.getImg()).into(view.ivWatchAllGenre);
            view.tvWatchAllGenreName.setText(item.getName());
        }
    }
}
