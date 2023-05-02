package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemSeriesBinding;
import com.example.bbc.interfaces.BottomSheetClicked;
import com.example.bbc.model.SeriesModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesMainAdapter extends RecyclerView.Adapter<SeriesMainAdapter.SeriesViewHolder> {


    List<SeriesModel> list;
    ItemSeriesBinding binding;
    private static BottomSheetClicked bottomSheetClicked;

    public SeriesMainAdapter(List<SeriesModel> list, BottomSheetClicked bottomSheetClicked) {
        this.list = list;
        this.bottomSheetClicked = bottomSheetClicked;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemSeriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SeriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        holder.onBindSeries(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SeriesViewHolder extends RecyclerView.ViewHolder {
        ItemSeriesBinding view;

        public SeriesViewHolder(@NonNull ItemSeriesBinding v) {
            super(v.getRoot());
            view = v;
        }

        private void onBindSeries(SeriesModel item) {
            Picasso.get().load(item.getImg()).into(view.ivMainSeries);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetClicked.itemClicked(item.getId());
                }
            });

        }
    }
}
