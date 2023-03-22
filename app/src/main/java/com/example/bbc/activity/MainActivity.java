package com.example.bbc.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.bbc.databinding.ActivityMainBinding;
import com.example.bbc.db.volley.SliderVolley;
import com.example.bbc.model.SliderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RequestQueue requestQueue;

    //Slider
    ViewPager slider;
    List<SliderModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setRequestQueue();


        setSlider();
        setSliderTimer();

    }

    private void setRequestQueue() {
        requestQueue = Volley.newRequestQueue(this);
    }

    private void setSlider() {
        slider = binding.vpMainSlider;
        SliderVolley sliderVolley = new SliderVolley(this, requestQueue, slider, list);
        sliderVolley.setRequestQueue();
        binding.tabLayout.setupWithViewPager(slider, true);
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



