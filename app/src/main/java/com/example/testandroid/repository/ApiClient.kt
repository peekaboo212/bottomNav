package com.example.testandroid.repository

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "f6a645a26832545d6a571359dafdf621"



    val apiServices: ApiServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiServices::class.java)

    }

}