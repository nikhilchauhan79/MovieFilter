package com.example.moviesfilter.repository

import com.example.moviesfilter.api.ApiHelper

class MovieRepository(private val apiHelper: ApiHelper)  {

    suspend fun getMovies() = apiHelper.getMovies()

}