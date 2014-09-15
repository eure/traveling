package com.eure.traveling;



import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MyRequest {

    public static final String TAG = MyRequest.class.getSimpleName();

    public interface SuccessListener<T> {
        public void onResponse(T response);
    }

    public interface ErrorListener {
        public void onErrorResponse(VolleyError error);
    }

    public static JsonObjectRequest MyJsonObjectRequest(final String category, int page
            , final SuccessListener successListener, final ErrorListener errorListener) {
        return new JsonObjectRequest(Request.Method.GET, "http://api.dribbble.com/shots/" + category + "?page=" + page,
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
                        errorListener.onErrorResponse(error);
                    }
                }
        );
    }
}
