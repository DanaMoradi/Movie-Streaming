package com.example.bbc.db.volley;

import android.content.Context;
import android.util.Log;

import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.SliderAdapter;
import com.example.bbc.model.SliderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SliderVolley {

    String link = volleyApp.LINK + "getSlider.php";

    RequestQueue requestQueue;
    List<SliderModel> list;
    SliderAdapter sliderAdapter;
    Context context;
    ViewPager viewPager;

    public SliderVolley(Context context, RequestQueue requestQueue, ViewPager viewPager, List<SliderModel> list) {
        this.context = context;
        this.requestQueue = requestQueue;
        this.viewPager = viewPager;
        this.list = list;
    }

    public void setRequestQueue() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("movie_streaming");
                    for (int i = 0; jsonArray.length() > 0; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        SliderModel sliderModel = new SliderModel();

                        String id = jsonObject.getString(volleyApp.ID);
                        String name = jsonObject.getString(volleyApp.NAME);
                        String img = jsonObject.getString(volleyApp.IMG);

                        sliderModel.setId(id);
                        sliderModel.setName(name);
                        sliderModel.setImg(img);

                        list.add(sliderModel);
                        sliderAdapter = new SliderAdapter(list);
                        viewPager.setAdapter(sliderAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}
