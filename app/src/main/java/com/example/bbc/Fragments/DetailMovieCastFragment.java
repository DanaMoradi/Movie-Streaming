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
import com.example.bbc.adapter.CastAdapter;
import com.example.bbc.application.app;
import com.example.bbc.databinding.FragmentDetailMovieBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.CastInterfaceCallBack;
import com.example.bbc.model.CastModel;

import java.util.List;

public class DetailMovieCastFragment extends Fragment implements CastInterfaceCallBack {

    FragmentDetailMovieBinding binding;

    private Long id;
    private RecyclerView castRecyclerView;


    public DetailMovieCastFragment(Long id) {
        this.id = id;
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


        castRecyclerView = binding.rvDetailMovie;
        castRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        castRecyclerView.setHasFixedSize(true);

        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.getCastMovie(id, this);


    }

    @Override
    public void onSuccess(List<CastModel> list) {
        CastAdapter adapter = new CastAdapter(list);
        castRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(VolleyError error) {
        Log.e(app.TAG, error.getMessage());
    }
}
