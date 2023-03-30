package com.example.bbc.db.volley;

import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bbc.adapter.SliderAdapter;
import com.example.bbc.application.app;
import com.example.bbc.model.SliderModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SliderVolley {

    String link = app.LINK + "getSlider.php";
    List<SliderModel> list;
    SliderAdapter sliderAdapter;
    ViewPager viewPager;

    public SliderVolley(ViewPager viewPager, List<SliderModel> list) {
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

                        Long id = jsonObject.getLong(app.ID);
                        String name = jsonObject.getString(app.NAME);
                        String img = jsonObject.getString(app.IMG);

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
        app.getInstance().addToRequestQueue(jsonObjectRequest);
    }

}
