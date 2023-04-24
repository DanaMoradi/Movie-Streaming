package com.example.bbc.interfaces;

import com.android.volley.VolleyError;
import com.example.bbc.model.SliderModel;

import java.util.List;

public interface SliderInterfaceCallBack {

    void onSuccess(List<SliderModel> list);

    void onError(VolleyError error);

}
