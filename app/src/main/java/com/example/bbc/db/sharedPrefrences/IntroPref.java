package com.example.bbc.db.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bbc.db.common.ApiConstant;

public class IntroPref {


    private SharedPreferences sharedPreferences;

    public IntroPref(Context context) {
        sharedPreferences = context.getSharedPreferences(ApiConstant.INTRO_PREF_NAME, Context.MODE_PRIVATE);
    }


    public boolean isOpen() {
        return sharedPreferences.getBoolean(ApiConstant.INTRO_IS_OPEN, false);
    }

    public void saveIntro() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ApiConstant.INTRO_IS_OPEN, true);
        editor.apply();
    }


}
