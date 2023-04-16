package com.example.bbc.db.volley;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.CastAdapter;
import com.example.bbc.application.app;
import com.example.bbc.model.CastModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CastVolley {


    private int id;
    List<CastModel> list = new ArrayList<>();
    CastAdapter adapter;
    RecyclerView recyclerView;
    String link;
    public CastVolley(RecyclerView recyclerView, int id) {
        this.recyclerView = recyclerView;
        this.id = id;
        link = app.LINK + "getCast.php?id_item=" + id;
    }

    public void setRequestQueue() {

        Log.e("ID ", "" + id);
        Log.e("link ", "" + link);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > i; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        CastModel castModel = new CastModel();

                        int id = jsonObject.getInt(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);

                        castModel.setId(id);
                        castModel.setName(name);
                        castModel.setImg(img);

                        list.add(castModel);
                        adapter = new CastAdapter(list);
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

}
