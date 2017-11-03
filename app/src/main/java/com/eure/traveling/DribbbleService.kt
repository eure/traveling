package com.eure.traveling

import com.eure.traveling.entity.Shot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

object DribbbleService {
    val API_URL = "https://api.dribbble.com/v1/"

    interface Dribbble {
        @GET("shots/?access_token=" + BuildConfig.DRIBBBLE_CLIENT_ACCESS_TOKEN)
        fun shots(@Query("list") type: String, @Query("page") page: Int): Call<List<Shot>>
    }

    @Throws(IOException::class)
    fun main(callback: Callback<List<Shot>>, type: String, page: Int) {
        val retrofit = Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

        val dribbble = retrofit.create<Dribbble>(Dribbble::class.java)

        val call = dribbble.shots(type, page)

        call.enqueue(callback)
    }
}
