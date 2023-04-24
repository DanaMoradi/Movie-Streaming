package com.example.bbc.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.bbc.adapter.AllSeriesAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.WatchAllFragmentBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.SeriesInterfaceCallBack;
import com.example.bbc.model.SeriesModel;

import java.util.List;

public class SeriesWatchAllFragment extends Fragment {

    private WatchAllFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WatchAllFragmentBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = binding.rvFragmentWatchAll;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.getAllSeries(new SeriesInterfaceCallBack() {
            @Override
            public void onSuccess(List<SeriesModel> list) {
                AllSeriesAdapter adapter = new AllSeriesAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {
                Log.e(app.TAG, error.getMessage());
            }
        });

    }
}
