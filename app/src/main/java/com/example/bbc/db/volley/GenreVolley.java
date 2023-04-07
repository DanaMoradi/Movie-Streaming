package com.example.bbc.db.volley;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.AllGenreAdapter;
import com.example.bbc.adapter.GenreMainAdapter;
import com.example.bbc.application.app;
import com.example.bbc.model.GenreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenreVolley {


    String link = app.LINK + "getGenre.php";


    Boolean all = false;
    List<GenreModel> list = new ArrayList<>();
    GenreMainAdapter adapter;
    AllGenreAdapter allGenreAdapter;
    RecyclerView recyclerView;

    public GenreVolley(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public GenreVolley(RecyclerView recyclerView, Boolean all) {
        this.recyclerView = recyclerView;
        this.all = all;
    }


    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");

                    for (int i = 0; jsonArray.length() > i; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        GenreModel genreModel = new GenreModel();

                        Long id = jsonObject.getLong(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);

                        genreModel.setId(id);
                        genreModel.setName(name);
                        genreModel.setImg(img);

                        list.add(genreModel);
                        if (all) {
                            allGenreAdapter = new AllGenreAdapter(list);
                            recyclerView.setAdapter(allGenreAdapter);
                        } else {
                            adapter = new GenreMainAdapter(list);
                            recyclerView.setAdapter(adapter);
                        }
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
