package com.example.bbc.db.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.bbc.db.common.ApiConstant;
import com.example.bbc.model.UserModel;

public class UserSharedPref {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public UserSharedPref(Context context) {
        sharedPreferences = context.getSharedPreferences(ApiConstant.USER_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveRegister(UserModel item) {
        editor = sharedPreferences.edit();
        editor.putString(ApiConstant.NAME, item.getFull_name());
        editor.putString(ApiConstant.USER_EMAIL, item.getEmail());
        editor.putString(ApiConstant.USER_PHONE, item.getPhone());
        editor.putString(ApiConstant.USER_PASSWORD, item.getPassword());
        editor.apply();
    }

    public Boolean isUserLogin() {
        return sharedPreferences.getBoolean(ApiConstant.USER_IS_LOGIN, false);
    }

    public void setUserLogIn(Boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(ApiConstant.USER_IS_LOGIN, true);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString(ApiConstant.USER_NAME, "");
    }

    public String getEmail() {
        return sharedPreferences.getString(ApiConstant.USER_EMAIL, "");
    }

    public String getPhone() {
        return sharedPreferences.getString(ApiConstant.USER_PHONE, "");


    }

    public String getPassword() {
        return sharedPreferences.getString(ApiConstant.USER_PASSWORD, "");
    }

    public void clearName() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ApiConstant.USER_NAME);
        editor.apply();

    }

    public void clearEmail() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ApiConstant.USER_EMAIL);
        editor.apply();

    }

    public void clearPhone() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ApiConstant.USER_PHONE);
        editor.apply();

    }

    public void clearPassword() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(ApiConstant.USER_PASSWORD);
        editor.apply();

    }


    public void clearAll() {
        editor = sharedPreferences.edit();
        Log.e("ok", "ok");
        editor.clear();
        editor.apply();
    }


}
