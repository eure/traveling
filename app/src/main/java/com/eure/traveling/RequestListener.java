package com.eure.traveling;

import com.android.volley.VolleyError;

public class RequestListener {

    public interface SuccessListener<T> {

        public void onResponse(T response);
    }

    public interface FailureListener {

        public void onErrorResponse(VolleyError error);
    }
}
