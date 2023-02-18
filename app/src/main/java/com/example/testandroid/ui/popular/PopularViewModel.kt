package com.example.testandroid.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.testandroid.repository.MovieRepository
import com.example.testandroid.utils.Resource
import kotlinx.coroutines.Dispatchers

class PopularViewModel(private val repo: MovieRepository) : ViewModel() {
    fun fetchPopularMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getPopularMovies()))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class PopularViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}