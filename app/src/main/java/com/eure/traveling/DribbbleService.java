package com.eure.traveling;

import com.eure.traveling.entity.Shot;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public final class DribbbleService {
    public static final String API_URL = "https://api.dribbble.com/v1/";

    public interface Dribbble {
        @GET("shots/?access_token=" + BuildConfig.DRIBBBLE_ACCESS_TOKEN)
        Call<List<Shot>> shots(@Query("list") String type, @Query("page") int page);
    }

    public static void main(Callback<List<Shot>> callback, String type, int page) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        Dribbble dribbble = retrofit.create(Dribbble.class);

        Call<List<Shot>> call = dribbble.shots(type, page);

        call.enqueue(callback);
    }
}
