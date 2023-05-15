package com.example.bbc.db.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.bbc.application.app;
import com.example.bbc.db.common.ApiConstant;
import com.example.bbc.interfaces.RegisterCallBack;
import com.example.bbc.model.UserModel;

import org.json.JSONException;
import org.json.JSONObject;


public class UserApi extends ApiService {

    public UserApi(Context context, String requestTag) {
        super(context, requestTag);
    }

    public void isEmailPhoneExist(String phone, String email, RegisterCallBack callBack) {

        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("phone", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GsonCustomRequest<UserModel> request = new GsonCustomRequest<>(Request.Method.POST, UserModel.class, ApiConstant.CHECK_EMAIL_PHONE, body, new Response.Listener<UserModel>() {
            @Override
            public void onResponse(UserModel response) {
                callBack.onEmailAndPhoneCheck(response.getEmail(), response.getPhone());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });

        app.getInstance().addToRequestQueue(request);
    }


    public void addUser(String name, String email, String phone, String password, RegisterCallBack callBack) {
        JSONObject body = new JSONObject();
        try {
            body.put("name", name);
            body.put("email", email);
            body.put("phone", phone);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        GsonCustomRequest<UserModel> addUserRequest = new GsonCustomRequest<>(Request.Method.POST, UserModel.class, ApiConstant.REGISTER, body, new Response.Listener<UserModel>() {
            @Override
            public void onResponse(UserModel response) {
                callBack.onAddUserCallBack(response.getStatus());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onError(error);
            }
        });
        requestQueue.add(addUserRequest);
    }


}