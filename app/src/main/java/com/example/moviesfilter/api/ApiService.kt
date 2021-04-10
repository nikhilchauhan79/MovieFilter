package com.example.moviesfilter.api

import com.example.moviesfilter.model.Movie
import retrofit2.http.GET

interface ApiService {

    @GET("moviedata.json")
    suspend fun getMovies(): List<Movie>

}