package com.example.bbc.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class app extends Application {


    public static final String TAG = app.class.getSimpleName();


    public static final String ALL_KEY = "watchAll";
    public static final String ALL_MOVIE = "watchAllMovie";
    public static final String ALL_GENRE = "watchAllGenre";
    public static final String ALL_SERIES = "watchAllSeries";
    public static final String SINGLE_MOVIE = "movie";
    public static final String SINGLE_SERIES = "series";


    public static final String REGISTER = "register";
    public static final String LOGIN = "login";


    RequestQueue mRequestQueue;


    private static app mInstance;


    public static synchronized app getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mInstance);
        }
        return mRequestQueue;
    }

    public void addToRequestQueue(Request req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public void addToRequestQueue(Request req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequest(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
