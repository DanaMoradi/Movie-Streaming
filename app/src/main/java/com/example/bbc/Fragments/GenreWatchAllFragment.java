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
import com.example.bbc.adapter.AllGenreAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.WatchAllFragmentBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.GenreInterfaceCallBack;
import com.example.bbc.model.GenreModel;

import java.util.List;

public class GenreWatchAllFragment extends Fragment {


    private WatchAllFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WatchAllFragmentBinding.inflate(LayoutInflater.from(container.getContext()), container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.rvFragmentWatchAll;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.getAllGenre(new GenreInterfaceCallBack() {
            @Override
            public void onSuccess(List<GenreModel> list) {
                AllGenreAdapter adapter = new AllGenreAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(app.TAG, error.getMessage());
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
