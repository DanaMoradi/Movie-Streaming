package com.example.bbc.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.bbc.adapter.RelatedAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.FragmentDetailMovieBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.RelatedMovieCallBack;
import com.example.bbc.model.RelatedModel;

import java.util.List;

public class DetailMovieRelatedMovie extends Fragment implements RelatedMovieCallBack {

    private String genre;
    FragmentDetailMovieBinding binding;
    private RecyclerView recyclerView;

    public DetailMovieRelatedMovie(String genre) {
        this.genre = genre;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.rvDetailMovie;
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setHasFixedSize(true);

        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.getRelatedMovie(genre, this);

    }



    @Override
    public void onSuccess(List<RelatedModel> list) {
        RelatedAdapter adapter = new RelatedAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(VolleyError error) {
        Log.e("________________", error.getMessage());
    }
}
