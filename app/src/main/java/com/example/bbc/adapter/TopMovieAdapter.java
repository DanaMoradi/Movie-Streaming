package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemTopMovieBinding;
import com.example.bbc.model.TopMovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopMovieAdapter extends RecyclerView.Adapter<TopMovieAdapter.TopMovieViewHolder> {


    List<TopMovieModel> list;
    ItemTopMovieBinding binding;

    public TopMovieAdapter(List<TopMovieModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TopMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding = ItemTopMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TopMovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMovieViewHolder holder, int position) {
        holder.onBindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TopMovieViewHolder extends RecyclerView.ViewHolder {

        ItemTopMovieBinding view;

        public TopMovieViewHolder(@NonNull ItemTopMovieBinding v) {
            super(v.getRoot());
            view = v;
        }

        private void onBindView(TopMovieModel item) {
            Picasso.get().load(item.getImg()).into(view.ivMainTopMain);
            view.tvMainTopMovieName.setText(item.getName());
            String movieName = view.tvMainTopMovieName.getText().toString();
            view.btnTopMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), movieName, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
