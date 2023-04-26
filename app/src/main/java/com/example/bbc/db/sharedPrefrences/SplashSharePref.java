package com.example.bbc.db.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;

public class SplashSharePref {

    private SharedPreferences sharedPreferences;

    public SplashSharePref(Context context) {
        sharedPreferences = context.getSharedPreferences("splash", Context.MODE_PRIVATE);
    }

    public boolean isOpen() {
        return sharedPreferences.getBoolean("isOpen", false);
    }

    public void saveSplashScreen() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isOpen", true);
        editor.apply();
    }


}
