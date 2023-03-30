package com.example.bbc.db.volley;

import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.TopMovieMainAdapter;
import com.example.bbc.application.app;
import com.example.bbc.model.TopMovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopMovieVolley {


    String link = app.LINK + "getMovieInformation.php?" + app.CATEGORY + "=" + app.CATEGORY_TOP_MOVIE;
    List<TopMovieModel> list = new ArrayList<>();
    TopMovieMainAdapter adapter;
    ViewPager2 viewPager2;

    public TopMovieVolley(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
    }

    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > i; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        TopMovieModel topMovieModel = new TopMovieModel();

                        Long id = jsonObject.getLong(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);
                        String description = jsonObject.getString(app.DESCRIPTION);

                        topMovieModel.setId(id);
                        topMovieModel.setName(name);
                        topMovieModel.setImg(img);
                        topMovieModel.setDescription(description);


                        list.add(topMovieModel);
                        adapter = new TopMovieMainAdapter(list);
                        viewPager2.setAdapter(adapter);
                        viewPager2.setCurrentItem(1, false);
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
