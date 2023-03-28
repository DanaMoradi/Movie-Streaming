package com.example.bbc.db.volley;

import android.content.Context;
import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.TopMovieMainAdapter;
import com.example.bbc.model.TopMovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopMovieVolley {


    String link = volleyApp.LINK + "getMovieInformation.php?" + volleyApp.CATEGORY + "=" + volleyApp.CATEGORY_TOP_MOVIE;

    RequestQueue requestQueue;
    List<TopMovieModel> list = new ArrayList<>();
    TopMovieMainAdapter adapter;
    Context context;
    ViewPager2 viewPager2;

    public TopMovieVolley(Context context, RequestQueue requestQueue, ViewPager2 viewPager2) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.viewPager2 = viewPager2;
    }

    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > 0; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TopMovieModel topMovieModel = new TopMovieModel();

                        String id = jsonObject.getString(volleyApp.ID);
                        String name = jsonObject.getString(volleyApp.NAME);
                        String img = jsonObject.getString(volleyApp.IMG);
                        String description = jsonObject.getString(volleyApp.DESCRIPTION);

                        topMovieModel.setId(id);
                        topMovieModel.setName(name);
                        topMovieModel.setImg(img);
                        topMovieModel.setDescription(description);

                        list.add(topMovieModel);
                        adapter = new TopMovieMainAdapter(list);
                        viewPager2.setAdapter(adapter);
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
        requestQueue.add(jsonObjectRequest);
    }

}
