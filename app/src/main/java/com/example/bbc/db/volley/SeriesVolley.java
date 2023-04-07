package com.example.bbc.db.volley;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.AllSeriesAdapter;
import com.example.bbc.adapter.SeriesMainAdapter;
import com.example.bbc.application.app;
import com.example.bbc.model.SeriesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SeriesVolley {


    String limitedLink = app.LINK + "getSeriesInformation.php";
    String link = app.LINK + "getAllSeriesInformation.php";

    List<SeriesModel> list = new ArrayList<>();
    SeriesMainAdapter adapter;
    AllSeriesAdapter allSeriesAdapter;
    RecyclerView recyclerView;

    public SeriesVolley(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void setRequestQueueLimited() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, limitedLink, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > i; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SeriesModel seriesModel = new SeriesModel();

                        Long id = jsonObject.getLong(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);

                        seriesModel.setId(id);
                        seriesModel.setName(name);
                        seriesModel.setImg(img);

                        list.add(seriesModel);
                        adapter = new SeriesMainAdapter(list);
                        recyclerView.setAdapter(adapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Catch", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        app.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > i; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SeriesModel seriesModel = new SeriesModel();

                        Long id = jsonObject.getLong(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);
                        String director = jsonObject.getString(app.DIRECTOR);
                        String seasonAndEpisodes = jsonObject.getString(app.TIME);
                        String rateImdb = jsonObject.getString(app.RATE_IMDB);

                        seriesModel.setId(id);
                        seriesModel.setName(name);
                        seriesModel.setImg(img);
                        seriesModel.setDirector(director);
                        seriesModel.setSeasonsAndEpisodes(seasonAndEpisodes);
                        seriesModel.setRate_imdb(rateImdb);

                        list.add(seriesModel);
                        allSeriesAdapter = new AllSeriesAdapter(list);
                        recyclerView.setAdapter(allSeriesAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Catch", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
            }
        });
        app.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}
