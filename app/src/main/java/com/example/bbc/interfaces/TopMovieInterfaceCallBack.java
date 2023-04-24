package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.TopMovieModel;

import java.util.List;

public interface TopMovieInterfaceCallBack {


    void onSuccess(List<TopMovieModel> list);

    void onError(VolleyError error);


}
