package com.example.bbc.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.Fragments.MovieWatchAllFragment;
import com.example.bbc.R;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityWatchAllBinding;

public class WatchAllActivity extends AppCompatActivity {

    private ActivityWatchAllBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWatchAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        String getPutExtra = bundle.getString(app.ALL_MOVIE);


        switch (getPutExtra) {
            case app.ALL_MOVIE:

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_watchAll_fragment, new MovieWatchAllFragment());
                fragmentTransaction.commit();
                break;
        }


    }
}