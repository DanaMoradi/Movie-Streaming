package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemCastBinding;
import com.example.bbc.model.CastModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.castViewHolder> {

    private List<CastModel> list;
    ItemCastBinding binding;

    public CastAdapter(List<CastModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public castViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new castViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull castViewHolder holder, int position) {
        holder.onBindCast(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class castViewHolder extends RecyclerView.ViewHolder {
        ItemCastBinding view;

        public castViewHolder(@NonNull ItemCastBinding v) {
            super(v.getRoot());
            this.view = v;
        }

        public void onBindCast(CastModel item) {
            view.tvDetailMovieCastName.setText(item.getName());
            Picasso.get().load(item.getImg()).into(view.ivDetailMovieCast);
        }

    }
}
