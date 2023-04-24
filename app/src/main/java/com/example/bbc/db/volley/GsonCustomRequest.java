package com.example.bbc.db.volley;


import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonCustomRequest<T> extends Request<T> {

    Gson gson = new Gson();
    Type type;
    Response.Listener<T> listener;

    public GsonCustomRequest(int method, Type type, String url, Response.Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.type = type;
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        String responseString = new String(networkResponse.data);
        T response = gson.fromJson(responseString, type);
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(networkResponse));

    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
