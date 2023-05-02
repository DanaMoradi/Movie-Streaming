package com.example.bbc.interfaces;

import com.example.bbc.model.BottomSheetModel;

public interface BottomSheetInterfaceCallBack extends VolleyErrorInterfaceCallBack {
    void onSuccess(BottomSheetModel item);
}
