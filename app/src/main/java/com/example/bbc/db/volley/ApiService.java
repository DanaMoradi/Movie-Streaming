package com.example.bbc.db.volley;

import static com.example.bbc.db.common.ApiConstant.CAST_URL;
import static com.example.bbc.db.common.ApiConstant.RELATED_MOVIE_URL;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.bbc.application.app;
import com.example.bbc.db.common.ApiConstant;
import com.example.bbc.interfaces.CastInterfaceCallBack;
import com.example.bbc.interfaces.GenreInterfaceCallBack;
import com.example.bbc.interfaces.RelatedMovieCallBack;
import com.example.bbc.interfaces.SeriesInterfaceCallBack;
import com.example.bbc.interfaces.SliderInterfaceCallBack;
import com.example.bbc.interfaces.TopMovieInterfaceCallBack;
import com.example.bbc.model.CastModel;
import com.example.bbc.model.GenreModel;
import com.example.bbc.model.RelatedModel;
import com.example.bbc.model.SeriesModel;
import com.example.bbc.model.SliderModel;
import com.example.bbc.model.TopMovieModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ApiService {


    RequestQueue requestQueue;
    Gson gson;
    private final String requestTag;
    private final Context context;


    public ApiService(Context context, String requestTag) {
        gson = new Gson();
        this.requestTag = requestTag;
        this.context = context;
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public void getSlider(SliderInterfaceCallBack callBack) {

        GsonCustomRequest<List<SliderModel>> getSliderRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<SliderModel>>() {
        }.getType(), ApiConstant.SLIDER_URL, callBack::onSuccess, callBack::onError);
        app.getInstance().addToRequestQueue(getSliderRequest);
    }

    public void getGenre(GenreInterfaceCallBack callBack) {
        GsonCustomRequest<List<GenreModel>> getGenreRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<GenreModel>>() {
        }.getType(), ApiConstant.GENRE_URL, callBack::onSuccess, callBack::onError);

        app.getInstance().addToRequestQueue(getGenreRequest);

    }

    public void getAllGenre(GenreInterfaceCallBack callBack) {
        GsonCustomRequest<List<GenreModel>> getGenreRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<GenreModel>>() {
        }.getType(), ApiConstant.GENRE_ALL_URL, callBack::onSuccess, callBack::onError);

        app.getInstance().addToRequestQueue(getGenreRequest);

    }

    public void getTopMovie(TopMovieInterfaceCallBack callBack) {

        GsonCustomRequest<List<TopMovieModel>> getTopMovieRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<TopMovieModel>>() {
        }.getType(), ApiConstant.TOP_MOVIE_URL, callBack::onSuccess, callBack::onError);
        app.getInstance().addToRequestQueue(getTopMovieRequest);
    }

    public void getAllTopMovie(TopMovieInterfaceCallBack callBack) {

        GsonCustomRequest<List<TopMovieModel>> getTopMovieRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<TopMovieModel>>() {
        }.getType(), ApiConstant.TOP_MOVIE_ALL_URL, callBack::onSuccess, callBack::onError);
        app.getInstance().addToRequestQueue(getTopMovieRequest);
    }

    public void getSeries(SeriesInterfaceCallBack callBack) {
        GsonCustomRequest<List<SeriesModel>> getSeriesRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<SeriesModel>>() {
        }.getType(), ApiConstant.SERIES_URL, new Response.Listener<List<SeriesModel>>() {
            @Override
            public void onResponse(List<SeriesModel> response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });
        app.getInstance().addToRequestQueue(getSeriesRequest);
    }

    public void getAllSeries(SeriesInterfaceCallBack callBack) {

        GsonCustomRequest<List<SeriesModel>> getSeriesRequest = new GsonCustomRequest<>(Request.Method.GET, new TypeToken<List<SeriesModel>>() {
        }.getType(), ApiConstant.SERIES_ALL_URL, callBack::onSuccess, callBack::onError);
        app.getInstance().addToRequestQueue(getSeriesRequest);
    }

    public void getCastMovie(int id, CastInterfaceCallBack callBack) {
        String link = CAST_URL + id;
        GsonCustomRequest<List<CastModel>> getCastRequest = new GsonCustomRequest<>(Request.Method.GET,
                new TypeToken<List<CastModel>>() {
                }.getType(), link, new Response.Listener<List<CastModel>>() {
            @Override
            public void onResponse(List<CastModel> response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });
        app.getInstance().addToRequestQueue(getCastRequest);
    }

    public void getRelatedMovie(String genre, RelatedMovieCallBack callBack) {
        String url = RELATED_MOVIE_URL + genre;
        GsonCustomRequest<List<RelatedModel>> getRelatedRequest = new GsonCustomRequest<>(Request.Method.GET,
                new TypeToken<List<RelatedModel>>() {
                }.getType(), url, new Response.Listener<List<RelatedModel>>() {
            @Override
            public void onResponse(List<RelatedModel> response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });
        app.getInstance().addToRequestQueue(getRelatedRequest);
    }

}
