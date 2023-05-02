package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.SeriesModel;

import java.util.List;

public interface SeriesInterfaceCallBack extends VolleyErrorInterfaceCallBack {


    void onSuccess(List<SeriesModel> list);



}
