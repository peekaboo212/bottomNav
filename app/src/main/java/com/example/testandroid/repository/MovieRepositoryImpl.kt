package com.example.testandroid.repository

import com.example.testandroid.data.remote.MovieDataSource
class MovieRepositoryImpl(private val dataSource: MovieDataSource) : MovieRepository {
    override suspend fun getPopularMovies() = dataSource.getPopularMovies()
}