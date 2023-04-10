package com.example.bbc.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.Fragments.SingleFragment;
import com.example.bbc.R;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityDetailPageBinding;

public class DetailPageActivity extends AppCompatActivity {


    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Bundle getBundle = getIntent().getExtras();


        switch (getBundle.getString(app.SINGLE_KEY)) {
            case app.SINGLE_MOVIE:
                SingleFragment singleFragment = new SingleFragment();

                int id = getBundle.getInt(app.ID);
                String name = getBundle.getString(app.NAME);
                String img = getBundle.getString(app.IMG);
                String rate_imdb = getBundle.getString(app.RATE_IMDB);
                String description = getBundle.getString(app.DESCRIPTION);
                String director = getBundle.getString(app.DIRECTOR);
                String time = getBundle.getString(app.TIME);

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                getBundle.putInt(app.ID, id);
                getBundle.putString(app.NAME, name);
                getBundle.putString(app.IMG, img);
                getBundle.putString(app.RATE_IMDB, rate_imdb);
                getBundle.putString(app.DESCRIPTION, description);
                getBundle.putString(app.DIRECTOR, director);
                getBundle.putString(app.TIME, time);

                singleFragment.setArguments(getBundle);

                fragmentTransaction.replace(R.id.frame_single_fragment, singleFragment);
                fragmentTransaction.commit();
                break;


        }
    }
}