package com.example.testandroid.repository

import com.example.testandroid.data.model.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<GetMoviesResponse>
}