package com.example.bbc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.FragmentDetailMovieCastBinding;
import com.example.bbc.db.volley.CastVolley;

public class DetailMovieCastFragment extends Fragment {

    FragmentDetailMovieCastBinding binding;

    int id;

    public DetailMovieCastFragment(int id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailMovieCastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView castRecyclerView = binding.rvDetailMovieCast;
        castRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        CastVolley castVolley = new CastVolley(castRecyclerView, id);
        castVolley.setRequestQueue();

    }
}
