package com.example.bbc.interfaces;

public interface RegisterCallBack extends VolleyErrorInterfaceCallBack {

    void onEmailAndPhoneCheck(String email, String phone);

    void onAddUserCallBack(String item);

}
