package com.example.bbc.db.volley;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.GenreMainAdapter;
import com.example.bbc.model.GenreModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GenreVolley {


    String link = volleyApp.LINK + "getGenre.php";

    RequestQueue requestQueue;
    List<GenreModel> list = new ArrayList<>();
    GenreMainAdapter adapter;
    Context context;
    RecyclerView recyclerView;

    public GenreVolley(Context context, RequestQueue requestQueue, RecyclerView recyclerView) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.recyclerView = recyclerView;
    }

    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > 0; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        GenreModel genreModel = new GenreModel();

                        String id = jsonObject.getString(volleyApp.ID);
                        String name = jsonObject.getString(volleyApp.NAME);
                        String img = jsonObject.getString(volleyApp.IMG);

                        genreModel.setId(id);
                        genreModel.setName(name);
                        genreModel.setImg(img);

                        list.add(genreModel);
                        adapter = new GenreMainAdapter(list);
                        recyclerView.setAdapter(adapter);
                        Log.e("Try" , list.size()+"");
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
