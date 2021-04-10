package com.example.moviesfilter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviesfilter.repository.MovieRepository
import com.example.moviesfilter.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = movieRepository.getMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}