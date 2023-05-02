package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bbc.databinding.ItemSliderBinding;
import com.example.bbc.interfaces.BottomSheetClicked;
import com.example.bbc.model.SliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {


    private ItemSliderBinding binding;
    private List<SliderModel> list;
    private BottomSheetClicked bottomSheetClicked;


    public SliderAdapter(List<SliderModel> list, BottomSheetClicked bottomSheetClicked) {
        this.list = list;
        this.bottomSheetClicked = bottomSheetClicked;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        binding = ItemSliderBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        Picasso.get().load(list.get(position).getImg()).into(binding.ivSlider);
        binding.ivSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetClicked.itemClicked(list.get(position).getId());
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
