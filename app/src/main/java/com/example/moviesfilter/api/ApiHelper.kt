package com.example.moviesfilter.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getMovies() = apiService.getMovies()
}