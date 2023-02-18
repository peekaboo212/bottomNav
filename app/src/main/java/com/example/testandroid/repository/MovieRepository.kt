package com.example.testandroid.repository

import com.example.testandroid.data.model.GetMoviesResponse

interface MovieRepository {
    suspend fun getPopularMovies(): GetMoviesResponse
}