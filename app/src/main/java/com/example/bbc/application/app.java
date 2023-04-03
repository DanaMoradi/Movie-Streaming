package com.example.bbc.application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class app extends Application {


    public static final String TAG = app.class.getSimpleName();


    public static final String LINK = "http://bbc.mywebcommunity.org/";
    //    public static final String LINK = "http://192.168.132.122/BBC/";


    public static final String ALL_MOVIE = "watchAllMovie";


    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMG = "img";
    public static final String DIRECTOR = "director";
    public static final String RATE_IMDB = "rate_imdb";
    public static final String PUBLISHED = "published";
    public static final String TIME = "time";
    public static final String CATEGORY = "category_name";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_TOP_MOVIE = "top_movie_new";
    public static final String CATEGORY_NEW_MOVIE = "movie_new";
    public static final String CATEGORY_SERIES = "series";
    public static final String CATEGORY_ANIMATION = "animation";
    public static final String CATEGORY_POPULAR = "popular_movie";


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
