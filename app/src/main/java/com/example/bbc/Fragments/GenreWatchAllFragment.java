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

import com.example.bbc.databinding.WatchAllFragmentBinding;
import com.example.bbc.db.volley.GenreVolley;

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

        GenreVolley genreVolley = new GenreVolley(recyclerView, true);
        genreVolley.setRequestQueue();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
