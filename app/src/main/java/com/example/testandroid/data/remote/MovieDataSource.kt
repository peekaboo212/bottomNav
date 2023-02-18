package com.example.testandroid.data.remote

import com.example.testandroid.data.model.GetMoviesResponse
import com.example.testandroid.repository.ApiClient
import com.example.testandroid.repository.ApiServices

class MovieDataSource(private val apiServices: ApiServices) {
    fun getPopularMovies(): GetMoviesResponse {
        return apiServices.getPopularMovies(ApiClient.API_KEY).execute().body()!!
    }
}