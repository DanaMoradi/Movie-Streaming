//package com.example.bbc.db.volley;
//
//import android.net.http.HttpResponseCache;
//
//import androidx.annotation.Nullable;
//
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.google.gson.JsonObject;
//
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//
//public class StringRequest<T> extends Request<StringRequest> {
//
//    Response.Listener<StringRequest> listener;
//    HashMap<String, String> params;
//    Class<T> clazz;
//
//    public StringRequest(int method, Class<T> clazz, HashMap<String, String> params, String url, Response.Listener<StringRequest> listener, @Nullable Response.ErrorListener errorListener) {
//        super(method, url, errorListener);
//        this.listener = listener;
//        this.clazz = clazz;
//        this.params = params;
//    }
//
//    @Override
//    protected Response<StringRequest> parseNetworkResponse(NetworkResponse response) {
//        try {
//            String responseString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//            return Response.success(response , HttpHeaderParser);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void deliverResponse(StringRequest response) {
//        listener.onResponse(response);
//    }
//}
