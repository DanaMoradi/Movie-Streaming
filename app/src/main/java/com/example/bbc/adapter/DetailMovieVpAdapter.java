package com.example.bbc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.bbc.Fragments.DetailMovieCastFragment;
import com.example.bbc.Fragments.DetailMovieRelatedMovie;

public class DetailMovieVpAdapter extends FragmentStateAdapter {

    private final int id;
    private final String genre;

    public DetailMovieVpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, int id, String genre) {
        super(fragmentManager, lifecycle);
        this.id = id;
        this.genre = genre;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new DetailMovieCastFragment(id);
            case 1:
                return new DetailMovieRelatedMovie(genre);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }




}
