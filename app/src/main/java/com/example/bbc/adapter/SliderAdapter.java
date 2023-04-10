package com.example.bbc.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bbc.activity.DetailPageActivity;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ItemSliderBinding;
import com.example.bbc.model.SliderModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends PagerAdapter {


    private ItemSliderBinding binding;
    List<SliderModel> list;

    public SliderAdapter(List<SliderModel> list) {
        this.list = list;
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
                Intent intent = new Intent(view.getContext(), DetailPageActivity.class);
                intent.putExtra(app.SINGLE_KEY, app.SINGLE_MOVIE);
                intent.putExtra(app.ID, list.get(position).getId());
                intent.putExtra(app.NAME, list.get(position).getName());
                intent.putExtra(app.IMG, list.get(position).getImg());
                view.getContext().startActivity(intent);
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
