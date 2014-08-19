package com.eure.traveling;



import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MyRequest {

    public static final String TAG = MyRequest.class.getSimpleName();


    public static JsonObjectRequest MyJsonObjectRequest(final String category, int page) {
        return new JsonObjectRequest(Request.Method.GET, "http://api.dribbble.com/shots/" + category + "?page=" + page,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ShotsParser.parseShotsList(response, category);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }
        );
    }
}
