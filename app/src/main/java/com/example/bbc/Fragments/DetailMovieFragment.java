package com.example.bbc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.R;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ItemDetailMovieFragmentBinding;
import com.example.bbc.model.BottomSheetModel;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.squareup.picasso.Picasso;

public class DetailMovieFragment extends Fragment {

    private ItemDetailMovieFragmentBinding binding;
    private BottomSheetModel item;
    private MaterialButtonToggleGroup buttonToggleGroup;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemDetailMovieFragmentBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item = getArguments().getParcelable(app.SINGLE_MOVIE);

        if (item != null) {
            binding.tvSingleName.setText(item.getName());
            Picasso.get().load(item.getImg()).into(binding.ivSingle);
            binding.tvSingleDirector.setText(item.getDirector());
            binding.tvSingleDescription.setText(item.getDescription());
            binding.tvSingleRate.setText(item.getRate_imdb());
            binding.tvSingleTime.setText(item.getTime());
            binding.tvSingleGenre.setText(item.getGenre_name());
            binding.tvSinglePublished.setText(item.getPublished());
        }


        getCast();
        binding.tgDetailMovie.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {
                if (binding.btnDetailMovieCast.isChecked()) {
                    getCast();
                } else if (binding.btnDetailMovieRelated.isChecked()) {
                    getRelated();
                }
            }
        });

    }


    private void getCast() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        DetailMovieCastFragment castFragment = new DetailMovieCastFragment(item.getId());
        fragmentTransaction.replace(R.id.fram_detailMove, castFragment);
        fragmentTransaction.commit();
    }

    private void getRelated() {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        DetailMovieRelatedMovie relatedFragment = new DetailMovieRelatedMovie(item.getGenre_name());
        fragmentTransaction.replace(R.id.fram_detailMove, relatedFragment);
        fragmentTransaction.commit();
    }


}
