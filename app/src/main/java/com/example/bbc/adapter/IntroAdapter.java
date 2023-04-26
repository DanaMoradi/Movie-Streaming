package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bbc.databinding.ItemSplashScreenBinding;
import com.example.bbc.model.IntroModel;

import java.util.List;

public class IntroAdapter extends PagerAdapter {

    List<IntroModel> list;
    private ItemSplashScreenBinding binding;

    public IntroAdapter(List<IntroModel> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        binding = ItemSplashScreenBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();

        binding.lottieItemSplash.setAnimation(list.get(position).getImg());
        binding.tvItemSplashDescription.setText(list.get(position).getDescription());

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
