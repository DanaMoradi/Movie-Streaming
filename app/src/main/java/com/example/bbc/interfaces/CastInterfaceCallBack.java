package com.example.bbc.interfaces;

import com.example.bbc.model.CastModel;

import java.util.List;

public interface CastInterfaceCallBack extends VolleyErrorInterfaceCallBack {

    void onSuccess(List<CastModel> list);

}
