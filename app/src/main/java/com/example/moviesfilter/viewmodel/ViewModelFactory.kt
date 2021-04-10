package com.example.moviesfilter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesfilter.api.ApiHelper
import com.example.moviesfilter.repository.MovieRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}