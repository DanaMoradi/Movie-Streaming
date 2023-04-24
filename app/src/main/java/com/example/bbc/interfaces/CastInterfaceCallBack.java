package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.CastModel;

import java.util.List;

public interface CastInterfaceCallBack {

    void onSuccess(List<CastModel> list);

    void onError(VolleyError error);


}
