package com.example.bbc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.VolleyError;
import com.example.bbc.Fragments.BottomSheetFragment;
import com.example.bbc.adapter.GenreAdapter;
import com.example.bbc.adapter.SeriesMainAdapter;
import com.example.bbc.adapter.SliderAdapter;
import com.example.bbc.adapter.TopMovieMainAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityMainBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.BottomSheetClicked;
import com.example.bbc.interfaces.GenreInterfaceCallBack;
import com.example.bbc.interfaces.SeriesInterfaceCallBack;
import com.example.bbc.interfaces.SliderApiInterfaceCallBack;
import com.example.bbc.interfaces.TopMovieInterfaceCallBack;
import com.example.bbc.model.GenreModel;
import com.example.bbc.model.SeriesModel;
import com.example.bbc.model.SliderModel;
import com.example.bbc.model.TopMovieModel;
import com.itsronald.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    //Slider
    ViewPager sliderVp;
    List<SliderModel> list = new ArrayList<>();
    private TextView movieName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


//        transparentStatus();

        initView();

        splashScreen();
        setSlider();
        setSliderTimer();
        setGenre();
        setTopMovie();
        setSeries();
        setWatchAll();


    }

    private void transparentStatus() {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    private void initView() {

    }

    private void splashScreen() {
        ConstraintLayout container = binding.clMainContainer;
        LottieAnimationView intro = binding.lottie;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                container.setVisibility(View.VISIBLE);
                intro.setVisibility(View.GONE);
            }
        }, 5000);
    }

    private void setSlider() {
        sliderVp = binding.vpMainSlider;
        movieName = binding.tvAppBarName;

        final ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
        layoutParams.width = ViewPager.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewPager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        final ViewPagerIndicator viewPagerIndicator = new ViewPagerIndicator(this);
        sliderVp.addView(viewPagerIndicator, layoutParams);



        ApiService apiService = new ApiService(this, app.TAG);
        apiService.getSlider(new SliderApiInterfaceCallBack() {
            @Override
            public void onSuccess(List<SliderModel> list) {
                SliderAdapter sliderAdapter = new SliderAdapter(list, new BottomSheetClicked() {
                    @Override
                    public void itemClicked(Long id) {
                        itemBottomSheetClicked(id);
                    }
                });

                sliderVp.setAdapter(sliderAdapter);
                MainActivity.this.list = list;
                movieName.setText(list.get(0).getName());
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("ERROR : ", error.getMessage());
            }
        });
        sliderVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                movieName.setText(list.get(position).getName());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setGenre() {

        ApiService apiService = new ApiService(this, app.TAG);
        apiService.getGenre(new GenreInterfaceCallBack() {
            @Override
            public void onSuccess(List<GenreModel> list) {
                RecyclerView genreRv = binding.rvMainGenre;
                genreRv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true));
                GenreAdapter genreMainAdapter = new GenreAdapter(list);
                genreRv.setAdapter(genreMainAdapter);
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(app.TAG, error.getMessage());
            }
        });


    }

    private void setTopMovie() {
        ViewPager2 topMovieVp = binding.vpMainTopMovie;
        TopMovieMainAdapter adapter;
        topMovieVp.setOffscreenPageLimit(3);
        topMovieVp.setClipToPadding(false);
        topMovieVp.setClipChildren(false);
        topMovieVp.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);


        ApiService apiService = new ApiService(this, app.TAG);
        apiService.getTopMovie(new TopMovieInterfaceCallBack() {
            @Override
            public void onSuccess(List<TopMovieModel> list) {
                TopMovieMainAdapter adapter = new TopMovieMainAdapter(list, new BottomSheetClicked() {
                    @Override
                    public void itemClicked(Long id) {
                        itemBottomSheetClicked(id);
                    }
                });
                topMovieVp.setAdapter(adapter);
                topMovieVp.setCurrentItem(1, false);
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(app.TAG, error.getMessage());
            }
        });

        setViewPageTransformer(topMovieVp);


    }

    private void setSeries() {
        RecyclerView seriesRV = binding.rvMainSeries;

        ApiService apiService = new ApiService(this, app.TAG);
        apiService.getSeries(new SeriesInterfaceCallBack() {
            @Override
            public void onSuccess(List<SeriesModel> list) {
                seriesRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true));
                seriesRV.setClipToPadding(false);
                seriesRV.setHasFixedSize(true);
                SeriesMainAdapter adapter = new SeriesMainAdapter(list, new BottomSheetClicked() {
                    @Override
                    public void itemClicked(Long id) {
                        itemBottomSheetClicked(id);
                    }
                });
                seriesRV.setAdapter(adapter);

            }

            @Override
            public void onError(VolleyError error) {
                Log.e(app.TAG, error.getMessage());
            }
        });
    }

    private void setWatchAll() {


        TextView seeMovie = binding.tvWatchAllMovies;
        TextView seeSeries = binding.tvWatchAllSeries;

        seeMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WatchAllActivity.class);
                intent.putExtra(app.ALL_KEY, app.ALL_MOVIE);
                startActivity(intent);
            }
        });

        seeSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WatchAllActivity.class);
                intent.putExtra(app.ALL_KEY, app.ALL_SERIES);
                startActivity(intent);
            }
        });

    }

    //SetTransformers To ViewPAger
    private void setViewPageTransformer(ViewPager2 viewPager) {

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(25));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);

            }
        });

        viewPager.setPageTransformer(compositePageTransformer);

    }

    //Timer For Slider
    private void setSliderTimer() {
        TimerSlider timerSlider = new TimerSlider();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerSlider, 8000, 6000);
    }

    public void itemBottomSheetClicked(Long id) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("bottomSheet", id);
        bottomSheetFragment.setArguments(bundle);
        bottomSheetFragment.show(getSupportFragmentManager(), null);

    }

    //Timer for Slider
    public class TimerSlider extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderVp.getCurrentItem() < list.size() - 1) {
                        sliderVp.setCurrentItem(sliderVp.getCurrentItem() + 1);
                    } else {
                        sliderVp.setCurrentItem(0);
                    }
                }
            });
        }
    }

}



