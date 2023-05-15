package com.example.bbc.interfaces;

import com.example.bbc.model.UserModel;

public interface RegisterCallBack extends VolleyErrorInterfaceCallBack {

    void onEmailAndPhoneCheck(UserModel item);

}
