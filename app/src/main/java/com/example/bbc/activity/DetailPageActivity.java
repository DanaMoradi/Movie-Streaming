package com.example.bbc.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.Fragments.DetailMovieFragment;
import com.example.bbc.R;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ActivityDetailPageBinding;
import com.example.bbc.model.BottomSheetModel;

public class DetailPageActivity extends AppCompatActivity {

    private BottomSheetModel itemMovie;
    private BottomSheetModel itemSeries;

    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemMovie = getIntent().getParcelableExtra(app.SINGLE_MOVIE);
        itemSeries = getIntent().getParcelableExtra(app.SINGLE_SERIES);

        Bundle bundle = new Bundle();

        if (itemMovie != null) {
            DetailMovieFragment singleFragment = new DetailMovieFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            bundle.putParcelable(app.SINGLE_MOVIE, itemMovie);
            singleFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_single_fragment, singleFragment);
            fragmentTransaction.commit();
        } else if (itemSeries != null) {
            Toast.makeText(this, "This is a series", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }


    }
}