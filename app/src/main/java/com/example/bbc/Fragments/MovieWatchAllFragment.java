package com.example.bbc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.bbc.adapter.AllTopMovieAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.WatchAllFragmentBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.TopMovieInterfaceCallBack;
import com.example.bbc.model.TopMovieModel;

import java.util.List;

public class MovieWatchAllFragment extends Fragment {


    private WatchAllFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = WatchAllFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.rvFragmentWatchAll;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.getAllTopMovie(new TopMovieInterfaceCallBack() {
            @Override
            public void onSuccess(List<TopMovieModel> list) {
                AllTopMovieAdapter adapter = new AllTopMovieAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
