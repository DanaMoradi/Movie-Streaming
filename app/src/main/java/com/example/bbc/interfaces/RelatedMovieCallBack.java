package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.RelatedModel;

import java.util.List;

public interface RelatedMovieCallBack {

    void onSuccess(List<RelatedModel> list);

    void onError(VolleyError error);


}
