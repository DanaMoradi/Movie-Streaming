package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemMainGenreBinding;
import com.example.bbc.model.GenreModel;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {

    private ItemMainGenreBinding binding;
    List<GenreModel> list;


    public GenreAdapter(List<GenreModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMainGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {

        ItemMainGenreBinding view;


        public GenreViewHolder(@NonNull ItemMainGenreBinding v) {
            super(v.getRoot());
            view = v;
        }

        void bindData(GenreModel item) {
            view.tvGenreTittle.setText(item.getName());
        }

    }

}
