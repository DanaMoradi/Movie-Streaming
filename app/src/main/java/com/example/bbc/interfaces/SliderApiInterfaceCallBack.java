package com.example.bbc.interfaces;

import com.example.bbc.model.SliderModel;

import java.util.List;

public interface SliderApiInterfaceCallBack extends VolleyErrorInterfaceCallBack {

    void onSuccess(List<SliderModel> list);

}
