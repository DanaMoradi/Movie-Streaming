package com.example.bbc.interfaces;

import com.example.bbc.model.TopMovieModel;

import java.util.List;

public interface TopMovieInterfaceCallBack extends VolleyErrorInterfaceCallBack {


    void onSuccess(List<TopMovieModel> list);


}
