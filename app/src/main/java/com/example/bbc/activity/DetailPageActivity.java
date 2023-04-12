package com.example.bbc.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.Fragments.DetailMovieFragment;
import com.example.bbc.R;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityDetailPageBinding;
import com.example.bbc.model.SeriesModel;
import com.example.bbc.model.TopMovieModel;

public class DetailPageActivity extends AppCompatActivity {


    private TopMovieModel movieModel;
    private SeriesModel seriesModel;
    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        movieModel = getIntent().getParcelableExtra(app.SINGLE_MOVIE);
        seriesModel = getIntent().getParcelableExtra(app.SINGLE_SERIES);


        Bundle bundle = new Bundle();

        if (movieModel != null) {
            DetailMovieFragment singleFragment = new DetailMovieFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putParcelable(app.SINGLE_MOVIE, movieModel);
            singleFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_single_fragment, singleFragment);
            fragmentTransaction.commit();
        }

        if (seriesModel != null) {
            Log.e("SERIES", seriesModel + "");
        }


    }
}