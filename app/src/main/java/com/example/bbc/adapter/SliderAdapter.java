package com.example.bbc.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bbc.R;
import com.example.bbc.model.SliderModel;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderModel> sliderItem;
    private ViewPager2 viewPager2;

    public SliderAdapter(List<SliderModel> sliderItem, ViewPager2 viewPager2) {
        this.sliderItem = sliderItem;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setImage(sliderItem.get(position));
        if (position == sliderItem.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItem.size();
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {

        ImageView sliderImage;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            sliderImage = itemView.findViewById(R.id.iv_slider);
        }

        void setImage(SliderModel sliderModel) {
            sliderImage.setImageResource(sliderModel.getImage());
        }

    }


    private Runnable runnable = new Runnable() {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void run() {
            sliderItem.addAll(sliderItem);
            notifyDataSetChanged();
        }
    };


}
