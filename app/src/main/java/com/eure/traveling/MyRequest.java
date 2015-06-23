package com.eure.traveling;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MyRequest {

    public static final String TAG = MyRequest.class.getSimpleName();

    private static MyRequest sInstance;

    public static MyRequest getInstance() {
        if (sInstance == null) {
            sInstance = new MyRequest();
        }
        return sInstance;
    }

    public JsonObjectRequest MyJsonObjectRequest(final String category, int page,
            final RequestListener.SuccessListener successListener,
            final RequestListener.FailureListener failureListener) {
        return new JsonObjectRequest(
                "http://api.dribbble.com/shots/" + category + "?page=" + page,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ShotsParser.parseShotsList(response, category);
                        successListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        failureListener.onErrorResponse(error);
                    }
                }
        );
    }
}
