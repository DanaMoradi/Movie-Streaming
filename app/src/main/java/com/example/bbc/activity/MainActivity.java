package com.example.bbc.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bbc.databinding.ActivityMainBinding;
import com.example.bbc.db.volley.GenreVolley;
import com.example.bbc.db.volley.SeriesVolley;
import com.example.bbc.db.volley.SliderVolley;
import com.example.bbc.db.volley.TopMovieVolley;
import com.example.bbc.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //Slider
    ViewPager slider;
    List<SliderModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        setSlider();
        setSliderTimer();
        setGenre();
        setTopMovie();
        setSeries();


    }

    private void setSlider() {
        slider = binding.vpMainSlider;
        SliderVolley sliderVolley = new SliderVolley(slider, list);
        sliderVolley.setRequestQueue();
        binding.tabLayout.setupWithViewPager(slider, true);

    }

    private void setGenre() {
        RecyclerView genre = binding.rvMainGenre;
        genre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        GenreVolley genreVolley = new GenreVolley(binding.rvMainGenre);
        genreVolley.setRequestQueue();
    }

    private void setTopMovie() {
        ViewPager2 viewPager2 = binding.vpMainTopMovie;
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(25));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);


        TopMovieVolley topMovieVolley = new TopMovieVolley(viewPager2);
        topMovieVolley.setRequestQueue();

    }

    private void setSeries() {
        RecyclerView seriesRV = binding.rvMainSeries;
        seriesRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        seriesRV.setClipToPadding(false);
        seriesRV.setHasFixedSize(true);

        SeriesVolley seriesVolley = new SeriesVolley(seriesRV);
        seriesVolley.setRequestQueue();
    }

    //Timer For Slider
    private void setSliderTimer() {
        TimerSlider timerSlider = new TimerSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerSlider, 5000, 3000);
    }

    //Timer for Slider
    public class TimerSlider extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slider.getCurrentItem() < list.size() - 1) {
                        slider.setCurrentItem(slider.getCurrentItem() + 1);
                    } else {
                        slider.setCurrentItem(0);
                    }
                }
            });
        }
    }
}



