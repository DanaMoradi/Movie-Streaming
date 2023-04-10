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
import com.squareup.picasso.Picasso;

public class SingleFragment extends Fragment {

    private ItemDetailMovieFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemDetailMovieFragmentBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        binding.tvSingleName.setText(bundle.getString(app.NAME));
        Picasso.get().load(bundle.getString(app.IMG)).into(binding.ivSingle);
        binding.tvSingleDirector.setText(bundle.getString(app.DIRECTOR));
        binding.tvSingleDescription.setText(bundle.getString(app.DESCRIPTION));
        binding.tvSingleRate.setText(bundle.getString(app.RATE_IMDB));
        binding.tvSingleTime.setText(bundle.getString(app.TIME));
    }
}
