package com.example.bbc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bbc.application.app;
import com.example.bbc.databinding.ItemDetailMovieFragmentBinding;
import com.example.bbc.model.TopMovieModel;
import com.squareup.picasso.Picasso;

public class DetailMovieFragment extends Fragment {

    private ItemDetailMovieFragmentBinding binding;
    private TopMovieModel movieModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemDetailMovieFragmentBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieModel = getArguments().getParcelable(app.SINGLE_MOVIE);

        if (movieModel != null) {
            binding.tvSingleName.setText(movieModel.getName());
            Picasso.get().load(movieModel.getImg()).into(binding.ivSingle);
            binding.tvSingleDirector.setText(movieModel.getDirector());
            binding.tvSingleDescription.setText(movieModel.getDescription());
            binding.tvSingleRate.setText(movieModel.getRate_imdb());
            binding.tvSingleTime.setText(movieModel.getTime());
            binding.tvSingleGenre.setText(movieModel.getGenre_name());
            binding.tvSinglePublished.setText(movieModel.getPublished());
        }


    }
}
