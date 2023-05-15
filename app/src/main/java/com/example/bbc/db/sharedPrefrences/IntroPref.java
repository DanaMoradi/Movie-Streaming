package com.example.bbc.db.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bbc.db.common.ApiConstant;

public class SplashSharePref {




    private SharedPreferences sharedPreferences;

    public SplashSharePref(Context context) {
        sharedPreferences = context.getSharedPreferences("splash", Context.MODE_PRIVATE);
    }


    public boolean isOpen() {
        return sharedPreferences.getBoolean(ApiConstant.INTRO_IS_OPEN, false);
    }

    public void saveSplashScreen() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ApiConstant.INTRO_IS_OPEN, true);
        editor.apply();
    }


}
