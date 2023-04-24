package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemRelatedBinding;
import com.example.bbc.model.RelatedModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.relatedViewHolder> {


    private final List<RelatedModel> list;
    private ItemRelatedBinding binding;

    public RelatedAdapter(List<RelatedModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public relatedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRelatedBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new relatedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull relatedViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class relatedViewHolder extends RecyclerView.ViewHolder {
        private ItemRelatedBinding view;

        public relatedViewHolder(@NonNull ItemRelatedBinding v) {
            super(v.getRoot());
            this.view = v;
        }

        public void onBind(RelatedModel item) {
            Picasso.get().load(item.getImg()).into(view.ivRelatedMovie);
            view.tvRelatedName.setText(item.getName());
        }
    }
}
