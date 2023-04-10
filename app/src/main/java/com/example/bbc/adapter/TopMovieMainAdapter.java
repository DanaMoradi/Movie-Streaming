package com.example.bbc.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.activity.DetailPageActivity;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityDetailPageBinding;
import com.example.bbc.databinding.ItemTopMovieBinding;
import com.example.bbc.model.TopMovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopMovieMainAdapter extends RecyclerView.Adapter<TopMovieMainAdapter.TopMovieViewHolder> {


    List<TopMovieModel> list;
    ItemTopMovieBinding binding;

    public TopMovieMainAdapter(List<TopMovieModel> list) {
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

            ImageView img = view.ivMainTopMain;
            view.tvMainTopMovieName.setText(item.getName());
            view.tvTopMovieDescription.setText(item.getDescription());
            Picasso.get().load(item.getImg()).into(img);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view.tvTopMovieDescription.getVisibility() == View.VISIBLE) {
                        view.tvTopMovieDescription.setVisibility(View.GONE);
                    } else {
                        view.tvTopMovieDescription.setVisibility(View.VISIBLE);
                    }
                }
            });
            view.btnTopMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getRoot().getContext(), DetailPageActivity.class);
                    intent.putExtra(app.SINGLE_KEY, app.SINGLE_MOVIE);
                    intent.putExtra(app.ID, item.getId());
                    intent.putExtra(app.NAME, item.getName());
                    intent.putExtra(app.IMG, item.getImg());
                    intent.putExtra(app.DESCRIPTION, item.getDescription());
                    intent.putExtra(app.DIRECTOR, item.getDirector());
                    intent.putExtra(app.TIME, item.getTime());
                    intent.putExtra(app.RATE_IMDB, item.getRate_imdb());
                    view.getRoot().getContext().startActivity(intent);
                }
            });
        }
    }

}
