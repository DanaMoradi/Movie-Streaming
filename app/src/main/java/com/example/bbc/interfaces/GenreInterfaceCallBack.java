package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.GenreModel;

import java.util.List;

public interface GenreInterfaceCallBack extends VolleyErrorInterfaceCallBack {

    void onSuccess(List<GenreModel> list);

}
